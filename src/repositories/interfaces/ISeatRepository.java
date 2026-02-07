package repositories.interfaces;

import models.Seat;

import java.util.List;

public interface ISeatRepository {
    boolean isSeatFree(int seatId);
    void bookSeat(int seatId);
    List<Seat> getSeatsByHall(int hallId);
}
