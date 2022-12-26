import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    private List<Doctor> doctors;

    public WestminsterSkinConsultationManager() {
        this.doctors = new ArrayList<>();
    }

    public void displayMenu() {
        System.out.println("Westminster Skin Consultation Manager");
        System.out.println("-------------------");
        System.out.println("1. Add doctor");
        System.out.println("2. Delete doctor");
        System.out.println("3. Print doctors");
        System.out.println("4. Save");
        System.out.println("5. Load");
        System.out.println("6. Quit the Program");
        System.out.println("-------------------");
        System.out.println("Enter your choice: ");
    }

    @Override
    public void addDoctor() {
        if (this.doctors.size() >= 10) {
            System.out.println("Cannot add more doctors. The maximum capacity of 10 doctors has been reached.");
            return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Enter doctor's name: ");
        String name = in.nextLine();
        System.out.println("Enter doctor's surname: ");
        String surname = in.nextLine();
        System.out.println("Enter doctor's date of birth (dd/mm/yyyy): ");
        String dobString = in.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateOfBirth = null;
        try {
            dateOfBirth = LocalDate.parse(dobString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format");
        }
        System.out.println("Enter doctor's mobile number: ");
        String mobileNumber = in.nextLine();
        System.out.println("Enter doctor's medical license number: ");
        String licenseNumber = in.nextLine();
        System.out.println("Enter doctor's specialization: ");
        String specialisation = in.nextLine();
        Doctor doctor = new Doctor(name, surname, dateOfBirth, mobileNumber, licenseNumber, specialisation);
        this.doctors.add(doctor);
        System.out.println("Doctor added successfully.");

    }

    @Override
    public void deleteDoctor() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter doctor's medical license number: ");
        String licenseNumber = in.nextLine();

        Doctor doctorToDelete = null;
        for (Doctor doctor : this.doctors) {
            if (doctor.getLicenceNumber().equals(licenseNumber)) {
                doctorToDelete = doctor;
                break;
            }
        }

        if (doctorToDelete == null) {
            System.out.println("Doctor with medical license number " + licenseNumber + " not found.");
            return;
        }

        this.doctors.remove(doctorToDelete);
        System.out.println("Doctor deleted successfully: " + doctorToDelete);
        System.out.println("Total number of doctors in the centre: " + this.doctors.size());

    }

    @Override
    public void printDoctors() {
        this.doctors.sort(Comparator.comparing(Person::getSurname));
        for (Doctor doctor : this.doctors) {
            System.out.println(doctor.getName() + " " + doctor.getSurname() + " (" + doctor.getDateOfBirth() + ") - " + doctor.getMobileNumber() + " - " + doctor.getLicenceNumber() + " - " + doctor.getSpecialisation());
        }

    }

    @Override
    public void save() {
        try (
                FileOutputStream fos = new FileOutputStream("doctors.txt"))
        {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(this.doctors);
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    @Override
    public void load() {
        try {
                FileInputStream fis = new FileInputStream("doctors.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);

                doctors = (List<Doctor>) ois.readObject();

                ois.close();
                fis.close();
        } catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void sortDoctorsAlphabetically() {
        // use a comparator to sort the list of doctors alphabetically by surname
        Collections.sort(doctors, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor d1, Doctor d2) {
                return d1.getSurname().compareTo(d2.getSurname());
            }
        });
    }

}
