package repositories;

import data.IDB;
import models.Film;
import repositories.interfaces.IFilmRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class FilmRepository implements IFilmRepository {
    private final IDB db;

    public FilmRepository(IDB db) {
        this.db = db;
    }

    @Override
    public Film getFilm(int id) {
        String sql = """
                SELECT f.id, f.title, f.duration, f.genre_id, g.name AS genre_name
                FROM films f
                LEFT JOIN genres g ON g.id = f.genre_id
                WHERE f.id = ?
                """;

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Film(
                            rs.getString("title"),
                            rs.getInt("duration")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();

        String sql = """
                SELECT f.id, f.title, f.duration, f.genre_id, g.name AS genre_name
                FROM films f
                LEFT JOIN genres g ON g.id = f.genre_id
                ORDER BY f.id
                """;

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                films.add(new Film(
                        rs.getString("title"),
                        rs.getInt("duration")
                ));
            }
        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return films;
    }

    @Override
    public boolean addFilm(Film film) {
        String sql = "INSERT INTO films(title, duration, genre_id) VALUES (?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, film.getTitle());
            st.setInt(2, film.getDuration());
            st.setInt(3, film.getGenreId());

            return st.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateFilmDuration(int id, int duration) {
        String sql = "UPDATE films SET duration = ? WHERE id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, duration);
            st.setInt(2, id);

            return st.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteFilm(int id) {
        String sql = "DELETE FROM films WHERE id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);
            return st.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("sql error: " + e.getMessage());
            return false;
        }
    }
}