package ct414;

public class MCQquestion implements Question {

    private String detail;
    private String[] answerOptions;
    private int number;

    public MCQquestion(String detail, String[] answerOptions, int number) {
        this.detail = detail;
        this.answerOptions = answerOptions;
        this.number = number;
    }

    @Override
    public int getQuestionNumber() {
        return this.number;
    }

    @Override
    public String getQuestionDetail() {
        return this.detail;
    }

    @Override
    public String[] getAnswerOptions() {
        return this.answerOptions;
    }

    @Override
    public String toString(){
        return this.detail;
    }
}
