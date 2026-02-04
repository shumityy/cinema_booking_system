package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {

    private String host;
    private int port;
    private String username;
    private String password;
    private String dbName;

    private Connection connection;

    // Singleton instance
    private static PostgresDB instance;

    // Private constructor
    public PostgresDB(String host, int port, String username, String password, String dbName) {
        setHost(host);
        setPort(port);
        setUsername(username);
        setPassword(password);
        setDbName(dbName);
    }

    // Factory method for Singleton
    public static PostgresDB getInstance(String host, int port, String username, String password, String dbName) {
        if (instance == null) {
            instance = new PostgresDB(host, port, username, password, dbName);
        }
        return instance;
    }

    // Get connection
    public Connection getConnection() {
        String connectionUrl = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }

            // Load PostgreSQL driver (optional in modern JDBC)
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(connectionUrl, username, password);
            return connection;

        } catch (Exception e) {
            System.out.println("Failed to connect to PostgreSQL: " + e.getMessage());
            return null;
        }
    }

    // Data validation
    public void setHost(String host) {
        if (host == null || host.isEmpty()) throw new IllegalArgumentException("Host cannot be empty");
        this.host = host;
    }

    public void setPort(int port) {
        if (port <= 0) throw new IllegalArgumentException("Port must be positive");
        this.port = port;
    }

    public void setUsername(String username) {
        if (username == null || username.isEmpty()) throw new IllegalArgumentException("Username cannot be empty");
        this.username = username;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("Password cannot be empty");
        this.password = password;
    }

    public void setDbName(String dbName) {
        if (dbName == null || dbName.isEmpty()) throw new IllegalArgumentException("Database name cannot be empty");
        this.dbName = dbName;
    }

    public String getHost() { return host; }
    public int getPort() { return port; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getDbName() { return dbName; }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException ex) {
                System.out.println("Connection close error: " + ex.getMessage());
            }
        }
    }

    // Example Role Management
    public boolean hasAccess(String role) {
        return role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("manager");
    }
}
