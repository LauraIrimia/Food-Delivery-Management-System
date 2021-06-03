package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminEmployeeLogInGUI extends JFrame {
    private JPanel contentPane;
    private JTextField logInUsername;
    private JPasswordField logInPassword;
    private JButton logInButton;

    public AdminEmployeeLogInGUI() {
        setTitle("Log In");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(350, 100, 230, 220);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel lblNewLabel_1 = new JLabel("Log In");
        lblNewLabel_1.setForeground(Color.RED);
        lblNewLabel_1.setBounds(85, 20, 49, 14);
        contentPane.add(lblNewLabel_1);

        logInUsername = new JTextField();
        logInUsername.setToolTipText("Username");
        logInUsername.setBounds(60, 45, 96, 20);
        contentPane.add(logInUsername);
        logInUsername.setColumns(10);

        logInPassword = new JPasswordField();
        logInPassword.setToolTipText("Password");
        logInPassword.setBounds(60, 76, 96, 20);

        contentPane.add(logInPassword);
        logInPassword.setColumns(10);

        logInButton = new JButton("LOG IN");
        logInButton.setForeground(Color.RED);
        logInButton.setBounds(60, 107, 96, 23);
        contentPane.add(logInButton);

    }

    public String adminLogIn(String username, String password) {
        if (!username.equals("admin"))
            return "Wrong username";
        if (!password.equals("admin"))
            return "Wrong password";
        return "Logged In";
    }

    public String employeeLogIn(String username, String password) {
        if (!username.equals("employee"))
            return "Wrong username";
        if (!password.equals("employee"))
            return "Wrong password";
        return "Logged In";
    }

    public void addLogInButtonListener(ActionListener actionListener) {
        this.logInButton.addActionListener(actionListener);
    }

    public String getUsername() {
        return this.logInUsername.getText();
    }

    public String getPassword() {
        return this.logInPassword.getText();
    }
}
