package menus;

import controllers.interfaces.IBookingController;
import controllers.interfaces.IFilmController;
import controllers.interfaces.IUserController;
import models.User;
import repositories.interfaces.IGenreRepository;

import java.util.Scanner;

public class UserMenu {
    private final Scanner scanner = new Scanner(System.in);

    private final User user;
    private final IFilmController filmController;
    private final IBookingController bookingController;
    private final IUserController userController;
    private final IGenreRepository genreRepository;

    public UserMenu(User user,
                    IFilmController filmController,
                    IBookingController bookingController,
                    IUserController userController,
                    IGenreRepository genreRepository) {
        this.user = user;
        this.filmController = filmController;
        this.bookingController = bookingController;
        this.userController = userController;
        this.genreRepository = genreRepository;
    }

    private void menu() {
        System.out.println();
        System.out.println("Welcome, " + user.getUsername());
        System.out.println("1. Select Movies");
        System.out.println("2. View Bookings");
        if (user.isAdmin()) {
            System.out.println("3. Add new film");
            System.out.println("4. Update film duration");
            System.out.println("5. Delete film");
            System.out.println("6. Delete user");
            System.out.println("7. Logout");
        } else {
            System.out.println("3. Logout");
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
                    bookingMenu();
                    break;
                case 3:
                    if (user.isAdmin()) { addFilmMenu(); break; }
                    System.out.println("Logged out.");
                    return;
                case 4:
                    if (user.isAdmin()) { updateFilmDurationMenu(); break; }
                    System.out.println("Logged out.");
                    return;
                case 5:
                    if (user.isAdmin()) { deleteFilmMenu(); break; }
                    System.out.println("Logged out.");
                    return;
                case 6:
                    if (user.isAdmin()) { deleteUserMenu(); break; }
                    System.out.println("Logged out.");
                    return;
                case 7:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Logged out.");
                    return;
            }
        }
    }

    private void moviesMenu() {
        System.out.println(filmController.getAllFilms());

        System.out.print("Film ID: ");
        int filmId = scanner.nextInt();

        System.out.print("Seat: ");
        int seat = scanner.nextInt();

        System.out.println(
                bookingController.addBooking(
                        user.getUsername(),
                        filmController.getFilm(filmId),
                        seat,
                        500
                )
        );
    }

    private void bookingMenu() {
        System.out.print("Booking ID: ");
        int id = scanner.nextInt();
        System.out.println(bookingController.getFullBooking(id));
    }

    private void addFilmMenu() {
        scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Duration: ");
        int duration = scanner.nextInt();

        System.out.println(genreRepository.getAllGenres());

        System.out.print("Genre ID: ");
        int genreId = scanner.nextInt();
if (!genreRepository.existsById(genreId)) {
        System.out.println("Invalid genre id");
            return;
                    }

                    System.out.println(filmController.addFilm(user, title, duration, genreId));
        }

private void updateFilmDurationMenu() {
    System.out.print("Film ID: ");
    int filmId = scanner.nextInt();

    System.out.print("New duration: ");
    int duration = scanner.nextInt();

    System.out.println(filmController.updateFilmDuration(user, filmId, duration));
}

private void deleteFilmMenu() {
    System.out.print("Film ID: ");
    int filmId = scanner.nextInt();

    System.out.println(filmController.deleteFilm(user, filmId));
}

private void deleteUserMenu() {
    System.out.print("User ID: ");
    int userId = scanner.nextInt();

    System.out.println(userController.deleteUser(user, userId));
}
}