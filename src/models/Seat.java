package models;

public class Seat {
    private int id;
    private int hallId;
    private int seatNumber;
    private boolean booked;

    public Seat(int seatNumber){
        this.seatNumber = seatNumber;
    }
    public Seat(int id, int hallId, int seatNumber, boolean booked) {
        this.id = id;
        this.hallId = hallId;
        this.seatNumber = seatNumber;
        this.booked = booked;
    }

    public Seat(int hallId, int seatNumber) {
        this.hallId = hallId;
        this.seatNumber = seatNumber;
        this.booked = false;
    }

    public int getHallId() {
        return hallId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public int getId() {
        return id;
    }
}