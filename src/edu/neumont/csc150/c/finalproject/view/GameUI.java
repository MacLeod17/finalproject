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

    public enum BattleMenuItem {
        FLEE,
        ATTACK,
        SPECIAL_ATTACK,
        DRINK_POTION
    }

    public GameUI() {

    }

    public BattleMenuItem promptBattleMenuSelection() throws IOException {
        String townMenuString = buildBattleMenuDisplay();
        displayMessage(townMenuString);
        int sel = readInt(0, 3);
        return BattleMenuItem.values()[sel];
    }

    private String buildBattleMenuDisplay() {
        return String.format("To Battle!\r\n" +
                " 1 - %s\r\n" +
                " 2 - %s\r\n" +
                " 3 - %s\r\n" +
                " 0 - %s\r\n",
                BattleMenuItem.ATTACK, BattleMenuItem.SPECIAL_ATTACK, BattleMenuItem.DRINK_POTION, BattleMenuItem.FLEE);
    }

    public GameUI.TownMenuItem promptTownMenuSelection() throws IOException {
        String townMenuString = buildTownMenuDisplay();
        displayMessage(townMenuString);
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
