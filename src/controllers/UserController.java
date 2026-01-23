package controllers;

import models.User;
import repositories.IUserRepository;

import java.util.Scanner;

public class UserController implements IUserController {

    private final IUserRepository repo;
    private final Scanner scanner = new Scanner(System.in);

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public void register() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();

        User user = new User(name, surname);
        repo.create(user);

        System.out.println(" User registered successfully!");
    }
}
