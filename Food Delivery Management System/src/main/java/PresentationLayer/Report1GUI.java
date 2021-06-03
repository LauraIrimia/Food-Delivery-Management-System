package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class Report1GUI extends JFrame {
    private JPanel contentPane;
    private JTextField startHour;
    private JTextField endHour;
    private JButton reportButton;
    public Report1GUI(){
        setTitle("Generate report 1");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(650, 630, 350, 170);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        startHour = new JTextField();
        startHour.setToolTipText("Start hour");
        startHour.setBounds(116, 21, 96, 20);
        contentPane.add(startHour);
        startHour.setColumns(10);

        endHour = new JTextField();
        endHour.setToolTipText("End hour");
        endHour.setBounds(116, 60, 96, 20);
        contentPane.add(endHour);
        endHour.setColumns(10);

        reportButton = new JButton("REPORT");
        reportButton.setBounds(116, 99, 96, 23);
        contentPane.add(reportButton);
    }
    public void addReportButtonListener(ActionListener actionListener) {
        this.reportButton.addActionListener(actionListener);
    }
    public String getStartHour(){
        return startHour.getText();
    }
    public String getEndHour(){
        return endHour.getText();
    }
}
