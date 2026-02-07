package models;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;

    public User(String username) {
        setName(username);
    }

    public User(String name, String password) {
        setName(name);
        setSurname(password);
    }

    public User(int id, String name, String password) {
        this(name, password);
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


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + "'";
    }
}
