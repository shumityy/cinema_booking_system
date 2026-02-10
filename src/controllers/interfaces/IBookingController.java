package controllers.interfaces;

import models.Booking;

public interface IBookingController {
    String getFullBooking(int id);
    String addBooking(String user_username, String film_title,int seat, double total_price);
}
