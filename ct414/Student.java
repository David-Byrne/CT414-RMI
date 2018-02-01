package ct414;

public class Student {

    private int id;
    private String password;
    private Assessment[] assessments;

    public Student(int id, String password, Assessment[] assessments) {
        this.id = id;
        this.password = password;
        this.assessments = assessments;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Assessment[] getAssessments() {
        return assessments;
    }
}
