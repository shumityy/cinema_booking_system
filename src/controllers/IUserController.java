package controllers;

public interface IUserController {
    String createUser(String name, String surname, String email, String password);
    String getUser(int id);
    String getAllUsers();
}

