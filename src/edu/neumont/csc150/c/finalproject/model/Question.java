package edu.neumont.csc150.c.finalproject.model;

public class Question {
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

    public Question() {

    }

    public Question(String question, String answerA, String answerB, String answerC, String answerD) {
        setQuestion(question);
        setAnswerA(answerA);
        setAnswerB(answerB);
        setAnswerC(answerC);
        setAnswerD(answerD);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        validateNotNull(question, "question");
        if (!question.contains("?")) {
            throw new IllegalArgumentException(String.format("'%s' is not a question", question));
        }
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        validateNotNull(answerA, "answerA");
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        validateNotNull(answerB, "answerB");
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        validateNotNull(answerC, "answerC");
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        validateNotNull(answerD, "answerD");
        this.answerD = answerD;
    }

    private void validateNotNull(Object parm, String name) {
        if(parm == null) {
            throw new IllegalArgumentException(String.format("%s cannot be null", name));
        }
    }
}
