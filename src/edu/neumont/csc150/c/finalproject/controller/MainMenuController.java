package edu.neumont.csc150.c.finalproject.controller;

import edu.neumont.csc150.c.finalproject.model.Player;
import edu.neumont.csc150.c.finalproject.view.MainMenuUI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenuController {

    private final static String characterFolder = "characters";
    private MainMenuUI ui = new MainMenuUI();
    private Player player;

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

    private void playGame() throws IOException {
        //TODO Implement Character search
        player = null;
        viewCharacter();
        if (player == null) {
            ui.displayError("Returning you to Main Menu");
        }
        else {
            boolean isPlaying = ui.readBoolean("Would you like to play this character?", "Yes", "No");
            if (isPlaying) {
                new GameController().run(player);
            }
            else {
                ui.displayMessage("Returning you to Main Menu");
            }
        }
    }

    private void createCharacter() throws IOException {
        Player player = new QuestionController().run();
        saveCharacter(String.format("%s_%d", player.getName(), player.getLevel()), player);
    }

    private void saveCharacter(String fileName, Player player) throws FileNotFoundException {
        File file = new File(characterFolder, fileName);
        PrintStream outFile = new PrintStream(file);
        try {
            outFile.print(player.serialize());
        }
        finally {
            outFile.close();
        }
    }

    /** View all of a specific Player's properties except attackDice, attackSides, winsNeeded, and winsToNextLevel */
    private void viewCharacter() throws IOException {
        String name = ui.readString("Enter your character's name", 2);
        ui.displayMessage("Enter your character's level");
        int level = ui.readInt(1, 100);
        String fileName = String.format("%s_%d", name, level);
        File folder = new File(characterFolder);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (fileName.equals(file.getName())) {
                BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                player = new Player();
                player.deserialize(inFile.readLine().trim());
                ui.displayMessage(player.toString());
                return;
            }
        }
        ui.displayError("There is no character with the name and level entered");
    }

    /** Search by range of levels; shows level, name, charClass, and gender of search results */
    private void searchCharacters() throws IOException {
        ui.displayMessage("Enter the min level");
        int min = ui.readInt(0, 100);
        ui.displayMessage("Enter max level");
        int max = ui.readInt(min, 100);
        List<Player> searchResults = new ArrayList<>();

        File folder = new File(characterFolder);
        File[] files = folder.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            String[] fileNamePieces = fileName.split("_");
            int level = Integer.parseInt(fileNamePieces[1]);
            if (level <= max && level >= min) {
                Player player = loadPlayer(file.getAbsolutePath());
                searchResults.add(player);
            }
        }
        for (Player searchResult : searchResults) {
            ui.displayMessage(searchResult.toSearchString());
        }
    }

    private Player loadPlayer(String userFileName) throws IOException {
        BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream(userFileName)));
        try {
            String content = "";
            while (inFile.ready()) {
                content += inFile.readLine() + System.lineSeparator();

            }
            if (!content.trim().isEmpty()) {
                Player p = new Player();
                p.deserialize(content);
                return p;
            }
        }
        finally {
            inFile.close();
        }
        return null;
    }
}
