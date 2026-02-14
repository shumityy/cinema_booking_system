package repositories.interfaces;

public interface IGenreRepository {
    String getAllGenres(); // просто строкой для меню
    boolean existsById(int id);
}