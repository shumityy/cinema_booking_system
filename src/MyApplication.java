import controllers.IUserController;
import repositories.SeatRepository;
import repositories.TicketRepository;
import models.Seat;
import models.Ticket;

import java.util.List;
import java.util.Scanner;

public class MyApplication {

    private final IUserController userController;
    private final SeatRepository seatRepo;
    private final TicketRepository ticketRepo;

    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(
            IUserController userController,
            SeatRepository seatRepo,
            TicketRepository ticketRepo
    ) {
        this.userController = userController;
        this.seatRepo = seatRepo;
        this.ticketRepo = ticketRepo;
    }

    public void start() {

        System.out.println(" Welcome to Cinema Booking System");
        System.out.println("---------------------------------");

        // Registration
        userController.register();

        // Theatre / Hall selection (simplified)
        int hallId = 1; // fixed hall for demo (DB already has it)

        System.out.println("\n Hall " + hallId);
        System.out.println("Seats:");

        List<Seat> seats = seatRepo.getSeatsByHall(hallId);

        // Visual layout
        for (Seat seat : seats) {
            String status = seat.isBooked() ? "[X]" : "[ ]";
            System.out.printf("%2d %s  ", seat.getSeatNumber(), status);

            if (seat.getSeatNumber() % 4 == 0) {
                System.out.println();
            }
        }

        System.out.println("\n\nChoose seat number:");
        int seatNumber = scanner.nextInt();

        Seat chosenSeat = seats.stream()
                .filter(s -> s.getSeatNumber() == seatNumber)
                .findFirst()
                .orElse(null);

        if (chosenSeat == null) {
            System.out.println(" Invalid seat.");
            return;
        }

        if (chosenSeat.isBooked()) {
            System.out.println(" Seat already booked!");
            return;
        }

        Ticket ticket = new Ticket(chosenSeat.getId(), 6.0);
        ticketRepo.create(ticket);
        seatRepo.bookSeat(chosenSeat.getId());

        System.out.println("\n Ticket purchased successfully!");
        System.out.println("Hall: " + hallId);
        System.out.println("Seat: " + chosenSeat.getSeatNumber());
        System.out.println("Price: $6.0");
    }
}
