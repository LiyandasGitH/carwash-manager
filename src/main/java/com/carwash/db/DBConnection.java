package com.carwash.db;

public class DBConnection {

    private static final String HOST = System.getenv().getOrDefault("CARWASH_DB_HOST", "localhost");
    private static final String PORT = System.getenv().getOrDefault("CARWASH_DB_PORT", "3306");
    private static final String DATABASE = System.getenv().getOrDefault("CARWASH_DB_NAME", "carwash");
    private static final String USER = System.getenv().getOrDefault("CARWASH_DB_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("CARWASH_DB_PASSWORD", "");


}
