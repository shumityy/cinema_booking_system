package models;

public class Ticket {
    private int id;
    private int seatId;
    private double price;
    private String category;

    public Ticket(int id, int seatId, double price, String category) {
        setId(id);
        setSeatId(seatId);
        setPrice(price);
        setCategory(category);
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        this.id = id;
    }

    public int getSeatId() { return seatId; }
    public void setSeatId(int seatId) {
        if (seatId <= 0) throw new IllegalArgumentException("Seat ID must be positive");
        this.seatId = seatId;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) {
        if (category == null || category.isEmpty()) throw new IllegalArgumentException("Category required");
        this.category = category;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatId=" + seatId +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
