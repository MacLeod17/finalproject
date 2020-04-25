package edu.neumont.csc150.c.finalproject;

import edu.neumont.csc150.c.finalproject.controller.MainMenuController;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException {
        new MainMenuController().run();
    }
}
