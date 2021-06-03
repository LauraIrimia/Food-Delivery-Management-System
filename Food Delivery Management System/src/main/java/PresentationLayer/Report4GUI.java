package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class Report4GUI extends JFrame {
    private JPanel contentPane;
    private JTextField day;
    private JButton reportButton;
    public Report4GUI(){
        setTitle("Generate report 4");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(650, 630, 350, 130);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        day = new JTextField();
        day.setToolTipText("Day");
        day.setBounds(118, 21, 96, 20);
        contentPane.add(day);
        day.setColumns(10);

        reportButton = new JButton("REPORT");
        reportButton.setBounds(118, 52, 96, 23);
        contentPane.add(reportButton);
    }
    public void addReportButtonListener(ActionListener actionListener) {
        this.reportButton.addActionListener(actionListener);
    }
    public String getDay(){
        return day.getText();
    }
}
