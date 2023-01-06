import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ViewDoctorsDialog extends JDialog {

    private WestminsterSkinConsultationManager manager;

    private JTable doctorTable;

    private DefaultTableModel tableModel;


    public ViewDoctorsDialog(JFrame parent,WestminsterSkinConsultationManager manager) {
        super(parent, "View Doctors", true);

        this.manager = manager;

        setLayout(new BorderLayout());

        // create a table model to hold the data for the table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Date of Birth");
        tableModel.addColumn("Mobile Number");
        tableModel.addColumn("Medical License Number");
        tableModel.addColumn("Specialization");

        // add the data for the doctors to the table model
        for (Doctor doctor : manager.getDoctors()) {
            Object[] rowData = {doctor.getName(), doctor.getSurname(), doctor.getDateOfBirth(),
                    doctor.getMobileNumber(), doctor.getLicenceNumber(), doctor.getSpecialisation()};
            tableModel.addRow(rowData);
        }

        // create the table using the table model
        doctorTable = new JTable(tableModel);
        doctorTable.setBackground(new Color(252, 228, 220));

        // show column names in the table
        JTableHeader tableHeader = doctorTable.getTableHeader();
        tableHeader.setBackground(new Color(252, 228, 220));
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);

        // create a panel to hold the table and buttons
        JPanel panel = new JPanel(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        // add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(doctorTable);
        scrollPane.setBackground(new Color(252, 228, 220));
        panel.add(scrollPane, BorderLayout.CENTER);

        // create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();

        // create the "Sort Alphabetically" button
        JButton sortAlphabeticallyButton = new JButton("Sort Alphabetically");
        sortAlphabeticallyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // sort the list of doctors alphabetically
                List<Doctor> doctors = new ArrayList<>(manager.getDoctors());
                doctors.sort(Comparator.comparing(Person::getSurname));

                // clear the table model
                tableModel.setRowCount(0);
                // add the doctors to the table model
                for (Doctor doctor : doctors) {
                    tableModel.addRow(new Object[]{doctor.getName(), doctor.getSurname(), doctor.getDateOfBirth(),
                            doctor.getMobileNumber(), doctor.getLicenceNumber(), doctor.getSpecialisation()});
                }
            }
        });
        buttonPanel.add(sortAlphabeticallyButton);
        buttonPanel.setBackground(new Color(252, 228, 220));

        panel.add(buttonPanel, BorderLayout.SOUTH);

        setSize(850, 300);
        setLocationRelativeTo(parent);

    }
}