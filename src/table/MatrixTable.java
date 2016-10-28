import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * A sample that illustrates an implementation of a table model that wraps Apache's RealMatrix. 
 * 
 * @see http://stackoverflow.com/a/25391023/1048330 for original context
 * 
*/

public class MatrixTable extends JPanel{
    private MyTableModel model;
    private JTable table;
    private JComboBox rowsCombo;
    private JComboBox columnsCombo;

    public MatrixTable() {
        setLayout(new BorderLayout(5, 5));

        model = new MyTableModel(2, 2);

        JPanel buttonsPanel = new JPanel();
        Integer[] test = {1, 2, 3, 4, 5};
        rowsCombo = new JComboBox(test);
        rowsCombo.setSelectedItem(2);
        rowsCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRows(((Integer)rowsCombo.getSelectedItem()));
            }
        });

        buttonsPanel.add(new JLabel("rows"));
        buttonsPanel.add(rowsCombo);

        columnsCombo = new JComboBox(test);
        columnsCombo.setSelectedItem(2);
        columnsCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setColumns(((Integer)columnsCombo.getSelectedItem()));
            }
        });

        buttonsPanel.add(new JLabel("columns"));
        buttonsPanel.add(columnsCombo);
        add(buttonsPanel, BorderLayout.NORTH);

        JTable table = new JTable();
        table.setModel(model);
        add(new JScrollPane(table));
    }

    class MyTableModel extends AbstractTableModel {
        private RealMatrix matrix;

        public MyTableModel(int rows, int columns) {
            matrix = new Array2DRowRealMatrix(rows, columns);
        }

        public void setRows(int rows) {
            matrix = new Array2DRowRealMatrix(rows, matrix.getColumnDimension());
            fireTableStructureChanged();
        }

        public void setColumns(int columns) {
            matrix = new Array2DRowRealMatrix(matrix.getRowDimension(), columns);
            fireTableStructureChanged();
        }

        @Override
        public int getColumnCount() {
            return matrix.getColumnDimension();
        }

        @Override
        public int getRowCount() {
            return matrix.getRowDimension();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return Double.class;
        }

        @Override
        public void setValueAt(Object value, int row, int column) {
            matrix.setEntry(row, column, (double)value);
        }

        @Override
        public Object getValueAt(int row, int column) {
            return matrix.getEntry(row, column);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 200);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {   
            public void run() {   
                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationByPlatform(true);

                MatrixTable panel = new MatrixTable();
                frame.add(panel);
                frame.pack();

                frame.setVisible(true);
            }
        });
    }
}
