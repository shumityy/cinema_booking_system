package repositories;

import data.IDB;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserRepository implements IUserRepository {

    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean create(User user) {
        String sql = "INSERT INTO users(name, surname) VALUES (?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.execute();
            return true;

        } catch (Exception e) {
            System.out.println("User creation failed: " + e.getMessage());
            return false;
        }
    }
}
