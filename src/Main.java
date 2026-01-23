import controllers.interfaces.IFilmController;
import controllers.interfaces.IUserController;
import data.PostgresDB;
import data.IDB;
import repositories.*;
import controllers.*;
import repositories.interfaces.IFilmRepository;
import repositories.interfaces.IUserRepository;

public class Main {

    public static void main(String[] args) {
        // Here you specify which DB and UserRepository to use
        // And changing DB should not affect to whole code
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "simpleDB");
        IUserRepository userRepo = new UserRepository(db);
        IFilmRepository filmRepo = new FilmRepository(db);
        IUserController userController = new UserController(userRepo);
        IFilmController filmController = new FilmController(filmRepo);

        MyApplication app = new MyApplication(userController, filmController);

        app.start();

        db.close();
    }
}
