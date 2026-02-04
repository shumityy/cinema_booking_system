package models;

public class User {
    private int id;
    private String username;
    private String password;
    private String role; // admin, manager, editor

    public User() {
    }

    public User(String username, String password, String role) {
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    public User(int id, String username, String password, String role) {
        this(username, password, role);
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password cannot be empty");
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.isEmpty())
            throw new IllegalArgumentException("Role cannot be empty");
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
