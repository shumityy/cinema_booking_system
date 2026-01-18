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

    public void createTicket(Ticket ticket) {
        // Removed film_id from SQL
        String sql = "INSERT INTO tickets(seat_id, price) VALUES (?,?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, ticket.getSeatId());
            st.setDouble(2, ticket.getPrice());
            st.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
