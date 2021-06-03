package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class DeleteProductGUI extends JFrame {
    private JPanel contentPane;
    private JButton titleButton;
    private JButton deleteButton;
    private JLabel titleString;
    public DeleteProductGUI() {
        setTitle("Delete product");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(650, 630, 400, 150);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        deleteButton = new JButton("DELETE");
        deleteButton.setBounds(151, 68, 89, 23);
        contentPane.add(deleteButton);

        titleButton = new JButton("Title");
        titleButton.setBounds(61, 20, 89, 23);
        contentPane.add(titleButton);

        titleString = new JLabel("");
        titleString.setBounds(160, 20, 200, 23);
        contentPane.add(titleString);
    }
    public void addTitleButtonListener(ActionListener actionListener) {
        this.titleButton.addActionListener(actionListener);
    }
    public void addDeleteButtonListener(ActionListener actionListener) {
        this.deleteButton.addActionListener(actionListener);
    }
    public void setTitleString(String title){
        titleString.setText(title);
    }
    public String getTitleString(){
        return titleString.getText();
    }
}
