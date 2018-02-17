
package ct414;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ExamEngine implements ExamServer {

    private Student[] students;
    private Assessment[] assessments;
    private HashMap<Integer, Session> activeSessions;

    // Constructor is required
    public ExamEngine(Student[] students, Assessment[] assessments) {
        super();
        this.students = students;
        this.assessments = assessments;
        this.activeSessions = new HashMap<>();
    }

    // Implement the methods defined in the ExamServer interface...
    // Return an access token that allows access to the server for some time period
    public int login(int studentid, String password) throws 
                UnauthorizedAccess, RemoteException {

        for(Student s: students){
            if(s.getId() == studentid && s.getPassword().equals(password)){
                int token = new Random().nextInt(Integer.MAX_VALUE);
                activeSessions.put(token, new Session(studentid));
                return token;
            }
        }

        throw new UnauthorizedAccess("Incorrect Username or Password");
    }

    // Return a summary list of Assessments currently available for this studentid
    public List<Assessment> getAvailableSummary(int token, int studentid) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {

        validateToken(token, studentid);

        List<Assessment> validAssessments = new ArrayList<>();

        for(Assessment a: this.assessments){
            if(a.getAssociatedID() == studentid){
                validAssessments.add(a);
            }
        }

        if(validAssessments.size() == 0) {
            throw new NoMatchingAssessment("No assessments available for this student");
        }
        return validAssessments;
    }

    // Return an Assessment object associated with a particular course code
    public Assessment getAssessment(int token, int studentid, String assessmentCode) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {

        validateToken(token, studentid);

        for(Assessment a: assessments){
            if(a.getAssessmentCode().equals(assessmentCode) && a.getAssociatedID() == studentid &&
                    a.getClosingDate().after(new Date())){
                return a;
            }
        }

        throw new NoMatchingAssessment("No matching assignment found");
    }

    // Submit a completed assessment
    public void submitAssessment(int token, int studentid, Assessment completed) throws 
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {
        validateToken(token, studentid);

        for(int i=0; i<assessments.length; i++){
            if(assessments[i].getAssessmentCode().equals(completed.getAssessmentCode()) &&
                    assessments[i].getAssociatedID() == studentid &&
                    assessments[i].getClosingDate().after(new Date())){
                assessments[i] = completed;
                return;
            }
        }

        throw new NoMatchingAssessment("No matching assignment found");
    }

    private void validateToken(int token, int userID) throws UnauthorizedAccess, RemoteException {
        Session session = activeSessions.get(token);

        if (session == null || session.getUserID() != userID){
            throw new UnauthorizedAccess("Session doesn't exist, please log in");
        }

        if (session.getExpiry().before(new Date())){
            // If the token has expired, remove it
            activeSessions.remove(token);
            throw new UnauthorizedAccess("Session has expired, please log in");
        }

    }

    private static Assessment[] generateAssessments(Student student){
        Assessment geo = new MCQassessment("Geography", "GEO001", student,
            new Date( new Date().getTime() + 1000),
            new Question[]{
                    new MCQquestion("Capital of Ireland",
                            new String[]{"Dublin", "London", "Galway"},
                            1),
                    new MCQquestion("Capital of US",
                            new String[]{"Washington DC", "New York", "California"},
                            2),
                    new MCQquestion("Capital of South Korea",
                            new String[]{"Pyongyang", "Seoul", "Beijing"},
                            3)
            }
        );

        Assessment networks = new MCQassessment("Networks", "NET005", student,
            new Date( new Date().getTime() + 30000),
            new Question[]{
                new MCQquestion("localhost in IPv4",
                                new String[]{"192.168.1.1","0.0.0.0","127.0.0.1"},
                                1),
                new MCQquestion("Not an Email protocol",
                                new String[]{"POP3","ICMP","IMAP"},
                                2),
                new MCQquestion("MTU of ethernet",
                                new String[]{"1518 bytes","1500 bytes","1024 bytes"},
                                3)
            }
        );

        return new Assessment[]{geo, networks};
    }


    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Student stu1 = new Student(1, "secret");
            Student stu2 = new Student(2,"secret");
            Assessment[] assessments = generateAssessments(stu1);

            ExamEngine engine = new ExamEngine(
                    new Student[]{stu1,stu2},
                    assessments
            );

            ExamServer stub = (ExamServer) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ExamServer", stub);
            System.out.println("ExamEngine bound");
        } catch (Exception e) {
            System.err.println("ExamEngine exception:");
            e.printStackTrace();
        }
    }
}
