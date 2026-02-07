package repositories;

import data.IDB;
import models.Film;
import repositories.interfaces.IFilmRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmRepository implements IFilmRepository {
    private final IDB db;

    public FilmRepository(IDB db) { this.db = db; }

    public List<Film> getAllFilms() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,title,duration FROM films";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Film> films = new ArrayList<>();
            while (rs.next()) {
                Film film = new Film(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("duration")
                );

                films.add(film);
            }

            return films;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    public Film getFilm(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,title,duration FROM films WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Film(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("duration")
                );
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }
    //FOR ADMIN
    public void addFilm(String title, int duration) {

        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO film(title, duration) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, title);
            ps.setInt(2, duration);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
        }
    }
}
