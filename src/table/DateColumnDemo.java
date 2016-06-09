package table;

import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.table.DatePickerCellEditor;

public class DateColumnDemo {

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("DateColumnDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTable table = new JTable(new Object[][] { { "1", new Date() } },
                new Object[] { "Id", "Time" });

        TableColumn dateColumn = table.getColumnModel().getColumn(1);
        dateColumn.setCellEditor(new DatePickerCellEditor());

        JScrollPane scrollPane = new JScrollPane(table); 

        frame.add(scrollPane);
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
