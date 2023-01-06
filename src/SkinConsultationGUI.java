import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SkinConsultationGUI extends JFrame {

    private WestminsterSkinConsultationManager manager;

    public SkinConsultationGUI(WestminsterSkinConsultationManager manager) {

        this.manager = manager;

        setLayout(new BorderLayout());

        // create a panel to hold the table and buttons
        JPanel panel = new JPanel(new BorderLayout());

        // create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();

        // create the "View Doctors" button
        JButton viewDoctorsButton = new JButton("View Doctors");
        viewDoctorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show the view doctors dialog
                ViewDoctorsDialog dialog = new ViewDoctorsDialog(SkinConsultationGUI.this,manager);
                dialog.setVisible(true);
            }
        });
        buttonPanel.add(viewDoctorsButton);

        // create the "Add Consultation" button
        JButton addConsultationButton = new JButton("Add Consultation");
        addConsultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show the add consultation dialog
                AddConsultationDialog dialog = new AddConsultationDialog(SkinConsultationGUI.this,manager);
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
                ViewConsultationDialog dialog = new ViewConsultationDialog(SkinConsultationGUI.this,manager);
                dialog.setVisible(true);
            }
        });
        buttonPanel.add(viewConsultationButton);
        buttonPanel.setBackground(new Color(252, 228, 220));

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
        ImageIcon icon = new ImageIcon("image.jpg");
        JLabel label = new JLabel(icon);
        panel.add(label,BorderLayout.CENTER);
    }

}









