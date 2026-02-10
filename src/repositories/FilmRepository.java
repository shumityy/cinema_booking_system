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
                        rs.getString("title"),
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
    public boolean addFilm(Film film) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO films(title, duration) VALUES (?, ?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, film.getTitle());
            st.setInt(2, film.getDuration());

            st.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
            return false;
        }
    }
    public boolean updateFilmDuration(int id, int duration) {
        try (Connection con = db.getConnection()) {
            String sql = "UPDATE films SET duration = ? WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, duration);
            st.setInt(2, id);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
            return false;
        }
    }
    @Override
    public boolean deleteFilm(int id) {
        try (Connection con = db.getConnection()) {
            String sql = "DELETE FROM films WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
            return false;
        }
    }
}
