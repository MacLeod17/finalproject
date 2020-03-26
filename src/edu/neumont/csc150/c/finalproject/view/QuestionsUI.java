package edu.neumont.csc150.c.finalproject.view;

import edu.neumont.csc150.c.finalproject.model.Question;

import java.io.IOException;

public class QuestionsUI extends GeneralUI {
    public enum Answers {
        EXIT,
        A,
        B,
        C,
        D
    }

    public QuestionsUI() {

    }

    public QuestionsUI.Answers promptMenuSelection(Question q) throws IOException {
        String mainMenuString = buildQuestionDisplay(q);
        displayMessage(mainMenuString);
        int sel = readInt(0, 4);
        return QuestionsUI.Answers.values()[sel];
    }

    private String buildQuestionDisplay(Question question) {
        return String.format("%s\r\n" +
                        " 1 - %s: %s\r\n" +
                        " 2 - %s: %s\r\n" +
                        " 3 - %s: %s\r\n" +
                        " 4 - %s: %s\r\n" +
                        " 0 - %s\r\n",
                question.getQuestion(), Answers.A, question.getAnswerA(), Answers.B, question.getAnswerB(), Answers.C, question.getAnswerC(),
                Answers.D, question.getAnswerD(), Answers.EXIT);
    }
}
