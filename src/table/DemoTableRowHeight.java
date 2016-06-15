package table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DemoTableRowHeight {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("DemoTable");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {
                "Column 1", "Column 2", "Column 3" });

        JTable table = new JTable(model);
        for (int count = 0; count < 3; count++){
            model.insertRow(count, new Object[] { count, "name", "age"});
        }
        table.setRowHeight(1, 30);

        frame.add(new JScrollPane(table));
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
