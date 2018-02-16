package ct414;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.rmi.RemoteException;
import java.util.List;

/*
 * Created by JFormDesigner on Tue Feb 13 21:09:29 GMT 2018
 */



/**
 * @author Jekaterina Zenkevica
 */
public class AssessmentSystem extends JFrame {

    static int ID_NUMBER;
    List<Assessment> assessmentList;
    boolean AssessmentNotAvailable = false;
    boolean unAuthorised = false;
    String unAuthorisedExceptionReason = "";

    public AssessmentSystem(ExamServer server, int username, int token) {
        this.ID_NUMBER = username;

        try{
            assessmentList = server.getAvailableSummary(token,username);
        }
        catch (NoMatchingAssessment noMatchingAssessment){
            AssessmentNotAvailable = true;
            System.err.println("No Assessment Available");
            noMatchingAssessment.printStackTrace();
        }
        catch (RemoteException remoteException){
            System.err.println("Remote Exception");
            remoteException.printStackTrace();
        }
        catch (UnauthorizedAccess unauthorizedAccess){
            unAuthorised = true;
            AssessmentNotAvailable = false;
            unAuthorisedExceptionReason = unauthorizedAccess.getMessage();
            System.err.println(unAuthorisedExceptionReason);
            unauthorizedAccess.printStackTrace();
        }
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Jekaterina Zenkevica
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();

        //======== this ========
        setTitle("Assessment System");
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.ITALIC, label1.getFont().getSize() + 10f));

        //---- label2 ----
        label2.setText("Student ID: " + ID_NUMBER);
        label2.setFont(label2.getFont().deriveFont(Font.BOLD|Font.ITALIC, label2.getFont().getSize() + 6f));

        //---- label3 ----
        label3.setText("Assessments are not available at the moment");
        label3.setVisible(AssessmentNotAvailable);

        //---- label4 ----
        label4.setText(unAuthorisedExceptionReason);
        label4.setVisible(unAuthorised);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(271, 271, 271)
                            .addComponent(label1))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(248, 248, 248)
                            .addComponent(label4)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label3))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(label2)))
                    .addContainerGap(289, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(label1)
                    .addGap(15, 15, 15)
                    .addComponent(label2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(label4))
                    .addGap(184, 184, 184))
        );
        pack();
        setLocationRelativeTo(getOwner());
        setVisible(true);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Jekaterina Zenkevica
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
