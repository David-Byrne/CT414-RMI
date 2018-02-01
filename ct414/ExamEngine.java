
package ct414;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class ExamEngine implements ExamServer {

    private Student[] students;

    // Constructor is required
    public ExamEngine(Student[] students) {
        super();
        this.students = students;
    }

    // Implement the methods defined in the ExamServer interface...
    // Return an access token that allows access to the server for some time period
    public int login(int studentid, String password) throws 
                UnauthorizedAccess, RemoteException {

        for(Student s: students){
            if(s.getId() == studentid && s.getPassword().equals(password)){
                return 1; //TODO
            }
        }

        throw new UnauthorizedAccess("Incorrect Username or Password");
    }

    // Return a summary list of Assessments currently available for this studentid
    public Assessment[] getAvailableSummary(int token, int studentid) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {

        //TODO check token
        for(Student s: students){
            if(s.getId() == studentid){
                return s.getAssessments();
            }
        }

        throw new NoMatchingAssessment("No assessments available for this student");
    }

    // Return an Assessment object associated with a particular course code
    public Assessment getAssessment(int token, int studentid, String courseCode) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {

        // TBD: You need to implement this method!
        // For the moment method just returns an empty or null value to allow it to compile

        return null;
    }

    // Submit a completed assessment
    public void submitAssessment(int token, int studentid, Assessment completed) throws 
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {

        // TBD: You need to implement this method!
    }

    private static Assessment[] generateAssessments(){
        Assessment as1 = new MCQassessment("Geography",
                new Date(),
                new Question[]{new MCQquestion("Capital of Ireland", new String[]{"Dublin","London","Galway"})});

        return new Assessment[]{as1};
    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Assessment[] assessments = generateAssessments();

            ExamEngine engine = new ExamEngine(
                    new Student[]{new Student(1, "secret", assessments)}
            );

            engine.login(1, "secret");
            System.out.println(engine.login(1, "secret"));
            System.out.println(engine.getAvailableSummary(0, 1)[0].getInformation());

            ExamServer stub = (ExamServer) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("ExamServer", stub);
            System.out.println("ExamEngine bound");
        } catch (Exception e) {
            System.err.println("ExamEngine exception:");
            e.printStackTrace();
        }
    }
}
