package edu.neumont.csc150.c.finalproject.controller;

import edu.neumont.csc150.c.finalproject.model.Player;
import edu.neumont.csc150.c.finalproject.view.QuestionsUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionController {

    QuestionsUI ui = new QuestionsUI();
    private int fighterAnswerCounter;
    private int wizardAnswerCounter;
    private int clericAnswerCounter;
    private int thiefAnswerCounter;

    public Player run() throws IOException {
        int questionCounter = 0;
        boolean exitRequested = false;
        while (!exitRequested && questionCounter < 10) {
            QuestionsUI.Answers selection = ui.promptMenuSelection(questionCounter);
            switch (selection) {
                case EXIT:
                    exitRequested = true;
                    break;
                case A:
                    fighterAnswerCounter++;
                    break;
                case B:
                    wizardAnswerCounter++;
                    break;
                case C:
                    clericAnswerCounter++;
                    break;
                case D:
                    thiefAnswerCounter++;
                    break;
            }
            questionCounter++;
        }
        String charClass = determineCharacterClass();
//        ui.displayMessage(String.format("Fighter: %d", fighterAnswerCounter));
//        ui.displayMessage(String.format("Wizard: %d", wizardAnswerCounter));
//        ui.displayMessage(String.format("Cleric: %d", clericAnswerCounter));
//        ui.displayMessage(String.format("Thief: %d", thiefAnswerCounter));
        ui.displayMessage(String.format("Your Class: %s", charClass));

        return createPlayer(charClass);
    }

    private String determineCharacterClass() {
        Random gen = new Random();
        List<Integer> counters = new ArrayList<>();
        counters.add(fighterAnswerCounter);
        counters.add(wizardAnswerCounter);
        counters.add(clericAnswerCounter);
        counters.add(thiefAnswerCounter);
        List<Integer> tieBreaker = new ArrayList<>();
        for (int i=0; i < counters.size(); i++) {
            if (counters.get(i) < fighterAnswerCounter || counters.get(i) < wizardAnswerCounter || counters.get(i) < clericAnswerCounter ||
                    counters.get(i) < thiefAnswerCounter) {
                tieBreaker.add(i, null);
            }
            else {
                tieBreaker.add(i, counters.get(i));
            }
        }
        int clazz = gen.nextInt(counters.size());
        while (tieBreaker.get(clazz) == null) {
            clazz = gen.nextInt(counters.size());
        }
        if (tieBreaker.get(clazz) == counters.get(0)) {
            return "Fighter";
        }
        else if (tieBreaker.get(clazz) == counters.get(1)) {
            return "Wizard";
        }
        else if (tieBreaker.get(clazz) == counters.get(2)) {
            return "Cleric";
        }
        else if (tieBreaker.get(clazz) == counters.get(3)) {
            return "Thief";
        }
        else {
            throw new RuntimeException();
        }
    }

    private Player createPlayer(String charClass) throws IOException {
        String name = ui.readString("Enter your character's name", 2);
        String gender = ui.readString("Enter your character's gender, or press enter to use default", 0);
        switch (charClass) {
            case "Fighter":
                return new Player(name, 12, 13, 2, 1, 2, 8, charClass, gender);
            case "Wizard":
                return new Player(name, 6, 10, 0, 0, 1, 8, charClass, gender);
            case "Cleric":
                return new Player(name, 10, 12, 0, 1, 1, 10, charClass, gender);
            case "Thief":
                return new Player(name, 8, 10, 1, 0, 2, 6, charClass, gender);
            default:
                throw new RuntimeException();
        }
    }
}
