package edu.neumont.csc150.c.finalproject.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public abstract class GeneralUI {

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public GeneralUI() {

    }

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

    public String readString(String prompt, int minLength) throws IOException {
        while (true) {
            displayMessage(prompt);
            String input = in.readLine();
            if (input.length() < minLength) {
                displayError(String.format("Entry content must be at least %d characters long", minLength));
            }
            else {
                return input;
            }
        }
    }

    public LocalDate readDate(int minYear, int maxYear) throws IOException {
        displayMessage("Enter the year");
        int year = readInt(minYear, maxYear);
        displayMessage("Enter the month (1-12)");
        int month = readInt(1, 12);
        int maxDayInMonth = determineMaxDayInMonth(year, month);
        displayMessage(String.format("Enter the day (1-%d)", maxDayInMonth));
        int day = readInt(1, maxDayInMonth);
        return LocalDate.of(year, month, day);
    }

    public int determineMaxDayInMonth(int year, int month) {
        return LocalDate.of(year, month, 1)
                .plusMonths(1)
                .minusDays(1)
                .getDayOfMonth();
    }

    public void displayError(String msg) {
        System.err.println(msg);
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }
}
