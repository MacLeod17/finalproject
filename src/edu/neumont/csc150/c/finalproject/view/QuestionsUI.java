package edu.neumont.csc150.c.finalproject.view;

import edu.neumont.csc150.c.finalproject.model.Question;
import edu.neumont.csc150.c.finalproject.model.Questions;

import java.io.IOException;

public class QuestionsUI extends GeneralUI {
    public enum Answers {
        EXIT,
        A,
        B,
        C,
        D
    }

    private Questions questions = new Questions();

    public QuestionsUI() {

    }

    public QuestionsUI.Answers promptMenuSelection(int questionsIndex) throws IOException {
        Question question = questions.getQuestions().get(questionsIndex);
        String mainMenuString = buildQuestionDisplay(question);
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
