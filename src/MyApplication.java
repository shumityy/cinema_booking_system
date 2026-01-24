import java.util.InputMismatchException;
import java.util.Scanner;

import controllers.interfaces.IFilmController;
import controllers.interfaces.IUserController;
import menus.FilmMenu;
import models.User;


public class MyApplication {
    private final Scanner scanner = new Scanner(System.in);

    private final IUserController userController;
    private final IFilmController filmController;

    public MyApplication(IUserController userController, IFilmController filmController) {
        this.userController = userController;
        this.filmController = filmController;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to My Application");
        System.out.println("Select option:");
        //System.out.println("1. Get all users");
        //System.out.println("2. Get user by id");
        System.out.println("1. Register");
        System.out.println("2. Login");
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
                    //case 1: getAllUsersMenu(); break;
                    //case 2: getUserByIdMenu(); break;
                    case 1: registerMenu(); break;
                    case 2: loginMenu(); break;
                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");
        }
    }

    public void getAllUsersMenu() {
        String response = userController.getAllUsers();
        System.out.println(response);
    }

    public void getUserByIdMenu() {
        System.out.println("Please enter id");

        int id = scanner.nextInt();
        String response = userController.getUser(id);
        System.out.println(response);
    }

    public void registerMenu() {
        System.out.println("Please enter username");
        String username = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();

        String response = userController.register(username, password);
        System.out.println(response);
    }
    public void loginMenu() {
        System.out.println("Please enter username:");
        String username = scanner.next();

        System.out.println("Please enter password:");
        String password = scanner.next();

        User user = userController.login(username, password);

        if (user == null) {
            System.out.println("Login failed. User not found.");
            return;
        }

        System.out.println("Login successful!");
        FilmMenu filmMenu = new FilmMenu(user, filmController);
        filmMenu.start();
    }
}
