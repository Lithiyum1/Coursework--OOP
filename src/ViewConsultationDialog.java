import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


class ViewConsultationDialog extends JDialog {
    private WestminsterSkinConsultationManager manager;

    private JTable consultationTable;

    private DefaultTableModel tableModel;

    public ViewConsultationDialog(JFrame parent,WestminsterSkinConsultationManager manager) {
        super(parent, "View Consultation", true);

        this.manager = manager;

        setLayout(new BorderLayout());

        // create a table model to hold the data for the table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Patient's First Name");
        tableModel.addColumn("Doctor's First Name");
        tableModel.addColumn("Date");
        tableModel.addColumn("Start Time");
        tableModel.addColumn("End Time");
        tableModel.addColumn("Cost");

        // add the data for the doctors to the table model
        for (Consultation consultation : manager.getConsultations()) {

            Date dateObject = consultation.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(dateObject);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date startTimeObject = consultation.getStarttime();
            String formattedStartTime = timeFormat.format(startTimeObject);
            Date endTimeObject = consultation.getStarttime();
            String formattedEndTime = timeFormat.format(endTimeObject);

            Object[] rowData = {consultation.getPatient().getName(), consultation.getDoctor().getName(), formattedDate,
                    formattedStartTime, formattedEndTime, consultation.getCost()};
            tableModel.addRow(rowData);
        }
        // create the table using the table model
        consultationTable = new JTable(tableModel);
        consultationTable.setBackground(new Color(252, 228, 220));

        // show column names in the table
        JTableHeader tableHeader = consultationTable.getTableHeader();
        tableHeader.setBackground(new Color(252, 228, 220));
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);

        // create a panel to hold the table and buttons
        JPanel panel = new JPanel(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        // add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(consultationTable);
        scrollPane.setBackground(new Color(252, 228, 220));
        panel.add(scrollPane, BorderLayout.CENTER);

        // create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();

        // create the "View Notes & Images " button
        JButton ViewButton = new JButton("View Notes & Images");
        ViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row from the table
                int selectedRow = consultationTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(consultationTable, "Please select a row from the table first.");
                    return;
                }

                //Getting the image and note
                Consultation consultation = manager.getConsultations().get(selectedRow);
                String encryptedimage = consultation.getEncryptedImage();
                String decryptedimage = null;
                try {
                    decryptedimage = EncryptionUtil.decrypt(encryptedimage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                byte[] bytes = decryptedimage.getBytes();
                /*BufferedImage image = null;
                try {
                    image = ImageIO.read(new ByteArrayInputStream(bytes));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }*/
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream("sample.jpg");
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    outputStream.write(bytes);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // Load the image from the file system
                Image image = null;
                try {
                    image = ImageIO.read(new File("sample.jpg"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                String encryptednotes = consultation.getEncryptedNotes();
                String decryptednotes = null;
                try {
                    decryptednotes = EncryptionUtil.decrypt(encryptednotes);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                // Create a label to display the text and image
                ImageIcon icon = new ImageIcon(image);
                JLabel imagelabel = new JLabel(icon);
                JLabel noteslabel = new JLabel(decryptednotes);

                // Show the dialog with the label
                JOptionPane.showMessageDialog(imagelabel, noteslabel);

            }});

        buttonPanel.add(ViewButton);
        buttonPanel.setBackground(new Color(252, 228, 220));
        panel.add(buttonPanel,BorderLayout.SOUTH);

        setSize(850, 300);
        setLocationRelativeTo(parent);
    }
}