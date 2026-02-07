import controllers.interfaces.IBookingController;
import controllers.interfaces.IFilmController;
import controllers.interfaces.IUserController;
import data.PostgresDB;
import data.IDB;
import repositories.*;
import controllers.*;
import repositories.interfaces.IBookingRepository;
import repositories.interfaces.IFilmRepository;
import repositories.interfaces.IUserRepository;

public class Main {

    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "cinema");
        IUserRepository userRepo = new UserRepository(db);
        IFilmRepository filmRepo = new FilmRepository(db);
        IBookingRepository bookingRepo = new BookingRepository(db);
        IUserController userController = new UserController(userRepo);
        IFilmController filmController = new FilmController(filmRepo);
        IBookingController bookingController = new BookingController(bookingRepo);

        MyApplication app = new MyApplication(userController, filmController, bookingController);

        app.start();

        db.close();
    }
}
