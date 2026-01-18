import data.PostgresDB;
import data.IDB;
import repositories.*;
import controllers.*;

public class Main {
    public static void main(String[] args) {

        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "somedb"
        );

        IUserRepository userRepo = new UserRepository(db);
        IUserController userController = new UserController(userRepo);

        SeatRepository seatRepo = new SeatRepository(db);
        TicketRepository ticketRepo = new TicketRepository(db);

        MyApplication app = new MyApplication(
                userController,
                seatRepo,
                ticketRepo
        );

        app.start();
        db.close();
    }
}
