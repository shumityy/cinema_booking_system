package repositories;

import data.IDB;
import models.Role;
import models.User;
import repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean register(User user) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO users(username,password, role) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getRole().name());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public User getUser(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,username,password,role FROM users WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role"))
                );
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,username,password,role FROM users";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role"))
                );

                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }
    @Override
    public User login(String username, String password) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id, username, password, role FROM users WHERE username=? AND password=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role"))
                );
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }
}
