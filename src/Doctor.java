import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person implements Serializable {
    private String licenceNumber;
    private String specialisation;

    List<Consultation> consultations = new ArrayList<>();

    private static final long serialVersionUID = 8537993204036820764L;

    public Doctor(){

    }

    public Doctor(String name, String surname, LocalDate dateOfBirth, String mobileNumber, String licenceNumber, String specialisation) {
        super(name, surname, dateOfBirth, mobileNumber);
        this.licenceNumber = licenceNumber;
        this.specialisation = specialisation;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        out.writeObject(licenceNumber);
        out.writeObject(specialisation);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        licenceNumber = (String) in.readObject();
        specialisation = (String) in.readObject();
        consultations = new ArrayList<>();
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void addConsultations(Consultation consultation) {
        consultations.add(consultation);

    }
}
