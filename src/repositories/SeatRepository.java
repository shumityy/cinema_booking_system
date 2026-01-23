package repositories;

import data.IDB;
import models.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeatRepository {

    private final IDB db;

    public SeatRepository(IDB db) {
        this.db = db;
    }

    public boolean isSeatFree(int seatId) {
        String sql = "SELECT is_booked FROM seats WHERE id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, seatId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return !rs.getBoolean("is_booked");
            }

        } catch (Exception e) {
            System.out.println("Seat check failed: " + e.getMessage());
        }
        return false;
    }

    public void bookSeat(int seatId) {
        String sql = "UPDATE seats SET is_booked = true WHERE id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, seatId);
            st.execute();

        } catch (Exception e) {
            System.out.println("Seat booking failed: " + e.getMessage());
        }
    }

    // 🔽 NEW — for visual output
    public List<Seat> getSeatsByHall(int hallId) {
        List<Seat> seats = new ArrayList<>();
        String sql = "SELECT id, seat_number, is_booked FROM seats WHERE hall_id = ? ORDER BY seat_number";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, hallId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Seat seat = new Seat(
                        rs.getInt("id"),
                        rs.getInt("hall_id"),
                        rs.getInt("seat_number"),
                        rs.getBoolean("is_booked")
                );
                seats.add(seat);
            }

        } catch (Exception e) {
            System.out.println("Seat list failed: " + e.getMessage());
        }
        return seats;
    }
}
