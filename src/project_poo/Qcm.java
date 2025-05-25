package project_poo;

import java.util.ArrayList;
import java.util.List;

public class Qcm {
    private int questionNum;
    private String descriptifText;
    private List<Option> options;
    private int correctAnswers = 0;  // Correct answer count
    private int incorrectAnswers = 0;  // Incorrect answer count

    public Qcm(int questionNum, String descriptifText) {
        this.questionNum = questionNum;
        this.descriptifText = descriptifText;
        this.options = new ArrayList<>();
    }

    // Add an option to the QCM
    public void addOption(Option option) {
        this.options.add(option);
        // Update correct/incorrect counts
        if (option.getIsValid()) {
            correctAnswers++;
        } else {
            incorrectAnswers++;
        }
    }

    // Getters and Setters
    public int getQuestionNum() {
        return questionNum;
    }

    // Set QuestionNumber
    public void setQuestionNum(int newQuestionNum) {
        this.questionNum = newQuestionNum;
    }

    public String getQuestionText() {
        return descriptifText;
    }

    public void setQuestionText(String descriptifText) {
        this.descriptifText = descriptifText;
    }

    public List<Option> getOptions() {
        return options;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void printQcm() {
        System.out.println(questionNum + " ) " + descriptifText + " :");
        for (Option option : options) {
            option.printOption();
        }
    }
}
