package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class AddProductGUI extends JFrame {
    private JPanel contentPane;
    private JTextField title;
    private JTextField rating;
    private JTextField calories;
    private JTextField protein;
    private JTextField fat;
    private JTextField sodium;
    private JTextField price;
    private JButton addButton;

    public AddProductGUI(){
        setTitle("Add product");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(650, 630, 800, 150);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JTextField();
        title.setToolTipText("Title");
        title.setBounds(10, 21, 130, 20);
        contentPane.add(title);
        title.setColumns(10);

        rating = new JTextField();
        rating.setToolTipText("Rating");
        rating.setBounds(150, 21, 96, 20);
        contentPane.add(rating);
        rating.setColumns(10);

        calories = new JTextField();
        calories.setToolTipText("Calories");
        calories.setBounds(256, 21, 96, 20);
        contentPane.add(calories);
        calories.setColumns(10);

        protein = new JTextField();
        protein.setToolTipText("Protein");
        protein.setBounds(362, 21, 96, 20);
        contentPane.add(protein);
        protein.setColumns(10);

        fat = new JTextField();
        fat.setToolTipText("Fat");
        fat.setBounds(468, 21, 96, 20);
        contentPane.add(fat);
        fat.setColumns(10);

        sodium = new JTextField();
        sodium.setToolTipText("Sodium");
        sodium.setBounds(574, 21, 96, 20);
        contentPane.add(sodium);
        sodium.setColumns(10);

        price = new JTextField();
        price.setToolTipText("Price");
        price.setBounds(680, 21, 96, 20);
        contentPane.add(price);
        price.setColumns(10);

        addButton = new JButton("ADD");
        addButton.setBounds(362, 64, 89, 23);
        contentPane.add(addButton);
    }
    public void addAddButtonListener(ActionListener actionListener) {
        this.addButton.addActionListener(actionListener);
    }

    public String getTitle(){
        return title.getText();
    }
    public String getRating(){
        return rating.getText();
    }
    public String getCalories(){
        return calories.getText();
    }
    public String getProtein(){
        return protein.getText();
    }
    public String getFat(){
        return fat.getText();
    }
    public String getSodium(){
        return sodium.getText();
    }
    public String getPrice(){
        return price.getText();
    }

}
