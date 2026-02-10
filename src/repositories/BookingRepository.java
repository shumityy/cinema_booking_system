package repositories;

import data.IDB;
import models.Booking;
import models.Film;
import models.Seat;
import models.User;
import repositories.interfaces.IBookingRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository implements IBookingRepository {
    private final IDB db;

    public BookingRepository(IDB db) {
        this.db = db;
    }

    public Booking getFullBooking(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = """
                        SELECT b.id, u.username, f.title, s.seat_number, b.total_price
                        FROM bookings b
                        JOIN users u ON b.user_id = u.id
                        JOIN films f ON b.film_id = f.id
                        JOIN seats s ON b.seat_id = s.id
                        WHERE b.id = ?;
                        """;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Booking(rs.getInt("id"),
                        new User(rs.getString("username")),
                        new Film(rs.getString("title")),
                        new Seat(rs.getInt("seat_number")),
                        rs.getDouble("total_price"));
            }
            return null;

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }

    public boolean addBooking(Booking booking) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO bookings(user_id, film_id, seat_id, total_price) VALUES (?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, booking.getUser().getId());
            st.setInt(2, booking.getFilm().getId());
            st.setInt(3, booking.getSeat().getId());
            st.setDouble(4, booking.getTotalPrice());

            st.execute();

            return true;
        }
        catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }
}
