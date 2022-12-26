import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SkinConsultationGUI extends JFrame {
    private WestminsterSkinConsultationManager manager;
    private JTable doctorTable;
    private DefaultTableModel tableModel;

    public SkinConsultationGUI(WestminsterSkinConsultationManager manager) {
        this.manager = manager;

        setLayout(new BorderLayout());

        // create a panel to hold the table and buttons
        JPanel panel = new JPanel(new BorderLayout());

        // create the table to display the doctors
        doctorTable = new JTable();
        tableModel = new DefaultTableModel();
        doctorTable.setModel(tableModel);
        panel.add(doctorTable, BorderLayout.CENTER);

        // create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();

        // create the "Add Consultation" button
        JButton addConsultationButton = new JButton("Add Consultation");
        addConsultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show the add consultation dialog
                AddConsultationDialog dialog = new AddConsultationDialog(SkinConsultationGUI.this);
                dialog.setVisible(true);
            }
        });
        buttonPanel.add(addConsultationButton);

        // create the "View Consultation" button
        JButton viewConsultationButton = new JButton("View Consultation");
        viewConsultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show the view consultation dialog
                ViewConsultationDialog dialog = new ViewConsultationDialog(SkinConsultationGUI.this);
                dialog.setVisible(true);
            }
        });
        buttonPanel.add(viewConsultationButton);

        // create the "Sort Alphabetically" button
        JButton sortAlphabeticallyButton = new JButton("Sort Alphabetically");
        sortAlphabeticallyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // sort the list of doctors alphabetically
                manager.sortDoctorsAlphabetically();

                // update the table model
                updateTableModel();
            }
        });
        buttonPanel.add(sortAlphabeticallyButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        // update the table model
        updateTableModel();
    }

    public void updateTableModel() {
        // clear the table model
        tableModel.setRowCount(0);

        // get the list of doctors
        List<Doctor> doctors = manager.getDoctors();

        // add the doctors to the table model
        for (Doctor doctor : doctors) {
            tableModel.addRow(new Object[] {doctor.getName(), doctor.getSurname(), doctor.getSpecialisation()});
        }
    }

    public static void main(String[] args) {
        // create a new Skin Consultation Manager instance
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

        // create a new Skin Consultation GUI instance
        SkinConsultationGUI gui = new SkinConsultationGUI(manager);

        // set the frame properties
        gui.setTitle("Skin Consultation Centre");
        gui.setSize(800, 600);
        gui.setLocationRelativeTo(null);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}









