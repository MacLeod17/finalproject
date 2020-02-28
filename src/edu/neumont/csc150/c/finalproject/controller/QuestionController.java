package edu.neumont.csc150.c.finalproject.controller;

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

    public void run() throws IOException {
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
        ui.displayMessage(String.format("Fighter: %d", fighterAnswerCounter));
        ui.displayMessage(String.format("Wizard: %d", wizardAnswerCounter));
        ui.displayMessage(String.format("Cleric: %d", clericAnswerCounter));
        ui.displayMessage(String.format("Thief: %d", thiefAnswerCounter));
        ui.displayMessage(String.format("Your Class: %s", charClass));
    }

    private String determineCharacterClass() {
        Random gen = new Random();
        List<Integer> tieBreaker = new ArrayList<>();
        tieBreaker.add(fighterAnswerCounter);
        tieBreaker.add(wizardAnswerCounter);
        tieBreaker.add(clericAnswerCounter);
        tieBreaker.add(thiefAnswerCounter);
        //TODO Finish Tie Breaker sequence
        for (int i=0; i < tieBreaker.size(); i++) {
            if (tieBreaker.get(i) < fighterAnswerCounter || tieBreaker.get(i) < wizardAnswerCounter || tieBreaker.get(i) < clericAnswerCounter ||
                    tieBreaker.get(i) < thiefAnswerCounter) {
                tieBreaker.remove(tieBreaker.get(i));
            }
        }
        int clazz = gen.nextInt(tieBreaker.size()); //If there's 3-way tie, range is 0-2
        if (tieBreaker.get(clazz).equals(fighterAnswerCounter)) {
            return "Fighter";
        }
        else if (tieBreaker.get(clazz).equals(wizardAnswerCounter)) {
            return "Wizard";
        }
        else if (tieBreaker.get(clazz).equals(clericAnswerCounter)) {
            return "Cleric";
        }
        else if (tieBreaker.get(clazz).equals(thiefAnswerCounter)) {
            return "Thief";
        }
        else {
            throw new RuntimeException();
        }
    }
}
