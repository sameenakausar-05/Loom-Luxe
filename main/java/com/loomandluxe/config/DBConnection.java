package com.loomandluxe.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/loom_and_luxe";

    private static final String USERNAME =
            "root";

    private static final String PASSWORD =
            "root";

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