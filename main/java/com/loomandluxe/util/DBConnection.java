package com.loomandluxe.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database URL
    private static final String URL = "jdbc:mysql://localhost:3306/loom_and_luxe";

    // Database Username
    private static final String USERNAME = "root";

    // Database Password
    private static final String PASSWORD = "root";

    // Method to establish database connection
    public static Connection getConnection() {

        Connection connection = null;

        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create Connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Database Connected Successfully");

        } catch (ClassNotFoundException e) {

            System.out.println("MySQL JDBC Driver Not Found");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Failed To Connect Database");
            e.printStackTrace();
        }

        return connection;
    }

    // Method to close database connection
    public static void closeConnection(Connection connection) {

        try {

            if (connection != null && !connection.isClosed()) {

                connection.close();
                System.out.println("Database Connection Closed");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}