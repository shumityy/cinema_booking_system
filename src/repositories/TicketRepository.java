package repositories;

import data.IDB;
import models.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TicketRepository {

    private final IDB db;

    public TicketRepository(IDB db) {
        this.db = db;
    }

    public boolean create(Ticket ticket) {
        String sql = "INSERT INTO tickets(seat_id, price) VALUES (?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, ticket.getSeatId());
            st.setDouble(2, ticket.getPrice());
            st.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Ticket creation failed: " + e.getMessage());
            return false;
        }
    }
}
