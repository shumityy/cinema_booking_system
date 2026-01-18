package repositories;

import data.IDB;
import java.sql.*;

public class SeatRepository {

    private final IDB db;

    public SeatRepository(IDB db) {
        this.db = db;
    }

    public boolean isSeatAvailable(int seatId) {
        String sql = "SELECT is_booked FROM seats WHERE id=?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, seatId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return !rs.getBoolean("is_booked");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void bookSeat(int seatId) {
        String sql = "UPDATE seats SET is_booked=true WHERE id=?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, seatId);
            st.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
