import java.util.InputMismatchException;
import java.util.Scanner;

import controllers.interfaces.IBookingController;
import controllers.interfaces.IFilmController;
import controllers.interfaces.IUserController;
import menus.UserMenu;
import models.User;
import repositories.interfaces.IGenreRepository;

public class MyApplication {
    private final Scanner scanner = new Scanner(System.in);

    private final IUserController userController;
    private final IFilmController filmController;
    private final IBookingController bookingController;
    private final IGenreRepository genreRepository; // NEW

    public MyApplication(IUserController userController,
                         IFilmController filmController,
                         IBookingController bookingController,
                         IGenreRepository genreRepository) { // NEW
        this.userController = userController;
        this.filmController = filmController;
        this.bookingController = bookingController;
        this.genreRepository = genreRepository;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to Cinema !");
        System.out.println("Select option:");
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
        UserMenu menu = new UserMenu(user, filmController, bookingController, userController, genreRepository); // NEW
        menu.start();
    }
}