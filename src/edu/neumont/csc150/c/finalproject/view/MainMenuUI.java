package edu.neumont.csc150.c.finalproject.view;

import java.io.IOException;

public class MainMenuUI extends GeneralUI {
    public enum MenuItem {
        EXIT,
        PLAY_GAME,
        CREATE_CHARACTER,
        VIEW_CHARACTER,
        SEARCH_CHARACTERS
    }

    public MainMenuUI() {

    }

    public MenuItem promptMenuSelection() throws IOException {
        String mainMenuString = buildMainMenuDisplay();
        displayMessage(mainMenuString);
        int sel = readInt(0, 4);
        return MenuItem.values()[sel];
    }

    private String buildMainMenuDisplay() {
        return String.format("Main Menu\r\n" +
                        " 1 - %s\r\n" +
                        " 2 - %s\r\n" +
                        " 3 - %s\r\n" +
                        " 4 - %s\r\n" +
                        " 0 - %s\r\n",
                MenuItem.PLAY_GAME, MenuItem.CREATE_CHARACTER,
                MenuItem.VIEW_CHARACTER, MenuItem.SEARCH_CHARACTERS, MenuItem.EXIT);
    }
}
