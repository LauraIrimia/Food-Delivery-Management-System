package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class AdministratorGUI extends JFrame {
    private JPanel contentPane;
    private JButton importButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JButton raport1Button;
    private JButton raport2Button;
    private JButton raport3Button;
    private JButton raport4Button;
    private JButton viewButton;
    public AdministratorGUI(){
        setTitle("Administrator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(350, 100, 300, 520);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        importButton = new JButton("Import products");
        importButton.setBounds(65, 21, 150, 30);
        contentPane.add(importButton);

        addButton = new JButton("Add product");
        addButton.setBounds(65, 71, 150, 30);
        contentPane.add(addButton);

        deleteButton = new JButton("Delete product");
        deleteButton.setBounds(65, 121, 150, 30);
        contentPane.add(deleteButton);

        modifyButton = new JButton("Modify product");
        modifyButton.setBounds(65, 171, 150, 30);
        contentPane.add(modifyButton);

        raport1Button = new JButton("Generate report 1");
        raport1Button.setBounds(65, 221, 150, 30);
        contentPane.add(raport1Button);

        raport2Button = new JButton("Generate report 2");
        raport2Button.setBounds(65, 271, 150, 30);
        contentPane.add(raport2Button);

        raport3Button = new JButton("Generate report 3");
        raport3Button.setBounds(65, 321, 150, 30);
        contentPane.add(raport3Button);

        raport4Button = new JButton("Generate report 4");
        raport4Button.setBounds(65, 371, 150, 30);
        contentPane.add(raport4Button);

        viewButton = new JButton("View products");
        viewButton.setBounds(65, 421, 150, 30);
        contentPane.add(viewButton);
    }
    public void addImportButtonListener(ActionListener actionListener) {
        this.importButton.addActionListener(actionListener);
    }
    public void addAddButtonListener(ActionListener actionListener) {
        this.addButton.addActionListener(actionListener);
    }
    public void addDeleteButtonListener(ActionListener actionListener) {
        this.deleteButton.addActionListener(actionListener);
    }
    public void addModifyButtonListener(ActionListener actionListener) {
        this.modifyButton.addActionListener(actionListener);
    }
    public void addRaport1ButtonListener(ActionListener actionListener) {
        this.raport1Button.addActionListener(actionListener);
    }
    public void addRaport2ButtonListener(ActionListener actionListener) {
        this.raport2Button.addActionListener(actionListener);
    }
    public void addRaport3ButtonListener(ActionListener actionListener) {
        this.raport3Button.addActionListener(actionListener);
    }
    public void addRaport4ButtonListener(ActionListener actionListener) {
        this.raport4Button.addActionListener(actionListener);
    }
    public void addViewButtonListener(ActionListener actionListener) {
        this.viewButton.addActionListener(actionListener);
    }
}
