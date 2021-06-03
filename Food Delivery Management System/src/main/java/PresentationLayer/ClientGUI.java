package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private JPanel contentPane;
    private JButton searchButton;
    private JButton orderButton;
    private JButton viewButton;
    public ClientGUI(){
        setTitle("Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(350, 100, 300, 220);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        searchButton = new JButton("Search products");
        searchButton.setBounds(65, 21, 150, 30);
        contentPane.add(searchButton);

        viewButton = new JButton("View products");
        viewButton.setBounds(65, 71, 150, 30);
        contentPane.add(viewButton);

        orderButton = new JButton("Create order");
        orderButton.setBounds(65, 121, 150, 30);
        contentPane.add(orderButton);

    }
    public void addSearchButtonListener(ActionListener actionListener) {
        this.searchButton.addActionListener(actionListener);
    }
    public void addOrderButtonListener(ActionListener actionListener) {
        this.orderButton.addActionListener(actionListener);
    }
    public void addViewButtonListener(ActionListener actionListener) {
        this.viewButton.addActionListener(actionListener);
    }
}
