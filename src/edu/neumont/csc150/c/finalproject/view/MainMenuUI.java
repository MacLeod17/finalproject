package edu.neumont.csc150.c.finalproject.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class MainMenuUI {
    public enum MenuItem {
        EXIT,

    }

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public MainMenuUI() {

    }

//    public MenuItem promptMenuSelection() throws IOException {
//        String mainMenuString = buildMainMenuDisplay();
//        displayMessage(mainMenuString);
//        int sel = readInt(0, 4);
//        return MenuItem.values()[sel];
//    }

//    private String buildMainMenuDisplay() {
//        return String.format("Main Menu\r\n" +
//                        " 1 - %s\r\n" +
//                        " 2 - %s\r\n" +
//                        " 3 - %s\r\n" +
//                        " 4 - %s\r\n" +
//                        " 0 - %s\r\n",
//                MenuItem.CREATE_ENTRY, MenuItem.VIEW_ENTRY,
//                MenuItem.SEARCH_ENTRIES, MenuItem.EDIT_ENTRY, MenuItem.EXIT);
//    }

    public int readInt(int min, int max) throws IOException {
        while(true) {
            String rawInput = in.readLine();
            try {
                int input = Integer.parseInt(rawInput);
                if (input < min || input > max) {
                    throw new NumberFormatException();
                }
                return input;
            }
            catch (NumberFormatException ex) {
                displayError(String.format("You must enter an integer between %d and %d", min, max));
            }
        }
    }

    public String readString(int minLength) throws IOException {
        while (true) {
            displayMessage("Ready to receive your entry. To submit, press enter");
            String input = in.readLine();
            if (input.length() < minLength) {
                displayError(String.format("Entry content must be at least %d characters long", minLength));
            }
            else {
                return input;
            }
        }
    }

    private LocalDate readDate(int minYear, int maxYear) throws IOException {
        displayMessage("Enter the year");
        int year = readInt(minYear, maxYear);
        displayMessage("Enter the month (1-12)");
        int month = readInt(1, 12);
        int maxDayInMonth = determineMaxDayInMonth(year, month);
        displayMessage(String.format("Enter the day (1-%d)", maxDayInMonth));
        int day = readInt(1, maxDayInMonth);
        return LocalDate.of(year, month, day);
    }

    private int determineMaxDayInMonth(int year, int month) {
        return LocalDate.of(year, month, 1)
                .plusMonths(1)
                .minusDays(1)
                .getDayOfMonth();
    }

    private void displayError(String msg) {
        System.err.println(msg);
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }
}
