import java.util.Scanner;
import controllers.IUserController;
import repositories.SeatRepository;
import repositories.TicketRepository;
import models.Ticket;

public class MyApplication {

    private final Scanner scanner = new Scanner(System.in);
    private final IUserController userController;
    private final SeatRepository seatRepo;
    private final TicketRepository ticketRepo;

    public MyApplication(IUserController userController,
                         SeatRepository seatRepo,
                         TicketRepository ticketRepo) {
        this.userController = userController;
        this.seatRepo = seatRepo;
        this.ticketRepo = ticketRepo;
    }

    public void start() {

        System.out.println("Welcome to Cinema Ticket Booking App");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your surname: ");
        String surname = scanner.nextLine();

        System.out.println(userController.createUser(name, surname));

        System.out.print("Enter seat id: ");
        int seatId = scanner.nextInt();

        if (!seatRepo.isSeatAvailable(seatId)) {
            System.out.println("Sorry, this seat is already booked.");
            return;
        }

        seatRepo.bookSeat(seatId);
        ticketRepo.createTicket(new Ticket(seatId, 2500));

        System.out.println("Thank you for purchasing!");
    }
}
