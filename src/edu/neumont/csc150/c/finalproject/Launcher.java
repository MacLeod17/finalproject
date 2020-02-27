package edu.neumont.csc150.c.finalproject;

import edu.neumont.csc150.c.finalproject.controller.MainMenuController;
import edu.neumont.csc150.c.finalproject.view.MainMenuUI;

import java.io.IOException;

public class Launcher {

    private MainMenuUI ui = new MainMenuUI();

    public static void main(String[] args) throws IOException {
        new MainMenuController().run();
    }
}
