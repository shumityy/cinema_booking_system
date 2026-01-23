package models;

public class Hall extends Entity {
    private int seats;

    public Hall(int seats){
        this.seats = seats;
    }
    public Hall(int id, int seats){
        this(seats);
        this.id = id;
    }
}
