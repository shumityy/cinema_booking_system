import controllers.interfaces.IFilmController;
import controllers.FilmController;
import controllers.TicketController;
import controllers.UserController;
import controllers.interfaces.IUserController;
import data.PostgresDB;
import data.IDB;
import repositories.*;
import controllers.*;
import repositories.interfaces.IFilmRepository;
import repositories.interfaces.IUserRepository;

public class Main {

    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "cinema");
        IUserRepository userRepo = new UserRepository(db);
        IFilmRepository filmRepo = new FilmRepository(db);
        IUserController userController = new UserController(userRepo);
        IFilmController filmController = new FilmController(filmRepo);
        SeatRepository seatRepo = new SeatRepository(db);
        TicketRepository ticketRepo = new TicketRepository(db);
        TicketController ticketController = new TicketController(seatRepo, ticketRepo);

        MyApplication app = new MyApplication(userController, filmController, ticketController);

        app.start();

        db.close();
    }
}
