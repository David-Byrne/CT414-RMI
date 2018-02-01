package ct414;

import java.util.Date;

public class MCQassessment implements Assessment{

    private String information;
    private Date closingDate;
    private Question[] questions;

    public MCQassessment(String information, Date closingDate, Question[] questions) {
        this.information = information;
        this.closingDate = closingDate;
        this.questions = questions;
    }

    @Override
    public String getInformation() {
        return this.information;
    }

    @Override
    public Date getClosingDate() {
        return this.closingDate;
    }

    @Override
    public Question[] getQuestions() {
        return this.questions;
    }

    @Override
    public Question getQuestion(int questionNumber) throws InvalidQuestionNumber {
        // TODO
        return null;
    }

    @Override
    public void selectAnswer(int questionNumber, int optionNumber) throws InvalidQuestionNumber, InvalidOptionNumber {
        // TODO
    }

    @Override
    public int getSelectedAnswer(int questionNumber) {
        // TODO
        return 0;
    }

    @Override
    public int getAssociatedID() {
        // TODO

        return 0;
    }
}
