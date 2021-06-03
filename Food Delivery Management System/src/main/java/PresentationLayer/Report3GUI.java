package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class Report3GUI extends JFrame {
    private JPanel contentPane;
    private JTextField numberOfTimes;
    private JButton reportButton;
    private JTextField amount;
    public Report3GUI(){
        setTitle("Generate report 3");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(650, 630, 350, 130);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        numberOfTimes = new JTextField();
        numberOfTimes.setToolTipText("Number of times");
        numberOfTimes.setBounds(53, 21, 96, 20);
        contentPane.add(numberOfTimes);
        numberOfTimes.setColumns(10);

        reportButton = new JButton("REPORT");
        reportButton.setBounds(118, 52, 96, 23);
        contentPane.add(reportButton);

        amount = new JTextField();
        amount.setToolTipText("Amount");
        amount.setBounds(184, 21, 96, 20);
        contentPane.add(amount);
        amount.setColumns(10);
    }
    public void addReportButtonListener(ActionListener actionListener) {
        this.reportButton.addActionListener(actionListener);
    }
    public String getNumberOfTimes(){
        return numberOfTimes.getText();
    }
    public String getAmount(){
        return amount.getText();
    }
}
