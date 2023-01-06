import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Mains {
    public static void main(String[] args) {
        // Create a table with some sample data
        final JTable table = new JTable(new SampleTableModel());

        // Create a button that will open a new dialog when clicked
        JButton showDialogButton = new JButton("Show Dialog");
        showDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row from the table
                int selectedRow = table.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(table, "Please select a row from the table first.");
                    return;
                }

                // Get the data for the selected row
                String text = (String) table.getValueAt(selectedRow, 0);
                String imagePath = (String) table.getValueAt(selectedRow, 1);

                // Load the image from the file system
                Image image = null;
                try {
                    image = ImageIO.read(new File(imagePath));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // Create a label to display the text and image
                JLabel label = new JLabel("<html>" + text + "<br><br><img src='" + imagePath + "'></html>");

                // Show the dialog with the label
                JOptionPane.showMessageDialog(table, label);
            }
        });

        // Add the table and button to a panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(showDialogButton, BorderLayout.SOUTH);

        // Show the panel in a frame
        JFrame frame = new JFrame("Table Dialog Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    static class SampleTableModel extends AbstractTableModel {
        private final String[] columnNames = {"Text", "Image"};
        private final Object[][] data = {
                {"Sample text 1", "path/to/image1.jpg"},
                {"Sample text 2", "path/to/image2.jpg"},
                {"Sample text 3", "path/to/image3.jpg"}
        };

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return getValueAt(0, columnIndex).getClass();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    }
}
