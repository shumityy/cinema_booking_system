package repositories.interfaces;

import models.Film;

import java.util.List;

public interface IFilmRepository {
    Film getFilm(int id);
    List<Film> getAllFilms();

    boolean addFilm(Film film);
}