package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Observable;
import java.util.Observer;

public class EmployeeGUI extends JFrame implements Observer {
    private JPanel contentPane;
    private JLabel jLabel;
    private JTextArea jTextArea;
    private JScrollPane sp;

    public EmployeeGUI() {
        setTitle("Employee");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(350, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        jLabel = new JLabel("No orders have been placed yet...");
        jLabel.setBounds(20, 20, 250, 14);
        contentPane.add(jLabel);

        jTextArea = new JTextArea();
        jTextArea.setEditable(false);

        sp = new JScrollPane(jTextArea);
        sp.setBounds(20, 40, 350, 200);
        sp.setVisible(false);
        contentPane.add(sp);
    }

    @Override
    public void update(Observable o, Object arg) {
        jLabel.setText("The following orders have been placed: ");
        sp.setVisible(true);
        if (jTextArea.getText().isBlank()) {
            jTextArea.setText((String) arg + "\n");
        } else {
            jTextArea.setText(jTextArea.getText() + (String) arg + "\n");
        }
    }
}
