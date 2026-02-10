package models;


public class Booking {
    private int id;
    private User user;
    private Film film;
    private Seat seat;
    private double totalPrice;

    public Booking(int id, User user, Film film, Seat seat, double totalPrice) {
        this.id = id;
        this.user = user;
        this.film = film;
        this.seat = seat;
        this.totalPrice = totalPrice;
    }
    public Booking(User user, Film film, Seat seat, double totalPrice) {
        this.user = user;
        this.film = film;
        this.seat = seat;
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
    public Seat getSeat() { return seat; }
    @Override
    public String toString() {
        return id + " " + user.getUsername() + " " + film.getTitle() + " " + totalPrice;
    }
}
