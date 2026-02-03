package menus;

import controllers.TicketController;
import controllers.interfaces.IFilmController;
import models.User;
import java.util.Scanner;


public class FilmMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final User user;
    private final IFilmController controller;

    private final TicketController ticketController;

    public FilmMenu(User user, IFilmController controller, TicketController ticketController) {
        this.user = user;
        this.controller = controller;
        this.ticketController =  ticketController;
    }

    private void menu() {
        System.out.println();
        System.out.println("Welcome, " + user.getUsername());
        System.out.println("1. Select Movies");
        if (user.isAdmin()) { System.out.println("2. Admin: Add new film");
            System.out.println("3. Logout");
        } else {
            System.out.println("2. Logout");
        }
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
                    if (user.isAdmin()) {
                        addFilmMenu();
                        break;
                    }
                    System.out.println("Logged out.");
                    return;

                case 3:
                    System.out.println("Logged out.");
                    return;

                default:
                    System.out.println("Logged out.");
                    return;
            }
        }
    }
    public void moviesMenu() {
        System.out.println("Which film would you like to watch?: ");
        String response = controller.getAllFilms();
        System.out.println(response);
        System.out.println("Enter film ID: ");
        int filmId = scanner.nextInt();

        System.out.println("Selected film: " + controller.getFilm(filmId));

        ticketController.buyTicket();
    }

    private void addFilmMenu() {
        scanner.nextLine(); // consume leftover newline

        System.out.print("Film title: ");
        String title = scanner.nextLine();

        System.out.print("Duration (minutes): ");
        int duration = scanner.nextInt();

        String result = controller.addFilm(user, title, duration);
        System.out.println(result);
    }
}