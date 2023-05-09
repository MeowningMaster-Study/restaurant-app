package com.example.backendjakarta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLService {
    private SQLService() {}

    static boolean isInstantiated = false;
    static Connection connection;

    public static void instantiate() {
        if (!isInstantiated) {
            return;
        }
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/mydb",
            "pgadmin",
            "pgadmin"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        instantiate();
        return connection;
    }
}
