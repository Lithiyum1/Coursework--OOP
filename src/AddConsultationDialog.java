import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class AddConsultationDialog extends JDialog {

    private WestminsterSkinConsultationManager manager;

    private JTable doctorTable;

    private DefaultTableModel tableModel;

    private List<Consultation> consultations;

    String encryptedimage;


    public AddConsultationDialog(JFrame parent,WestminsterSkinConsultationManager manager) {
        super(parent, "Add Consultation", true);

        this.manager = manager;

        consultations = manager.getConsultations();

        setLayout(new BorderLayout());

        JLabel Datelabel = new JLabel();
        Datelabel.setText("Pick a date , Month & Year ");

        String[] Dates={"01","02","03","04","05","06","07","08","09","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26","27","28","29","30","31"};
        JComboBox<String> comboBoxDates = new JComboBox<>(Dates);

        String[] Months={"01","02","03","04","05","06","07","08","09","10",
                "11","12"};
        JComboBox<String> comboBoxMonths = new JComboBox<>(Months);

        String[] Year ={"2022","2023"};
        JComboBox<String> comboBoxYear = new JComboBox<>(Year);

        JLabel Timelabel = new JLabel();
        Timelabel.setText("Pick a Time");

        JLabel Fromlabel = new JLabel();
        Fromlabel.setText("From");
        String[] From ={"15:00","16:00","17:00","18:00","19:00"};
        JComboBox<String> comboBoxFrom = new JComboBox<>(From);

        JLabel Tolabel = new JLabel();
        Tolabel.setText("To");
        String[] To ={"16:00","17:00","18:00","19:00","20:00"};
        JComboBox<String> comboBoxTo = new JComboBox<>(To);

        JLabel LicenceNumberlabel = new JLabel("Enter Doctor's ID Number: ");
        JTextField LicenceNumberField = new JTextField(20);

        // create a panel to hold the input fields
        JPanel inputPanel = new JPanel(new GridLayout(8, 1));
        inputPanel.setBackground(new Color(252, 228, 220));

        // add the input fields
        inputPanel.add(new JLabel("Enter Patient Details"));
        inputPanel.add(new JLabel("           "));
        inputPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Surname:"));
        JTextField surnameField = new JTextField();
        inputPanel.add(surnameField);
        inputPanel.add(new JLabel("Date of Birth:"));
        JTextField dobField = new JTextField();
        inputPanel.add(dobField);
        inputPanel.add(new JLabel("Mobile Number:"));
        JTextField mobileField = new JTextField();
        inputPanel.add(mobileField);
        inputPanel.add(new JLabel("ID:"));
        JTextField idField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Notes:"));
        JTextArea notesField = new JTextArea();
        inputPanel.add(notesField);

        JButton uploadbutton = new JButton("Upload Image");

        // Add action listener to the button
        uploadbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(AddConsultationDialog.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    // Create a byte array of file length
                    byte[] byteimage = new byte[(int) selectedFile.length()];

                    // Read file content into byte array
                    FileInputStream inputStream = null;
                    try {
                        inputStream = new FileInputStream(selectedFile);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        inputStream.read(byteimage);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    String imagestring = new String(byteimage);
                    try {
                         encryptedimage = EncryptionUtil.encrypt(imagestring);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });


        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the date and time for the consultation
                String dateString = comboBoxDates.getSelectedItem() + "-" + comboBoxMonths.getSelectedItem() + "-" + comboBoxYear.getSelectedItem();
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                Date date = null;
                try {
                    date = dateFormat.parse(dateString);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                String starTimeString = (String) comboBoxFrom.getSelectedItem();
                Date startTime = null;
                try {
                    startTime = sdf.parse(dateString+" "+starTimeString);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                String endTimeString = (String) comboBoxTo.getSelectedItem();
                Date endTime = null;
                try {
                    endTime = sdf.parse(dateString+" "+endTimeString);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                String name = nameField.getText();
                String surname = surnameField.getText();
                String dobString = dobField.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dateOfBirth = LocalDate.parse(dobString, formatter);
                String mobile = mobileField.getText();
                String idstr = idField.getText();
                int id = Integer.parseInt(idstr);
                String notes = notesField.getText();
                String encryptednote;
                try {
                     encryptednote = EncryptionUtil.encrypt(notes);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                //Calculating the Cost
                String[] parts = starTimeString.split(":");
                int start = Integer.parseInt(parts[0]);
                String[] parts1 = endTimeString.split(":");
                int end = Integer.parseInt(parts1[0]);
                int hours = end-start;

                boolean isNew = true;
                for (Patient patient:manager.getPatients()){
                    if (patient.getId() == id ){
                        isNew = false;
                    }
                }
                int cost;
                if (isNew){
                    cost = 15 * hours;
                }else {
                    cost = 25 * hours;
                }


                // create a new Patient instance
                Patient patient = new Patient(name, surname, dateOfBirth, mobile, id);

                // add the patient to the manager
                manager.addPatients(patient);

                // get the selected doctor
                String licenceNumber = (LicenceNumberField.getText());
                Doctor selectedDoctor = null;
                for (Doctor doctor : manager.getDoctors()) {
                    if (doctor.getLicenceNumber().equals(licenceNumber)) {
                        selectedDoctor = doctor;
                    }
                }

                // book a consultation with the selected doctor
                Doctor bookedDoctor = bookConsultation(selectedDoctor, patient, date, startTime, endTime,encryptednote,encryptedimage,cost );

                // show a message if the consultation was booked successfully
                if (bookedDoctor != null) {
                    JOptionPane.showMessageDialog(AddConsultationDialog.this, "Consultation booked with " + bookedDoctor.getName() + " " + bookedDoctor.getSurname());
                } else {
                    JOptionPane.showMessageDialog(AddConsultationDialog.this, "No doctors are available at the specified date and time");
                }

            }
        });



        // create a table model to hold the data for the table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Medical License Number");
        tableModel.addColumn("Specialization");

        // add the data for the doctors to the table model
        for (Doctor doctor : manager.getDoctors()) {
            Object[] rowData = {doctor.getName(), doctor.getSurname(), doctor.getLicenceNumber(), doctor.getSpecialisation()};
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
        //buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(252, 228, 220));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 0));
        topPanel.setBackground(new Color(252, 228, 220));

        JPanel comboBoxPanel1 = new JPanel();
        comboBoxPanel1.setLayout(new GridLayout(1,0));
        comboBoxPanel1.setBackground(new Color(252, 228, 220));

        comboBoxPanel1.add(Datelabel);
        comboBoxPanel1.add(comboBoxDates);
        comboBoxPanel1.add(comboBoxMonths);
        comboBoxPanel1.add(comboBoxYear);

        JPanel comboBoxPanel2 = new JPanel();
        comboBoxPanel2.setLayout(new GridLayout(1,0));
        comboBoxPanel2.setBackground(new Color(252, 228, 220));

        comboBoxPanel2.add(Timelabel);
        comboBoxPanel2.add(Fromlabel);
        comboBoxPanel2.add(comboBoxFrom);
        comboBoxPanel2.add(Tolabel);
        comboBoxPanel2.add(comboBoxTo);

        JPanel comboBoxPanel3 = new JPanel();
        comboBoxPanel3.setLayout(new GridLayout(1,0));
        comboBoxPanel3.setBackground(new Color(252, 228, 220));

        comboBoxPanel3.add(LicenceNumberlabel);
        comboBoxPanel3.add(LicenceNumberField);

        JPanel comboBoxPanel4 = new JPanel();
        comboBoxPanel4.setLayout(new GridLayout(3,0));
        comboBoxPanel4.setBackground(new Color(252, 228, 220));

        topPanel.add(comboBoxPanel1);
        topPanel.add(comboBoxPanel2);
        topPanel.add(comboBoxPanel3);
        topPanel.add(comboBoxPanel4);

        panel.add(inputPanel,BorderLayout.NORTH);
        panel.add(topPanel, BorderLayout.SOUTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.EAST);


        buttonPanel.add(addButton);
        buttonPanel.add(uploadbutton);

        setSize(800, 600);
        setLocationRelativeTo(parent);

    }

    // method to book a consultation with the selected doctor
    private Doctor bookConsultation(Doctor selectedDoctor,Patient patient, Date date,Date startTime,Date endTime,String encryptednotes, String encryptedimage, int cost ) {

        // check if the doctor is available at the specified date and time
        if (manager.isAvailable(date,startTime,endTime,selectedDoctor)) {
            // book a consultation with the selected doctor
            // create a new Consultation instance
            Consultation consultation = new Consultation(patient,selectedDoctor,date,startTime,endTime, encryptednotes, encryptedimage,cost);

            selectedDoctor.addConsultations(consultation);

            // add the consultation to the manager
            manager.addConsultation(consultation);
            return selectedDoctor;
        }else {
            // if the selected doctor is not available, try to find another doctor who is available
            for (Doctor doctor : manager.getDoctors()) {
                if (manager.isAvailable(date,startTime,endTime,doctor)) {
                    // book a consultation with the available doctor
                    // create a new Consultation instance
                    Consultation consultation = new Consultation(patient,doctor,date,startTime,endTime, encryptednotes, encryptedimage,cost);

                    // add the consultation to the manager
                    manager.addConsultation(consultation);
                    return doctor;
                }
            }
        }
        // no doctors are available
        return null;
    }

}
