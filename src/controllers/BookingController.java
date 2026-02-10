package controllers;

import controllers.interfaces.IBookingController;
import models.Booking;
import models.Film;
import models.Seat;
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
    public String getFullBooking(int id) {
        Booking booking = bookingRepo.getFullBooking(id);

        return (booking == null ? "Booking was not found!" : booking.toString());
    }
    public String addBooking(String user_username, String film_title, int seat, double total_price) {
        if (!seatRepo.isSeatFree(seat)) {
            return " Seat is already booked!";
        }
        seatRepo.bookSeat(seat);
        Booking booking = new Booking(new User(user_username), new Film(film_title),new Seat(seat), total_price);

        boolean created = bookingRepo.addBooking(booking);

        return (created ? "Booking was created!" : "Booking creation was failed!");
    }
}
