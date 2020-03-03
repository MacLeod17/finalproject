package edu.neumont.csc150.c.finalproject.controller;

import edu.neumont.csc150.c.finalproject.model.Game;
import edu.neumont.csc150.c.finalproject.model.Player;
import edu.neumont.csc150.c.finalproject.model.terrains.Terrain;
import edu.neumont.csc150.c.finalproject.model.terrains.Town;
import edu.neumont.csc150.c.finalproject.view.GameUI;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GameController {

    GameUI ui = new GameUI();
    Game game = new Game();
    Terrain terrain;
    Player p;

    public void run(Player player) throws IOException {
        this.p = player;
        boolean exitRequested = false;

        game.initialSetGameMap();
        game.setUserMap();
        while (!exitRequested) {
            ui.displayMessage(game.getMap());
            terrain = game.getTerrain();
            ui.displayMessage(terrain.getIntroString());
            exitRequested = onUserAction();
        }
    }

    public boolean onUserAction() throws IOException {
        String action = ui.readString("", 1);
        switch (action.toLowerCase()) {
            case "go east":
            case "go right":
                moveEast();
                return false;
            case "go south":
            case "go down":
                moveSouth();
                return false;
            case "go west":
            case "go left":
                moveWest();
                return false;
            case "go north":
            case "go up":
                moveNorth();
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
                return false;
            case "exit":
            case "leave game":
                return true;
            default:
                ui.displayError("I'm sorry, I can't let you do that. Do something else, my patience is wearing thin");
                return false;
        }
    }

    private void moveNorth() {
        game.moveNorth();
        setMaps();

    }

    private void moveSouth() {
        game.moveSouth();
        setMaps();

    }

    private void moveEast() {
        game.moveEast();
        setMaps();
    }

    private void moveWest() {
        game.moveWest();
        setMaps();
    }

    private void saveGame() throws FileNotFoundException {
        MainMenuController.saveCharacter(String.format("%s_%s", p.getCharClass(), p.getName()), p);
        ui.displayMessage("Your character has been saved successfully");
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

    private void setMaps() {
        game.setGameMap();
        game.setUserMap();
    }
}

