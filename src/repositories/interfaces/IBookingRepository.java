package repositories.interfaces;

import models.Booking;

public interface IBookingRepository {
    Booking getFullBooking(int id);
    boolean addBooking(Booking booking);
}
