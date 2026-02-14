package repositories;

import data.IDB;
import repositories.interfaces.IGenreRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GenreRepository implements IGenreRepository {
    private final IDB db;

    public GenreRepository(IDB db) {
        this.db = db;
    }

    @Override
    public String getAllGenres() {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT id, name FROM genres ORDER BY id";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                sb.append(rs.getInt("id"))
                        .append(". ")
                        .append(rs.getString("name"))
                        .append("\n");
            }
        } catch (Exception e) {
            return "SQL error: " + e.getMessage();
        }

        return sb.length() == 0 ? "No genres found." : sb.toString();
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT 1 FROM genres WHERE id=?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            return false;
        }
    }
}