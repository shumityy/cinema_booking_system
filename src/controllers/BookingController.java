package controllers;

import controllers.interfaces.IBookingController;
import models.Booking;
import models.Film;
import models.User;
import repositories.interfaces.IBookingRepository;
import repositories.interfaces.ISeatRepository;

import java.util.List;


public class BookingController implements IBookingController {
    private final IBookingRepository bookingRepo;
    private final ISeatRepository seatRepo;
    public BookingController(IBookingRepository bookingRepo, ISeatRepository seatRepo) {
        this.bookingRepo = bookingRepo;
        this.seatRepo = seatRepo;
    }
    public String getFullBooking() {
        List<Booking> bookings = bookingRepo.getFullBooking();
        StringBuilder response = new StringBuilder();
        for (Booking booking : bookings) {
            response.append(booking.toString()).append("\n");
        }

        return response.toString();
    }
    public String addBooking(String user_username, String film_title, double total_price, int seatId) {
        if (!seatRepo.isSeatFree(seatId)) {
            return " Seat is already booked!";
        }
        seatRepo.bookSeat(seatId);
        Booking booking = new Booking(new User(user_username), new Film(film_title), total_price, seatId);

        boolean created = bookingRepo.addBooking(booking);

        return (created ? "Booking was created!" : "Booking creation was failed!");
    }
}
