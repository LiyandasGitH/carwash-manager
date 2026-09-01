package com.carwash.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String HOST = System.getenv().getOrDefault("CARWASH_DB_HOST", "localhost");
    private static final String PORT = System.getenv().getOrDefault("CARWASH_DB_PORT", "3306");
    private static final String DATABASE = System.getenv().getOrDefault("CARWASH_DB_NAME", "carwash");
    private static final String USER = System.getenv().getOrDefault("CARWASH_DB_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("CARWASH_DB_PASSWORD", "");

    private static final String URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE
                    + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found on classpath", e);
        }
    }

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
