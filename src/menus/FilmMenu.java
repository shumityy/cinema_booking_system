package menus;

import controllers.interfaces.IFilmController;
import models.User;
import java.util.Scanner;


public class FilmMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final User user;
    private final IFilmController controller;

    public FilmMenu(User user, IFilmController controller) {
        this.user = user;
        this.controller = controller;
    }

    private void menu() {
        System.out.println();
        System.out.println("Welcome, " + user.getUsername());
        System.out.println("1. Select Movies");
        System.out.println("2. Logout");
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
        System.out.println();
        System.out.println("Enter option: ");
        int option = scanner.nextInt();
        System.out.println("You bought a ticket of film" + controller.getFilm(option));
    }
}
