package controllers;

import models.User;
import repositories.IUserRepository;

public class UserController implements IUserController {

    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    public String createUser(String name, String surname) {
        User user = new User(name, surname);
        return repo.createUser(user)
                ? "User created successfully!"
                : "User creation failed!";
    }
}
