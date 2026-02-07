package menus;

import controllers.interfaces.IBookingController;
import controllers.interfaces.IFilmController;
import models.User;
import java.util.Scanner;


public class UserMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final User user;
    private final IFilmController controller;
    private final IBookingController bookingController;

    public UserMenu(User user, IFilmController controller, IBookingController bookingController) {
        this.user = user;
        this.controller = controller;
        this.bookingController = bookingController;
    }

    private void menu() {
        System.out.println();
        System.out.println("Welcome, " + user.getUsername());
        System.out.println("1. Select Films");
        System.out.println("2. View Your Booking");
        System.out.println("3. Logout");
        System.out.print("Enter option: ");
    }

    public void start() {
        while (true) {
            menu();
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    moviesMenu();
                    break;
                case 2:
                    bookingMenu();
                    break;
                default:
                    System.out.println("Logged out.");
                    return;
            }
        }
    }
    public void moviesMenu() {
        System.out.println("Which film would you like to watch?: ");
        String response1 = controller.getAllFilms();
        System.out.println(response1);
        System.out.println();
        System.out.println("Enter option: ");
        int option = scanner.nextInt();
        System.out.println("What seat you want (1-64): ");
        int seat =  scanner.nextInt();
        String response2 = bookingController.addBooking(user.getUsername(), controller.getFilm(option), 500);
        System.out.println(response2);
        System.out.println();
    }
    public void bookingMenu() {
        String response = bookingController.getFullBooking();
        System.out.println(response);
    }
}
