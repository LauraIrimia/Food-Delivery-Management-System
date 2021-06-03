package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientRegisterLogInGUI extends JFrame {
    private JPanel contentPane;
    private JTextField registerUsername;
    private JPasswordField registerPassword;
    private JTextField logInUsername;
    private JPasswordField logInPassword;
    private JButton registerButton;
    private JButton logInButton;

    public ClientRegisterLogInGUI() {
        setTitle("Register & Log In");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(350, 100, 350, 220);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Register");
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setBounds(65, 20, 49, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Log In");
        lblNewLabel_1.setForeground(Color.RED);
        lblNewLabel_1.setBounds(230, 20, 49, 14);
        contentPane.add(lblNewLabel_1);

        registerUsername = new JTextField();
        registerUsername.setToolTipText("Username");
        registerUsername.setBounds(40, 45, 96, 20);
        contentPane.add(registerUsername);
        registerUsername.setColumns(10);

        registerPassword = new JPasswordField();
        registerPassword.setToolTipText("Password");
        registerPassword.setBounds(40, 76, 96, 20);
        contentPane.add(registerPassword);
        registerPassword.setColumns(10);

        registerButton = new JButton("REGISTER");
        registerButton.setForeground(Color.RED);
        registerButton.setBounds(40, 107, 96, 23);
        contentPane.add(registerButton);

        logInUsername = new JTextField();
        logInUsername.setToolTipText("Username");
        logInUsername.setBounds(200, 45, 96, 20);
        contentPane.add(logInUsername);
        logInUsername.setColumns(10);

        logInPassword = new JPasswordField();
        logInPassword.setToolTipText("Password");
        logInPassword.setBounds(200, 76, 96, 20);
        contentPane.add(logInPassword);
        logInPassword.setColumns(10);

        logInButton = new JButton("LOG IN");
        logInButton.setForeground(Color.RED);
        logInButton.setBounds(200, 107, 96, 23);
        contentPane.add(logInButton);

    }
    public void addRegisterButtonListener(ActionListener actionListener){
        this.registerButton.addActionListener(actionListener);
    }

    public void addLogInButtonListener(ActionListener actionListener){
        this.logInButton.addActionListener(actionListener);
    }

    public String getRegisterUsername() {
        return registerUsername.getText();
    }

    public String getRegisterPassword() {
        return registerPassword.getText();
    }

    public String getLogInUsername() {
        return logInUsername.getText();
    }

    public String getLogInPassword() {
        return logInPassword.getText();
    }
}
