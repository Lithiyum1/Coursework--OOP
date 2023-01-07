import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class ViewDetailsDialog extends JDialog {

    public ViewDetailsDialog(JDialog parent, WestminsterSkinConsultationManager manager, JTable consultationTable) {
        super(parent, "View Details", true);

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

        // Load the image from the file system
        Image image = null;
        try {
            image = ImageIO.read(new File(decryptedimage));
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
        ImageIcon icon = new ImageIcon(image.getScaledInstance(640, 480, Image.SCALE_SMOOTH));
        JLabel imagelabel = new JLabel();
        imagelabel.setIcon(icon);
        JLabel noteslabel = new JLabel(decryptednotes);

        add("West", noteslabel);
        add("Center", imagelabel);

        setSize(850, 300);
        setLocationRelativeTo(parent);
    }
}
