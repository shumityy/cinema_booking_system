package repositories;

import data.IDB;
import models.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmRepository {

    private final IDB db;

    public FilmRepository(IDB db) {
        this.db = db;
    }

    public List<Film> getAll() {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT id, title, duration FROM films";

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Film film = new Film(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("duration")
                );
                films.add(film);
            }

        } catch (Exception e) {
            System.out.println("Film fetch failed: " + e.getMessage());
        }
        return films;
    }
}
