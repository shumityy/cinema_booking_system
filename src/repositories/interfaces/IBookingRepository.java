package repositories.interfaces;

import models.Booking;

import java.util.List;

public interface IBookingRepository {
    Booking getFullBooking(int id);
    boolean addBooking(Booking booking);
}
