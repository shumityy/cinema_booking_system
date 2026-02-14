package repositories;

import data.IDB;
import models.Booking;
import models.Film;
import models.Seat;
import models.User;
import repositories.interfaces.IBookingRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingRepository implements IBookingRepository {
    private final IDB db;

    public BookingRepository(IDB db) {
        this.db = db;
    }

    @Override
    public Booking getFullBooking(int id) {
        String sql = """
                SELECT b.id, b.user_username, b.film_title, s.seat_number, b.total_price
                FROM bookings b
                JOIN seats s ON s.id = b.seat_id
                WHERE b.id = ?;
                """;

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Booking(
                            rs.getInt("id"),
                            new User(rs.getString("user_username")),
                            new Film(rs.getString("film_title")),
                            new Seat(rs.getInt("seat_number")),
                            rs.getDouble("total_price")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean addBooking(Booking booking) {
        String sql = "INSERT INTO bookings(user_username, film_title, seat_id, total_price) VALUES (?,?,?,?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, booking.getUser().getUsername());
            st.setString(2, booking.getFilm().getTitle());
            st.setInt(3, booking.getSeat().getId()); // seat_id должен быть id из таблицы seats
            st.setDouble(4, booking.getTotalPrice());

            return st.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
            return false;
        }
    }
}