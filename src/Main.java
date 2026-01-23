import controllers.*;
import data.*;
import repositories.*;

public class Main {

    public static void main(String[] args) {

        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "password",
                "cinema"
        );

        IUserRepository userRepo = new UserRepository(db);
        SeatRepository seatRepo = new SeatRepository(db);
        TicketRepository ticketRepo = new TicketRepository(db);

        UserController userController = new UserController(userRepo);
        TicketController ticketController =
                new TicketController(seatRepo, ticketRepo);

        System.out.println("🎬 Welcome to Cinema Booking System");

        userController.register();
        ticketController.buyTicket();

        db.close();
    }
}
