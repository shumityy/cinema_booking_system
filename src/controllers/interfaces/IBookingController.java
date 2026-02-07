package controllers.interfaces;

import models.Booking;

public interface IBookingController {
    String getFullBooking();
    String addBooking(String user_username, String film_title, double total_price, int seatId);
}
