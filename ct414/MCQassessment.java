package ct414;

import java.util.Date;

public class MCQassessment implements Assessment{

    private String information;
    private String code;
    private Date closingDate;
    private Question[] questions;
    private int studentID;

    public MCQassessment(String information, String assessmentCode, Student student, Date closingDate, Question[] questions) {
        this.information = information;
        this.code = assessmentCode;
        this.closingDate = closingDate;
        this.questions = questions;
        this.studentID = student.getId();
    }

    @Override
    public String getInformation() {
        return this.information;
    }

    public String getAssessmentCode() {
        return code;
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
        try{
            return questions[questionNumber - 1];
        } catch (ArrayIndexOutOfBoundsException e){
            throw new InvalidQuestionNumber();
        }
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
        return this.studentID;
    }
}
