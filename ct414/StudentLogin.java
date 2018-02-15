import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import ct414.Student;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/*
 * Created by JFormDesigner on Tue Feb 13 19:32:35 GMT 2018
 */



/**
 * @author unknown
 */
public class StudentLogin extends JFrame {

    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Student";
            Registry registry = LocateRegistry.createRegistry(1099);
            Student student = (Student) registry.lookup(name);
        } catch (Exception e) {
            System.err.println("StudentLogin exception:");
            e.printStackTrace();
        }
    }
    public StudentLogin() {
        initComponents();
    }

    private void loginButtonActionPerformed(ActionEvent e) {
        String usernameValue = usernameField.getText();
        String passwordValue = String.valueOf(passwordField1.getPassword());

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Jekaterina Zenkevica
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        usernameField = new JTextField();
        passwordField1 = new JPasswordField();
        loginButton = new JButton();

        //======== this ========
        setFont(new Font("Dialog", Font.PLAIN, 16));
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Username:");

        //---- label2 ----
        label2.setText("Password:");

        //---- label3 ----
        label3.setText("Assessment System");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.ITALIC));

        //---- loginButton ----
        loginButton.setText("Login");
        loginButton.addActionListener(e -> loginButtonActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(126, 126, 126)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1)
                        .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2)
                        .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(123, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(229, Short.MAX_VALUE)
                    .addComponent(loginButton)
                    .addGap(101, 101, 101))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(loginButton)
                    .addContainerGap(38, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Jekaterina Zenkevica
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField usernameField;
    private JPasswordField passwordField1;
    private JButton loginButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
