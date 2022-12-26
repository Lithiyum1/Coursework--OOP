import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation {

    private Patient patient;  // reference to the patient who has booked the consultation
    private Doctor doctor;  // reference to the doctor who will conduct the consultation
    private LocalDate date;
    private LocalTime time;
    private int cost;
    private String notes;

    public Consultation(Patient patient, Doctor doctor,LocalDate date, LocalTime time, int cost, String notes) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.cost = cost;
        this.notes = notes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
