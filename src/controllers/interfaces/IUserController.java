package controllers.interfaces;

import models.User;

public interface IUserController {
    String register(String username, String password);
    User login(String username, String password);
    String getUser(int id);
    String getAllUsers();
    String deleteUser(User admin, int userId);
}

