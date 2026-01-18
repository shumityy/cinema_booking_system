package repositories;

import models.User;
import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    List<User> getAllUsers();
}
