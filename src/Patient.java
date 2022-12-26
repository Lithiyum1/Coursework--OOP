import java.io.Serializable;
import java.time.LocalDate;

public class Patient extends Person implements Serializable {
    private int id;

    public Patient(String name, String surname, LocalDate dateOfBirth, String mobileNumber, int id) {
        super(name, surname, dateOfBirth, mobileNumber);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
