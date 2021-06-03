package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class RadioButtonGUI extends JFrame {
    private JPanel contentPane;
    public JRadioButton adminRadioButton;
    public JRadioButton employeeRadioButton;
    public JRadioButton clientRadioButton;

    public RadioButtonGUI() {
        setTitle("Users");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 250, 220);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        adminRadioButton = new JRadioButton("Administrator");
        adminRadioButton.setBounds(70, 44, 111, 23);
        contentPane.add(adminRadioButton);

        employeeRadioButton = new JRadioButton("Employee");
        employeeRadioButton.setBounds(70, 107, 111, 23);
        contentPane.add(employeeRadioButton);

        clientRadioButton = new JRadioButton("Client");
        clientRadioButton.setBounds(70, 75, 111, 23);
        contentPane.add(clientRadioButton);
    }

    public void addAdminButtonListener(ActionListener actionListener) {
        this.adminRadioButton.addActionListener(actionListener);
    }

    public void addClientButtonListener(ActionListener actionListener) {
        this.clientRadioButton.addActionListener(actionListener);
    }

    public void addEmployeeButtonListener(ActionListener actionListener) {
        this.employeeRadioButton.addActionListener(actionListener);
    }
}
