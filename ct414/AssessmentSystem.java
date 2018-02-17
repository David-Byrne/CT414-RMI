package ct414;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
    Assessment currentAssessment;
    ExamServer server;
    int token;

    public AssessmentSystem(ExamServer server, int username, int token) {
        this.ID_NUMBER = username;

        this.server = server;

        this.token = token;

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

    class ItemChangeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                currentAssessment = assessmentList.get(comboBox1.getSelectedIndex());
                label7.setText(currentAssessment.getInformation());
                label8.setText("Due: " + currentAssessment.getClosingDate().toString());
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Jekaterina Zenkevica
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        panel1 = new JPanel();
        label5 = new JLabel();
        comboBox1 = new JComboBox();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setTitle("Assessment System");
        Container contentPane = getContentPane();

        //---- label2 ----
        label2.setText("Student ID: " + ID_NUMBER);
        label2.setFont(label2.getFont().deriveFont(Font.BOLD|Font.ITALIC, label2.getFont().getSize() + 6f));

        //---- label3 ----
        label3.setText("Assessments are not available at the moment");
        label3.setVisible(AssessmentNotAvailable);

        //---- label4 ----
        label4.setText(unAuthorisedExceptionReason);
        label4.setVisible(unAuthorised);
        if(!AssessmentNotAvailable) {

            //======== panel1 ========
            {
                //---- label5 ----
                label5.setText("Assignments Available: " + assessmentList.size());

                //---- label6 ----
                label6.setText("Select from: ");

                for(int i = 0; i<assessmentList.size(); i++){
                    comboBox1.addItem(assessmentList.get(i).getInformation());
                }

                currentAssessment = assessmentList.get(0);

                comboBox1.addItemListener(new ItemChangeListener());

                //---- label7 ----
                label7.setText(currentAssessment.getInformation());

                //---- label8 ----
                label8.setText("Due: " + currentAssessment.getClosingDate());

                //---- button1 ----
                button1.setText("Start Assignment");
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        AssessmentGUI assessmentGUI = new AssessmentGUI(server,token,ID_NUMBER, currentAssessment);
                    }
                });

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(panel1Layout.createParallelGroup()
                                                .addGroup(panel1Layout.createSequentialGroup()
                                                        .addComponent(label5)
                                                        .addContainerGap())
                                                .addGroup(panel1Layout.createSequentialGroup()
                                                        .addGroup(panel1Layout.createParallelGroup()
                                                                .addComponent(label6)
                                                                .addComponent(label7))
                                                        .addGroup(panel1Layout.createParallelGroup()
                                                                .addGroup(panel1Layout.createSequentialGroup()
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                                .addGroup(panel1Layout.createSequentialGroup()
                                                                        .addGap(142, 142, 142)
                                                                        .addComponent(label8)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 415, Short.MAX_VALUE)
                                                                        .addComponent(button1)
                                                                        .addGap(69, 69, 69))))))
                );
                panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(label5)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panel1Layout.createParallelGroup()
                                                .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label6))
                                        .addGroup(panel1Layout.createParallelGroup()
                                                .addGroup(panel1Layout.createSequentialGroup()
                                                        .addGap(32, 32, 32)
                                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(label7)
                                                                .addComponent(label8))
                                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(button1)
                                                        .addGap(17, 17, 17))))
                );
            }

        }
        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(48, 48, 48))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(308, 308, 308)
                            .addComponent(label4)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label3))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(label2)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(label2)
                    .addGap(34, 34, 34)
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4)
                        .addComponent(label3))
                    .addGap(184, 184, 184))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        setVisible(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Jekaterina Zenkevica
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JPanel panel1;
    private JLabel label5;
    private JComboBox comboBox1;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
