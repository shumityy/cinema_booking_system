package controllers;

import controllers.interfaces.IBookingController;
import models.Booking;
import models.Film;
import models.User;
import repositories.interfaces.IBookingRepository;

import java.util.List;


public class BookingController implements IBookingController {
    private final IBookingRepository bookingRepo;
    public BookingController(IBookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }
    public String getFullBooking() {
        List<Booking> bookings = bookingRepo.getFullBooking();
        StringBuilder response = new StringBuilder();
        for (Booking booking : bookings) {
            response.append(booking.toString()).append("\n");
        }

        return response.toString();
    }
    public String addBooking(String user_username, String film_title, double total_price) {
        Booking booking = new Booking(new User(user_username), new Film(film_title), total_price);

        boolean created = bookingRepo.addBooking(booking);

        return (created ? "Booking was created!" : "Booking creation was failed!");
    }
}
