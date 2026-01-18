import data.PostgresDB;
import data.IDB;
import repositories.IUserRepository;
import repositories.UserRepository;
import controllers.UserController;
import controllers.IUserController;

public class Main {

    public static void main(String[] args) {
        // Here you specify which DB and UserRepository to use
        // And changing DB should not affect to whole code
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "somedb");
        IUserRepository repo = new UserRepository(db);
        IUserController controller = new UserController(repo);

        MyApplication app = new MyApplication(controller);

        app.start();

        db.close();
    }
}