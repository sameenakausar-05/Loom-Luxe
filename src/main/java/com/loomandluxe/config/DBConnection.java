package com.loomandluxe.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:mysql://localhost:3306/loom_and_luxe";

    private static final String USERNAME =
            System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";

    private static final String PASSWORD =
            System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "root";

    public static Connection getConnection() {

        Connection conn = null;

        try {

            Class.forName(
                    "com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );

        }
        catch(Exception e) {

            e.printStackTrace();
        }

        return conn;
    }
}