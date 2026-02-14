package repositories.interfaces;

import models.Film;

import java.util.List;

public interface IFilmRepository {
    Film getFilm(int id);
    List<Film> getAllFilms();

    boolean addFilm(Film film);

    boolean updateFilmDuration(int id, int duration);
    boolean deleteFilm(int id);

    // NEW (genres)
    String getAllGenres();      // вывести список жанров
    boolean genreExists(int id); // проверить genre id
}