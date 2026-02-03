package repositories.interfaces;

import models.Booking;

import java.util.List;

public interface IBookingRepository {
    List<Booking> getFullBooking();
    boolean addBooking(Booking booking);
}
