import java.util.*;

public class Consultation {

    private Patient patient;  // reference to the patient who has booked the consultation
    private Doctor doctor;  // reference to the doctor who will conduct the consultation
    private Date date;
    private Date starttime;
    private Date endtime;
    private String encryptedNotes;
    private String encryptedImage;
    private int cost;

    public Consultation(Patient patient, Doctor doctor,Date date, Date starttime,Date endtime, String encryptednote, String encryptedImage, int cost) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.starttime = starttime;
        this.endtime = endtime;
        this.encryptedNotes = encryptednote;
        this.encryptedImage = encryptedImage;
        this.cost = cost;
    }

    public Patient getPatient(){return patient;}
    public Doctor getDoctor(){return doctor;}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStarttime() {
        return starttime;
    }
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime(){
        return endtime;
    }
    public void setEndtime(Date endtime){ this.endtime = endtime;}

    public String getEncryptedNotes() {
        return encryptedNotes;
    }

    public void setEncryptedNotes(String encryptedNotes) {this.encryptedNotes = encryptedNotes;}

    public String getEncryptedImage() {
        return encryptedImage;
    }

    public void setEncryptedImage(String encryptedImage) {
        this.encryptedImage = encryptedImage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
