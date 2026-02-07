package controllers;

import controllers.interfaces.IFilmController;
import models.Film;
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
        for (Film film : films) {
            response.append(film.toString()).append("\n");
        }

        return response.toString();
    }
    public String getFilm(int id) {
        Film film = repo.getFilm(id);

        return (film == null ? "Film was not found!" : film.toString());
    }
}