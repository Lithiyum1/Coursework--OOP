import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;


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
            Object[] rowData = {consultation.getPatient().getName(), consultation.getDoctor().getName(), consultation.getDate(),
                    consultation.getStarttime(), consultation.getEndtime(), consultation.getCost()};
            tableModel.addRow(rowData);
        }
        // create the table using the table model
        consultationTable = new JTable(tableModel);

        // show column names in the table
        JTableHeader tableHeader = consultationTable.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);

        // create a panel to hold the table and buttons
        JPanel panel = new JPanel(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        // add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(consultationTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        setSize(850, 300);
        setLocationRelativeTo(parent);
    }
}