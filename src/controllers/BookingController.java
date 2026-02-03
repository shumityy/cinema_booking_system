package controllers;

import controllers.interfaces.IBookingController;
import models.Booking;
import models.Film;
import models.User;
import repositories.interfaces.IBookingRepository;


public class BookingController implements IBookingController {
    private final IBookingRepository bookingRepo;
    public BookingController(IBookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }
    public String getFullBooking(int id) {
        Booking booking = bookingRepo.getFullBooking(id);

        return (booking == null ? "Booking was not found!" : booking.toString());
    }
    public String addBooking(String user_username, String film_title, double total_price) {
        Booking booking = new Booking(new User(user_username), new Film(film_title), total_price);

        boolean created = bookingRepo.addBooking(booking);

        return (created ? "Booking was created!" : "Booking creation was failed!");
    }
}
