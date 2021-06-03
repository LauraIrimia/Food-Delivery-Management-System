package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class OrderGUI extends JFrame {
    private JPanel contentPane;
    private JTextField clientID;
    private JButton createOrderButton;
    public OrderGUI(){
        setTitle("Create order");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(650, 630, 350, 130);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        clientID = new JTextField();
        clientID.setToolTipText("Client ID");
        clientID.setBounds(118, 21, 96, 20);
        contentPane.add(clientID);
        clientID.setColumns(10);

        createOrderButton = new JButton("ORDER");
        createOrderButton.setBounds(118, 52, 96, 23);
        contentPane.add(createOrderButton);
    }
    public void addCreateOrderButtonListener(ActionListener actionListener) {
        this.createOrderButton.addActionListener(actionListener);
    }
    public String getClientID(){
        return clientID.getText();
    }
}
