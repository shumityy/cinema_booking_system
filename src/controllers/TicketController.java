package controllers;

import models.Ticket;
import repositories.SeatRepository;
import repositories.TicketRepository;

import java.util.Scanner;

public class TicketController {

    private final SeatRepository seatRepo;
    private final TicketRepository ticketRepo;
    private final Scanner scanner = new Scanner(System.in);

    public TicketController(SeatRepository seatRepo,
                            TicketRepository ticketRepo) {
        this.seatRepo = seatRepo;
        this.ticketRepo = ticketRepo;
    }

    public void buyTicket() {
        System.out.print("Enter seat ID: ");
        int seatId = scanner.nextInt();

        if (!seatRepo.isSeatFree(seatId)) {
            System.out.println(" Seat is already booked!");
            return;
        }

        Ticket ticket = new Ticket(seatId, 6.0);
        ticketRepo.create(ticket);
        seatRepo.bookSeat(seatId);

        System.out.println("🎟 Ticket purchased!");
        System.out.println("Seat: " + seatId);
        System.out.println("Price: $6.0");
    }
}
