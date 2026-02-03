package repositories;

import data.IDB;
import models.Booking;
import models.Film;
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

    public Booking getFullBooking(int booking_id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = """
                        SELECT b.id, u.username, f.title, b.total_price 
                        FROM bookings u 
                        JOIN users u ON b.user_username = u.username
                        JOIN films f ON b.film_title= f.title
                        WHERE b.id = ?
                        """;
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, booking_id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Booking(rs.getInt("id"),
                        new User(rs.getString("username")),
                        new Film(rs.getString("title")),
                        rs.getDouble("total_price"));
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }

    public boolean addBooking(Booking booking) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO bookings(user_username,film_title, total_price) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, booking.getUser().getUsername());
            st.setString(2, booking.getFilm().getTitle());
            st.setDouble(3, booking.getTotalPrice());

            st.execute();

            return true;
        }
        catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }
}
