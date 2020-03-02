package edu.neumont.csc150.c.finalproject.controller;

import edu.neumont.csc150.c.finalproject.model.Game;
import edu.neumont.csc150.c.finalproject.model.Player;
import edu.neumont.csc150.c.finalproject.view.GameUI;

import java.io.IOException;

public class GameController {

    GameUI ui = new GameUI();
    Game game = new Game();
    Player p;

    public void run(Player player) {
        this.p = player;
        boolean exitRequested = false;

        while (!exitRequested) {

        }
    }

    public void onUserAction() throws IOException {
        String action = ui.readString("", 1);
        switch (action.toLowerCase()) {
            case "go east":
            case "go right":
                moveEast();
                break;
            case "go south":
            case "go down":
                moveSouth();
                break;
            case "go west":
            case "go left":
                moveWest();
                break;
            case "go north":
            case "go up":
                moveNorth();
                break;
        }
    }

    public void moveNorth() {
        game.moveNorth();
    }

    public void moveSouth() {
        game.moveSouth();
    }

    public void moveEast() {
        game.moveEast();
    }

    public void moveWest() {
        game.moveWest();
    }

}

