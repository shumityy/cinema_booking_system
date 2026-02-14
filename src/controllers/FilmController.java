package controllers;

import controllers.interfaces.IFilmController;
import models.Film;
import models.User;
import repositories.interfaces.IFilmRepository;

import java.util.List;

public class FilmController implements IFilmController {
    private final IFilmRepository repo;

    public FilmController(IFilmRepository repo) { // Dependency Injection
        this.repo = repo;
    }

    public String getAllFilms() {
        List<Film> films = repo.getAllFilms();

        StringBuilder response = new StringBuilder();
        films.forEach(film ->
                response.append(film.toString()).append("\n")
        );
        return response.toString();
    }
    public String getFilm(int id) {
        Film film = repo.getFilm(id);

        return (film == null ? "Film was not found!" : film.toString());
    }
    public String addFilm(User user, String title, int duration, int genreId) {
        if (user == null || !user.isAdmin()) {
            return "Access denied. Admin only.";
        }

        if (title == null || title.trim().isEmpty()) {
            return "Validation error: title cannot be empty.";
        }
        if (duration <= 0) {
            return "Validation error: duration must be > 0.";
        }

        boolean created = repo.addFilm(new Film(title, duration));
        return created ? "Film added successfully!" : "Film adding failed!";
    }
    public String updateFilmDuration(User user, int filmId, int duration) {
        if (!user.isAdmin()) return "Access denied. Admin only.";
        if (duration <= 0) return "Invalid duration.";

        boolean ok = repo.updateFilmDuration(filmId, duration);
        return ok ? "Film updated successfully!" : "Update failed.";
    }
    public String deleteFilm(User user, int filmId) {
        if (!user.isAdmin()) return "Access denied. Admin only.";

        boolean ok = repo.deleteFilm(filmId);
        return ok ? "Film deleted successfully!" : "Delete failed.";
    }
}