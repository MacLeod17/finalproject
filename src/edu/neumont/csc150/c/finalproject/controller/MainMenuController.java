package edu.neumont.csc150.c.finalproject.controller;

import edu.neumont.csc150.c.finalproject.view.MainMenuUI;

import java.io.File;
import java.io.IOException;

public class MainMenuController {

    private final static String characterFolder = "characters";
    private MainMenuUI ui = new MainMenuUI();

    public MainMenuController() {
        File folder = new File(characterFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public void run() throws IOException {
        boolean exitRequested = false;
        while (!exitRequested) {
            MainMenuUI.MenuItem selection = ui.promptMenuSelection();
            switch (selection) {
                case EXIT:
                    exitRequested = true;
                    break;
                case PLAY_GAME:
                    playGame();
                    break;
                case CREATE_CHARACTER:
                    createCharacter();
                    break;
                case VIEW_CHARACTER:
                    viewCharacter();
                    break;
                case SEARCH_CHARACTERS:
                    searchCharacters();
                    break;
            }
        }
    }

    private void playGame() {
        //TODO Implement Character search
        new GameController().run();
    }

    private void createCharacter() {
        new QuestionController().run();
    }

    private void viewCharacter() {

    }

    private void searchCharacters() {
        //TODO Implement search by character level-level range
    }
}
