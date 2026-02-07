package models;

import java.util.List;

public class Booking {
    private int id;
    private User user;
    private Film film;
    private int seatId;
    private double totalPrice;

    public Booking(int id, User user, Film film, int seatId, double totalPrice) {
        this.id = id;
        this.user = user;
        this.film = film;
        this.totalPrice = totalPrice;
    }
    public Booking(User user, Film film, double totalPrice, int seatId) {
        this.user = user;
        this.film = film;
        this.totalPrice = totalPrice;
    }
    public User getUser() {
        return user;
    }
    public Film getFilm() {
        return film;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    @Override
    public String toString() {
        return id + " " + user.getUsername() + " " + film.getTitle() + " " + totalPrice;
    }
}
