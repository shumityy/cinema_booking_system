import java.util.InputMismatchException;
import java.util.Scanner;
import controllers.IUserController;

public class MyApplication {
    private final Scanner scanner = new Scanner(System.in);

    private final IUserController controller;

    public MyApplication(IUserController controller) {
        this.controller = controller;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to Cinema Ticket Booking App");
        System.out.println("Select option:");
        System.out.println("1. Log in");
        System.out.println("2. Register");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (1-2): ");
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1: loginMenu(); break;
                    case 2: registerUserMenu(); break;
                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                scanner.nextLine(); // to ignore incorrect input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");
        }
    }

    public void loginMenu() {
        String response = controller.getAllUsers();
        System.out.println(response);
    }


    public void registerUserMenu() {
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter surname");
        String surname = scanner.next();
        System.out.println("Please enter email");
        String email = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();

        String response = controller.createUser(name, surname, email, password);
        System.out.println(response);
    }
}