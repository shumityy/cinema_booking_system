package controllers;

import models.User;
import repositories.IUserRepository;
import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) { // Dependency Injection
        this.repo = repo;
    }

    @Override
    public String createUser(String name, String surname, String email, String password) {
        boolean male = gender.equalsIgnoreCase("male");
        User user = new User(name, surname, email, password);

        boolean created = repo.createUser(user);

        return (created ? "User was created!" : "User creation was failed!");
    }

    public String getUser(int id) {
        User user = repo.getUser(id);

        return (user == null ? "User was not found!" : user.toString());
    }

    public String getAllUsers() {
        List<User> users = repo.getAllUsers();

        StringBuilder response = new StringBuilder();
        for (User user : users) {
            response.append(user.toString()).append("\n");
        }

        return response.toString();
    }
}
