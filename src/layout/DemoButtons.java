import java.awt.*;
import javax.swing.*;

/**
 * A sample that illustrates how to combine GridLayout and GridBagLayout to size buttons properly in a menu panel. 
 * 
 * @see http://stackoverflow.com/a/15687011/1048330 for original context
 * 
*/
public class DemoButtons {
    public DemoButtons() {
        final JFrame frame = new JFrame("Demo buttons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.add(new JButton("Export do SVG"));
        buttonPanel.add(new JButton("Export do PNG"));
        buttonPanel.add(new JButton("Tisk..."));

        JPanel east = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        east.add(buttonPanel, gbc);

        JPanel center = new JPanel(){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(200, 200);
            }
        };
        center.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        frame.add(east, BorderLayout.EAST);
        frame.add(center);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DemoButtons();
            }
        });
    }
} 
