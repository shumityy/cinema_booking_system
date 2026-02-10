package controllers;

import controllers.interfaces.IUserController;
import models.User;
import repositories.interfaces.IUserRepository;

import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) { // Dependency Injection
        this.repo = repo;
    }

    public String register(String username, String password){
        User user = new User(username, password);

        boolean created = repo.register(user);

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

    public User login(String name, String surname) {
        return repo.login(name, surname);
    }
    public String deleteUser(User admin, int userId) {
        if (admin == null || !admin.isAdmin()) {
            return "Access denied. Admin only.";
        }

        if (userId <= 0) {
            return "Invalid user ID.";
        }

        if (admin.getId() == userId) {
            return "You cannot delete your own account.";
        }

        boolean deleted = repo.deleteUser(userId);
        return deleted ? "User deleted successfully!" : "User not found or delete failed.";
    }
}
