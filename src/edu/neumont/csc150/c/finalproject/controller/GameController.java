package edu.neumont.csc150.c.finalproject.controller;

import edu.neumont.csc150.c.finalproject.model.Enemy;
import edu.neumont.csc150.c.finalproject.model.Game;
import edu.neumont.csc150.c.finalproject.model.playerclasses.Player;
import edu.neumont.csc150.c.finalproject.model.playerclasses.Wizard;
import edu.neumont.csc150.c.finalproject.model.terrains.Terrain;
import edu.neumont.csc150.c.finalproject.model.terrains.Town;
import edu.neumont.csc150.c.finalproject.view.GameUI;

import java.io.IOException;

public class GameController {

    private GameUI ui = new GameUI();
    private Game game = new Game();
    private Terrain terrain;
    private Player player;
    private boolean playerDead = false;
    private Enemy enemy;


    public void run(Player player) throws IOException {
        this.player = player;
        boolean exitRequested = false;

        game.initializeGameMap();
        game.setUserMap();
        while (!exitRequested) {
            if (enemy != null) {
                onEnemyEncounter();
            }
            if (playerDead) {
                ui.readString("Press enter key to return to Main Menu", 0);
                return;
            }
            ui.displayMessage(game.mapKey());
            ui.displayMessage(game.getMap());
            terrain = game.getTerrain();
            ui.displayMessage(terrain.getIntroString());
            exitRequested = onUserAction();
        }
    }

    private void onEnemyEncounter() throws IOException {
        boolean exitRequested = false;
        ui.displayMessage(String.format("Oh, no! %s has encountered a wild %s!", player.getName(), enemy.getName()));
        ui.readString("Press enter key to enter battle", 0);
        while (!exitRequested) {
            ui.displayMessage(player.toBattleString());
            ui.displayMessage(enemy.toBattleString());
            GameUI.BattleMenuItem selection = ui.promptBattleMenuSelection();
            switch (selection) {
                case FLEE:
                    ui.displayError(String.format("The cowardly %s has fled the battle", player.getName()));
                    ui.readString("Press enter key to continue your cowardly retreat", 0);
                    enemy = null;
                    return;
                case ATTACK:
                    int damage = player.attack(enemy.getArmorClass());
                    ui.displayMessage(String.format("%s's turn!", player.getName()));
                    ui.displayMessage(player.getHitType());
                    ui.displayMessage("");
                    enemy.takeDamage(damage);
                    break;
                case SPECIAL_ATTACK:
                    ui.displayError(String.format("Special attacks have not yet been implemented. While %s does nothing, the %s attacks",
                            player.getName(), enemy.getName()));
                    ui.readString("Press enter key to continue combat", 0);
                case DRINK_POTION:
                    try {
                        player.removePotion();
                        player.potionHeal(player.roll(2, 4) + 2);
                    }
                    catch (IllegalStateException ex) {
                        ui.displayError(String.format("%s has no potions! While fumbling for a potion %s doesn't have, the %s attacks.",
                                player.getName(), player.getPronoun().toLowerCase(), enemy.getName()));
                    }
                    break;
            }
            if (enemy.checkForDeath()) {
                if (enemy.isDespicableAct()) {
                    ui.displayError(String.format("Oh, the horror! %s has committed a despicable act!", player.getName()));
                    ui.displayError(String.format("%s should be ashamed of %s!", player.getName(), player.getOtherPronoun()));
                }
                else {
                    ui.displayMessage(String.format("%s has defeated the %s, looting %d gold and earning %d experience!",
                            player.getName(), enemy.getName(), enemy.getGold(), enemy.getExpValue()));
                    ui.readString("Press enter key to continue the game", 0);
                }
                player.addGold(enemy.getGold());
                player.battleWin(enemy.getExpValue());
                if (player.getCurrentExp() >= player.getExpNeeded()) {
                    player.raiseLevel();
                    ui.displayMessage(String.format("%s has gone up a level!", player.getName()));
                }
                enemy.resetEnemy();
                enemy = null;
                exitRequested = true;
            }
            else {
                int damage = enemy.attack(player.getArmorClass());
                ui.displayMessage(String.format("%s's turn!", enemy.getName()));
                ui.displayMessage(enemy.getHitType());
                ui.displayMessage("");
                player.takeDamage(damage);
            }
            if (player.checkForDeath()) {
                exitRequested = true;
                playerDead = true;
                ui.displayError(String.format("%s, the %s, has died. Please start a new game", player.getName(), player.getClass().getSimpleName()));
            }
        }
    }

    public boolean onUserAction() throws IOException {
        String action = ui.readString("", 1).toLowerCase();
        if (action.contains("east") || action.contains("right")) {
            moveEast();
            player.onPassHour();
            return false;
        }
        if (action.contains("north") || action.contains("up")) {
            moveNorth();
            player.onPassHour();
            return false;
        }
        if (action.contains("south") || action.contains("down")) {
            moveSouth();
            player.onPassHour();
            return false;
        }
        if (action.contains("west") || action.contains("left")) {
            moveWest();
            player.onPassHour();
            return false;
        }
        if (action.contains("save")) {
            saveGame();
            return false;
        }
        if (action.contains("town") || action.contains("shop") || action.contains("equipment")) {
            promptTownMenu();
            player.onPassHour();
            return false;
        }
        else if (action.contains("exit") || action.contains("leave")) {
            return true;
        }
        else if (action.contains("wait") || action.contains("rest")) {
            rest();
            return false;
        }
        else if (action.contains("character") || action.contains("stats")) {
            viewCharacter();
            return false;
        }
        else if (action.contains("potion")) {
            potionHeal();
            return false;
        }
        else {
            ui.displayError("I'm sorry, I can't let you do that. Do something else, my patience is wearing thin");
            return false;
        }
    }

    private void moveNorth() {
        game.moveNorth();
        setLocationCondition();
    }

    private void moveSouth() {
        game.moveSouth();
        setLocationCondition();
    }

    private void moveEast() {
        game.moveEast();
        setLocationCondition();
    }

    private void moveWest() {
        game.moveWest();
        setLocationCondition();
    }

    private void saveGame() throws IOException {
        MainMenuController.saveCharacter(String.format("%s_%s", player.getClass().getSimpleName(), player.getName()), player);
        ui.displayMessage("Your character has been saved successfully");
        ui.readString("Press enter key to continue", 0);
    }

    private void promptTownMenu() throws IOException {
        if (!(terrain instanceof Town)) {
            ui.displayError("I'm sorry, but I cannot let you enter a town that does not exist");
            return;
        }
        boolean exitRequested = false;
        while (!exitRequested) {
            GameUI.TownMenuItem selection = ui.promptTownMenuSelection();
            boolean willBuy;
            switch (selection) {
                case LEAVE_TOWN:
                    exitRequested = true;
                    break;
                case REST:
                    safeRest();
                    break;
                case BUY_ARMOR:
                    if (player instanceof Wizard) {
                        ui.displayMessage("A better Ring of Protection costs 150 gold");
                        willBuy = ui.readBoolean("Are you sure you want to buy a better Ring of Protection?", "Yes", "No");
                    }
                    else {
                        ui.displayMessage("Better armor costs 150 gold");
                        willBuy = ui.readBoolean("Are you sure you want to buy better armor?", "Yes", "No");
                    }
                    if (willBuy) {
                        try {
                            player.raiseArmorClass();
                        } catch (IllegalArgumentException ex1) {
                            if (player instanceof Wizard) {
                                ui.displayError(String.format("%s does not have enough gold to buy a better Ring of Protection!", player.getName()));
                            } else {
                                ui.displayError(String.format("%s does not have enough gold to buy more armor!", player.getName()));
                            }
                        } catch (ArithmeticException ex2) {
                            ui.displayError(String.format("%s's Armor Class cannot go any higher!", player.getName()));
                        }
                    }
                    break;
                case BUY_POTION:
                    ui.displayMessage("This Healing Potion costs 50 gold");
                    willBuy = ui.readBoolean("Are you sure you want to buy this Healing Potion?", "Yes", "No");
                    if (willBuy) {
                        try {
                            player.addPotion(50);
                        } catch (IllegalArgumentException ex1) {
                            ui.displayError(String.format("%s does not have enough gold to buy a healing potion!", player.getName()));
                        }
                    }
                    break;
                case BUY_RING:
                    ui.displayMessage("The Ring of Regeneration costs 500 gold");
                    willBuy = ui.readBoolean("Are you sure you want to buy this Ring of Regeneration?", "Yes", "No");
                    if (willBuy) {
                        try {
                            player.onBuyRegenerationRing(500);
                        } catch (IllegalArgumentException ex1) {
                            ui.displayError(String.format("%s does not have enough gold to buy this ring!", player.getName()));
                        } catch (RuntimeException ex2) {
                            ui.displayError(String.format("%s can only own one Ring of Regeneration!", player.getName()));
                        }
                    }
                    break;
            }
        }
    }

    private void rest() throws IOException {
        enemy = game.checkForEncounter();
        if (enemy != null) {
            ui.displayMessage(String.format("%s was awoken by the %s!", player.getName(), enemy.getName()));
            return;
        }
        player.onWait();
    }

    private void viewCharacter() throws IOException {
        ui.displayMessage(player.toString());
        ui.readString("Press enter key to continue", 0);
    }

    private void safeRest() {
        player.onWait();
        ui.displayMessage(String.format("%s finds an inn and sleeps behind the safe walls of the town", player.getName()));
    }

    private void potionHeal() {
        try {
            player.potionHeal(player.roll(2, 4) + 2);
        }
        catch (IllegalStateException ex) {
            ui.displayError(String.format("%s has no potions!", player.getName()));
        }
    }

    private void setLocationCondition() {
        game.setGameMap();
        game.setUserMap();
        enemy = game.checkForEncounter();
    }
}
