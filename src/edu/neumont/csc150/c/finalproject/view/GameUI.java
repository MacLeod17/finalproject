package edu.neumont.csc150.c.finalproject.view;

import java.io.IOException;

public class GameUI extends GeneralUI {

    public enum TownMenuItem {
        LEAVE_TOWN,
        REST,
        BUY_POTION,
        BUY_ARMOR,
        BUY_RING
    }

    public GameUI() {

    }

    public GameUI.TownMenuItem promptTownMenuSelection() throws IOException {
        String mainMenuString = buildTownMenuDisplay();
        displayMessage(mainMenuString);
        int sel = readInt(0, 4);
        return TownMenuItem.values()[sel];
    }

    private String buildTownMenuDisplay() {
        return String.format("Town Menu\r\n" +
                        " 1 - %s\r\n" +
                        " 2 - %s\r\n" +
                        " 3 - %s\r\n" +
                        " 4 - %s\r\n" +
                        " 0 - %s\r\n",
                TownMenuItem.REST, TownMenuItem.BUY_POTION, TownMenuItem.BUY_ARMOR, TownMenuItem.BUY_RING, TownMenuItem.LEAVE_TOWN);
    }
}
