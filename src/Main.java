import controllers.interfaces.IBookingController;
import controllers.interfaces.IFilmController;
import controllers.interfaces.IUserController;
import data.PostgresDB;
import data.IDB;
import repositories.*;
import controllers.*;
import repositories.interfaces.*;

public class Main {

    public static void main(String[] args) {
        IDB db = PostgresDB.getInstance("jdbc:postgresql://localhost:5432", "postgres", "0000", "cinema");
        IUserRepository userRepo = new UserRepository(db);
        IFilmRepository filmRepo = new FilmRepository(db) {
            @Override
            public String getAllGenres() {
                return "";
            }

            @Override
            public boolean genreExists(int id) {
                return false;
            }
        };
        IBookingRepository bookingRepo = new BookingRepository(db);
        ISeatRepository seatRepo = new SeatRepository(db);
        IGenreRepository genreRepo = new GenreRepository(db);

        IUserController userController = new UserController(userRepo);
        IFilmController filmController = new FilmController(filmRepo);
        IBookingController bookingController = new BookingController(bookingRepo, seatRepo);

        MyApplication app = new MyApplication(userController, filmController, bookingController, genreRepo);

        app.start();

        db.close();
    }
}
