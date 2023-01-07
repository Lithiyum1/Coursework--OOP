import javax.swing.*;
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
                adminmenu(manager);
            } else if (answer == 2) {
                manager.load();
                // create a new Skin Consultation GUI instance
                SkinConsultationGUI gui = new SkinConsultationGUI(manager);
                // set the frame properties
                gui.setTitle("Skin Consultation Centre");
                gui.setSize(800, 600);
                gui.setLocationRelativeTo(null);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setVisible(true);
            } else if (answer == 3) {
                System.out.println("Program has ended");
                break;
            } else {
                System.out.println("Wrong Data Type");
            }
        }
    }

    public static void adminmenu(WestminsterSkinConsultationManager manager){

        while (true) {
            int answer;
            Scanner input = new Scanner(System.in);
            System.out.println("Admin Panel");
            System.out.println("-------------------");
            System.out.println("1. Add doctor");
            System.out.println("2. Delete doctor");
            System.out.println("3. Print doctors");
            System.out.println("4. Save to file");
            System.out.println("5. Load from file");
            System.out.println("6. Back to Main menu ");
            System.out.println("-------------------");
            System.out.println("Enter your choice: ");
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
                break;
            } else {
                System.out.println("Wrong Data Type");
            }
        }
    }
}
