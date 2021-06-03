package PresentationLayer;

import javax.swing.*;
import javax.swing.table.TableColumn;

public class JTableGUI {
    public String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
    private JTable table;
    public JTableGUI(String[][] data) {
        JFrame tablePrd = new JFrame("Products");
        tablePrd.setResizable(false);
        table = new JTable(data, columnNames);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane sp = new JScrollPane(table);
        tablePrd.add(sp);
        tablePrd.setBounds(650, 100, 1000, 520);
        TableColumn column=table.getColumnModel().getColumn(0);
        column.setPreferredWidth(400);
        tablePrd.setVisible(true);
    }

    public JTable getTable() {
        return table;
    }
}
