package menus;

import controllers.TicketController;
import controllers.interfaces.IFilmController;
import controllers.interfaces.IUserController;
import models.User;
import java.util.Scanner;


public class FilmMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final User user;
    private final IFilmController controller;

    private final TicketController ticketController;
    private final IUserController userController;

    public FilmMenu(User user, IFilmController controller, TicketController ticketController, IUserController userController) {
        this.user = user;
        this.controller = controller;
        this.ticketController =  ticketController;
        this.userController = userController;
    }

    private void menu() {
        System.out.println();
        System.out.println("Welcome, " + user.getUsername());
        System.out.println("1. Select Movies");
        if (user.isAdmin()) { System.out.println("2. Add new film");
            System.out.println("3. Update film duration");
            System.out.println("4. Delete film");
            System.out.println("5. Delete user");
            System.out.println("6. Logout");
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
                    if (user.isAdmin()) {
                        updateFilmDurationMenu();
                        break;
                    }
                    System.out.println("Logged out.");
                    return;
                case 4:
                    if (user.isAdmin()) {
                        deleteFilmMenu();
                        break;
                    }
                    System.out.println("Logged out.");
                    return;
                case 5:
                    if (user.isAdmin()) {
                        deleteUserMenu();
                        break;
                    }
                    System.out.println("Logged out.");
                    return;
                case 6:
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
    private void updateFilmDurationMenu() {
        System.out.print("Film ID: ");
        int filmId = scanner.nextInt();

        System.out.print("New duration (minutes): ");
        int duration = scanner.nextInt();

        String result = controller.updateFilmDuration(user, filmId, duration);
        System.out.println(result);
    }
    private void deleteFilmMenu() {
        System.out.print("Film ID to delete: ");
        int filmId = scanner.nextInt();

        String result = controller.deleteFilm(user, filmId);
        System.out.println(result);
    }
    private void deleteUserMenu() {
        System.out.print("Enter user ID to delete: ");
        int userId = scanner.nextInt();

        String result = userController.deleteUser(user, userId);
        System.out.println(result);
    }
}