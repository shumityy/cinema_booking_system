package repositories;

import data.IDB;
import models.Booking;
import models.Film;
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

    public List<Booking> getFullBooking() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = """
                        SELECT b.id, u.username, f.title, b.total_price 
                        FROM bookings b 
                        JOIN users u ON b.user_username = u.username
                        JOIN films f ON b.film_title= f.title
                        """;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<Booking> bookings = new ArrayList<>();
            while (rs.next()) {
                Booking booking = new Booking(rs.getInt("id"),
                        new User(rs.getString("username")),
                        new Film(rs.getString("title")),
                        rs.getDouble("total_price"));
                bookings.add(booking);
            }
            return bookings;

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
