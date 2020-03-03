package edu.neumont.csc150.c.finalproject.controller;

import edu.neumont.csc150.c.finalproject.model.Enemy;
import edu.neumont.csc150.c.finalproject.model.Game;
import edu.neumont.csc150.c.finalproject.model.playerclasses.Player;
import edu.neumont.csc150.c.finalproject.model.terrains.Terrain;
import edu.neumont.csc150.c.finalproject.model.terrains.Town;
import edu.neumont.csc150.c.finalproject.view.GameUI;

import java.io.FileNotFoundException;
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

        game.initialSetGameMap();
        game.setUserMap();
        while (!exitRequested) {
            if (enemy != null) {
                onEnemyEncounter();
            }
            if (playerDead) {
                ui.readString("Press any button to return to Main Menu", 0);
                return;
            }
            ui.displayMessage(game.getMap());
            terrain = game.getTerrain();
            ui.displayMessage(terrain.getIntroString());
            exitRequested = onUserAction();
        }
    }

    private void onEnemyEncounter() throws IOException {
        boolean exitRequested = false;
        ui.displayMessage(String.format("Oh, no! %s has encountered a wild %s!", player.getName(), enemy.getName()));
        ui.readString("Press any key to enter battle", 0);
        while (!exitRequested) {
            ui.displayMessage(player.toBattleString());
            ui.displayMessage(enemy.toBattleString());
            GameUI.BattleMenuItem selection = ui.promptBattleMenuSelection();
            switch (selection) {
                case FLEE:
                    ui.displayMessage(String.format("The cowardly %s has fled the battle", player.getName()));
                    enemy = null;
                    return;
                case ATTACK:
                    int damage = player.attack(enemy.getArmorClass());
                    ui.displayMessage(String.format("%s's turn!", player.getName()));
                    ui.displayMessage(player.getHitType());
                    enemy.takeDamage(damage);
                    break;
                case DRINK_POTION:
                    try {
                        player.removePotion();
                        player.healDamage(player.roll(2, 4) + 2);
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
                    ui.readString("Press any key to continue the game", 0);
                }
                player.addGold(enemy.getGold());
                player.battleWin(enemy.getExpValue());
                if (player.getCurrentExp() >= player.getExpNeeded()) {
                    player.raiseLevel();
                }
                enemy = null;
                exitRequested = true;
            }
            else {
                int damage = enemy.attack(player.getArmorClass());
                ui.displayMessage(String.format("%s's turn!", enemy.getName()));
                ui.displayMessage(enemy.getHitType());
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
        String action = ui.readString("", 1);
        switch (action.toLowerCase()) {
            case "go east":
            case "go right":
                moveEast();
                player.onPassHour();
                return false;
            case "go south":
            case "go down":
                moveSouth();
                player.onPassHour();
                return false;
            case "go west":
            case "go left":
                moveWest();
                player.onPassHour();
                return false;
            case "go north":
            case "go up":
                moveNorth();
                player.onPassHour();
                return false;
            case "save game":
            case "save character":
                saveGame();
                return false;
            case "enter town":
            case "buy equipment":
            case "buy stuff":
            case "go to shop":
                promptTownMenu();
                player.onPassHour();
                return false;
            case "exit":
            case "leave game":
                return true;
            case "wait":
            case "rest":
                rest();
                return false;
            case "view character":
            case "character":
            case "see character":
            case "see stats":
            case "stats":
            case "character stats":
                viewCharacter();
                return false;
            default:
                ui.displayError("I'm sorry, I can't let you do that. Do something else, my patience is wearing thin");
                return false;
        }
    }

    private void moveNorth() {
        game.moveNorth();
        setMaps();
        enemy = game.checkForEncounter();
    }

    private void moveSouth() {
        game.moveSouth();
        setMaps();
        enemy = game.checkForEncounter();
    }

    private void moveEast() {
        game.moveEast();
        setMaps();
        enemy = game.checkForEncounter();
    }

    private void moveWest() {
        game.moveWest();
        setMaps();
        enemy = game.checkForEncounter();
    }

    private void saveGame() throws IOException {
        MainMenuController.saveCharacter(String.format("%s_%s", player.getClass().getSimpleName(), player.getName()), player);
        ui.displayMessage("Your character has been saved successfully");
        ui.readString("Press any key to continue", 0);
    }

    private void promptTownMenu() throws IOException {
        if (!(terrain instanceof Town)) {
            ui.displayError("I'm sorry, but I cannot let you enter a town that does not exist");
            return;
        }
        boolean exitRequested = false;
        while (!exitRequested) {
            GameUI.TownMenuItem selection = ui.promptTownMenuSelection();
            switch (selection) {
                case LEAVE_TOWN:
                    exitRequested = true;
                    break;
                case REST:
                    safeRest();
                    break;
                case BUY_ARMOR:
                    break;
                case BUY_POTION:
                    break;
                case BUY_RING:
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
        ui.readString("Press any key to continue", 0);
    }

    private void safeRest() {
        player.onWait();
        ui.displayMessage(String.format("%s finds an inn and sleeps behind the safe walls of the town", player.getName()));
    }

    private void setMaps() {
        game.setGameMap();
        game.setUserMap();
    }
}

