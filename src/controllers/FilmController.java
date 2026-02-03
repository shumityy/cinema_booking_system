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
    public String addFilm(User user, String title, int duration) {
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
}