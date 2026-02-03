package models;

public class User {
    private int id;
    private String username;
    private String password;
    private Role role;

    public User() {

    }

    public User(String name, String password) {
        setName(name);
        setSurname(password);
        this.role = Role.USER;
    }

    public User(String name, String password, Role role) {
        setName(name);
        setSurname(password);
        this.role = role;
    }

    public User(int id, String name, String password, Role role) {
        this(name, password, role);
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setSurname(String password) {
        this.password = password;
    }

    public Role getRole() {return role;}

    public void setRole(Role role) {this.role = role;}

    public boolean isAdmin() {return role == Role.ADMIN;}


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", surname='" + password + "'";
    }
}
