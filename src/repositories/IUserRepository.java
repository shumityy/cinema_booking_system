package repositories;

import models.User;

public interface IUserRepository {
    boolean create(User user);
}
