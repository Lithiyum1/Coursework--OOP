import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ViewConsultationDialog extends JDialog {
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField dobField;
    private JTextField mobileField;
    private JTextField idField;
    private JTextArea notesField;
    private JTextField costField;

    public ViewConsultationDialog(JFrame parent) {
        super(parent, "View Consultation", true);

        setLayout(new BorderLayout());

        // create a panel to hold the input fields
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));

        // add the input fields
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        nameField.setEditable(false);
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Surname:"));
        surnameField = new JTextField();
        surnameField.setEditable(false);
        inputPanel.add(surnameField);
        inputPanel.add(new JLabel("Date of Birth:"));
        dobField = new JTextField();
        dobField.setEditable(false);
        inputPanel.add(dobField);
        inputPanel.add(new JLabel("Mobile Number:"));
        mobileField = new JTextField();
        mobileField.setEditable(false);
        inputPanel.add(mobileField);
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        idField.setEditable(false);
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Notes:"));
        notesField = new JTextArea();
        notesField.setEditable(false);
        inputPanel.add(notesField);
        inputPanel.add(new JLabel("Cost:"));
        costField = new JTextField();
        costField.setEditable(false);
        inputPanel.add(costField);

        add(inputPanel, BorderLayout.CENTER);

        // create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();

        // create the "Close" button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close the dialog
                setVisible(false);
            }
        });
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }

    public void setConsultation(Consultation consultation) {
        //Patient patient = consultation.getPatient();
        //nameField.setText(patient.getName());
        //surnameField.setText(patient.getSurname());
        //dobField.setText(patient.getDateOfBirth().toString());
        //mobileField.setText(patient.getMobileNumber());
        //idField.setText(String.valueOf(patient.getId()));
        notesField.setText(consultation.getNotes());
        costField.setText(Double.toString(consultation.getCost()));
    }
}