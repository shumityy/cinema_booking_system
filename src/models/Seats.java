package models;

public class Seats extends Entity {
    private int hallId;
    private String seatNumber;
    private boolean isBooked;

    public Seats(int id, int hallId, String seatNumber, boolean isBooked) {
        this.id = id;
        this.hallId = hallId;
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
    }

    public int getHallId() {
        return hallId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }
}
