package controllers.interfaces;

import models.User;

public interface IFilmController {
    String getFilm(int id);
    String getAllFilms();
    String addFilm(User user, String title, int duration, int genreId);
    String updateFilmDuration(User user, int filmId, int duration);
    String deleteFilm(User user, int filmId);
}

