package controllers.interfaces;

import models.User;

public interface IUserController {
    String createUser(String username, String password);
    User login(String name, String surname);
    String getUser(int id);
    String getAllUsers();
}

