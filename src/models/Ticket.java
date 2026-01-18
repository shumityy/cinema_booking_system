package models;

public class Ticket extends Entity {
    private int seatId;
    private double price;

    public Ticket(int seatId, double price) {
        this.seatId = seatId;
        this.price = price;
    }

    public int getSeatId() {
        return seatId;
    }

    public double getPrice() {
        return price;
    }
}
