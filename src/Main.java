import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

        while (true) {
            int answer;
            Scanner input = new Scanner(System.in);
            manager.displayMenu();
            answer = input.nextInt();
            if (answer == 1) {
                manager.addDoctor();
            } else if (answer == 2) {
                manager.deleteDoctor();
            } else if (answer == 3) {
                manager.printDoctors();
            } else if (answer == 4) {
                manager.save();
            } else if (answer == 5) {
                manager.load();
            } else if (answer == 6) {
                System.out.println("Program has ended");
                break;
            } else {
                System.out.println("Wrong Data Type");
            }
        }
    }
}