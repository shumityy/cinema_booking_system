package repositories.interfaces;

import models.User;
import java.util.List;

public interface IUserRepository {
    boolean register(User user);
    User getUser(int id);
    List<User> getAllUsers();
    User login(String name, String surname);
    boolean deleteUser(int id);
}