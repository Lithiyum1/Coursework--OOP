import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class AddConsultationDialog extends JDialog {
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField dobField;
    private JTextField mobileField;
    private JTextField idField;
    private JTextArea notesField;
    private JTextField costField;

    public AddConsultationDialog(JFrame parent) {
        super(parent, "Add Consultation", true);

        setLayout(new BorderLayout());

        // create a panel to hold the input fields
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));

        // add the input fields
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Surname:"));
        surnameField = new JTextField();
        inputPanel.add(surnameField);
        inputPanel.add(new JLabel("Date of Birth:"));
        dobField = new JTextField();
        inputPanel.add(dobField);
        inputPanel.add(new JLabel("Mobile Number:"));
        mobileField = new JTextField();
        inputPanel.add(mobileField);
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Notes:"));
        notesField = new JTextArea();
        inputPanel.add(notesField);
        inputPanel.add(new JLabel("Cost:"));
        costField = new JTextField();
        inputPanel.add(costField);

        add(inputPanel, BorderLayout.CENTER);

        // create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();

        // create the "Save" button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the consultation information from the input fields
                String name = nameField.getText();
                String surname = surnameField.getText();
                String dobString = dobField.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dateOfBirth = LocalDate.parse(dobString, formatter);
                String mobile = mobileField.getText();
                String idstr = idField.getText();
                int id = Integer.parseInt(idstr);
                String notes = notesField.getText();
                String costStr = costField.getText();
                double cost = Double.parseDouble(costStr);

                // create a new Patient instance
                Patient patient = new Patient(name, surname, dateOfBirth, mobile, id);

                // create a new Consultation instance
                //Consultation consultation = new Consultation(patient, cost, notes);

                // add the consultation to the manager
                //manager.addConsultation(consultation);

                // close the dialog
                setVisible(false);
            }
        });
        buttonPanel.add(saveButton);

        // create the "Cancel" button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close the dialog
                setVisible(false);
            }
        });
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }
}