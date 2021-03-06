/*
 * Created by JFormDesigner on Sat Feb 17 14:58:16 GMT 2018
 */

package ct414;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Jekaterina Zenkevica
 */
public class AssessmentGUI extends JFrame {

    Assessment assessment;
    ExamServer examServer;
    int token;
    int ID_NUMBER;
    Question selectedQuestion;

    public AssessmentGUI(ExamServer server, int token, int username, Assessment assess) {
        this.examServer = server;
        this.assessment = assess;
        this.token = token;
        this.ID_NUMBER = username;
        try{
            selectedQuestion = assessment.getQuestion(1);
            initComponents();
        }catch (InvalidQuestionNumber invalidQuestionNumber){
            System.err.println("Invalid Question Number");
            invalidQuestionNumber.printStackTrace();
        }


    }

    class ItemChangeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                //as we are selecting from the combo box, the user cannot type in invalid question number
                try {
                    selectedQuestion = assessment.getQuestion(comboBox1.getSelectedIndex() + 1);
                    QuestionValue.setText(selectedQuestion.getQuestionDetail());
                    comboBox2.removeAllItems();
                    for(int j = 0;j<selectedQuestion.getAnswerOptions().length; j++){
                        comboBox2.addItem(selectedQuestion.getAnswerOptions()[j]);
                    }
                }catch (InvalidQuestionNumber invalidQuestionNumber){
                    System.err.println("Invalid Question Number");
                    invalidQuestionNumber.printStackTrace();
                }
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Jekaterina Zenkevica
        label1 = new JLabel();
        label2 = new JLabel();
        comboBox1 = new JComboBox();
        Select = new JLabel();
        QuestionValue = new JLabel();
        comboBox2 = new JComboBox();
        label3 = new JLabel();
        label4 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setTitle(assessment.getAssessmentCode());
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Due: " + assessment.getClosingDate());

        //---- label2 ----
        label2.setText("Available questions: " + assessment.getQuestions().length);

        //---- Select ----
        Select.setText("Select from: ");

        //---- QuestionValue ----
        QuestionValue.setText(selectedQuestion.getQuestionDetail());

        for (int i = 1; i<= assessment.getQuestions().length; i++){
            comboBox1.addItem(i);
        }

        for (int k = 0; k<selectedQuestion.getAnswerOptions().length; k++){
            comboBox2.addItem(selectedQuestion.getAnswerOptions()[k]);
        }

        comboBox1.addItemListener(new ItemChangeListener());

        //---- label3 ----
        label3.setText("Question:");

        //---- label4 ----
        label4.setText("Select Answer:");

        //---- button1 ----
        button1.setText("Submit");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    assessment.selectAnswer(comboBox1.getSelectedIndex()+1, comboBox2.getSelectedIndex());
                    examServer.submitAssessment(token,ID_NUMBER,assessment);
                    setVisible(false);
                    //default title and icon
                    JOptionPane.showMessageDialog(contentPane,
                            "Question submitted");
                }catch (InvalidQuestionNumber invalidQuestionNumber){
                    invalidQuestionNumber.printStackTrace();
                    System.err.println("Invalid Question Number");

                }catch (InvalidOptionNumber invalidOptionNumber){
                    invalidOptionNumber.printStackTrace();
                    System.err.println("Invalid Question Number");
                }catch (UnauthorizedAccess unauthorizedAccess){
                    unauthorizedAccess.printStackTrace();
                    System.err.println("Unauthorized Access");
                }catch (RemoteException remoteException){
                    remoteException.printStackTrace();
                    System.err.println("Remote Exception");
                }catch (NoMatchingAssessment noMatchingAssessment){
                    noMatchingAssessment.printStackTrace();
                    System.err.println("No Matching Assessment");
                }
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label1)
                                .addComponent(label2)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(Select)
                                    .addGap(18, 18, 18)
                                    .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label3)
                                            .addGap(84, 84, 84)
                                            .addComponent(QuestionValue))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label4)
                                            .addGap(34, 34, 34)
                                            .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))))
                    .addContainerGap(212, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 334, Short.MAX_VALUE)
                    .addComponent(button1)
                    .addGap(28, 28, 28))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(label1)
                    .addGap(18, 18, 18)
                    .addComponent(label2)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(Select))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(QuestionValue))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label4)
                        .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(31, 31, 31)
                    .addComponent(button1)
                    .addContainerGap(44, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        setVisible(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Jekaterina Zenkevica
    private JLabel label1;
    private JLabel label2;
    private JComboBox comboBox1;
    private JLabel Select;
    private JLabel QuestionValue;
    private JComboBox comboBox2;
    private JLabel label3;
    private JLabel label4;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
