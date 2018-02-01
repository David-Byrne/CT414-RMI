package ct414;

public class MCQquestion implements Question {

    private String detail;
    private String[] answerOptions;

    public MCQquestion(String detail, String[] answerOptions) {
        this.detail = detail;
        this.answerOptions = answerOptions;
    }

    @Override
    public int getQuestionNumber() {
        //TODO
        return 0;
    }

    @Override
    public String getQuestionDetail() {
        return this.detail;
    }

    @Override
    public String[] getAnswerOptions() {
        return this.answerOptions;
    }
}
