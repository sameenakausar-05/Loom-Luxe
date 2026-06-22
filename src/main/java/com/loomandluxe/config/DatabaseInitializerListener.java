package com.loomandluxe.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.stream.Collectors;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import com.loomandluxe.util.DBConnection;

@WebListener
public class DatabaseInitializerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("DatabaseInitializerListener: Initializing database...");
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("DatabaseInitializerListener: Could not establish a database connection.");
                return;
            }

            // Check if the 'users' table exists to determine if we should run schema.sql
            boolean tableExists = false;
            try (ResultSet rs = conn.getMetaData().getTables(null, null, "users", null)) {
                if (rs.next()) {
                    tableExists = true;
                }
            }

            if (!tableExists) {
                System.out.println("DatabaseInitializerListener: Schema not found. Running schema.sql...");
                executeSqlScript(conn, "schema.sql");
                System.out.println("DatabaseInitializerListener: Seeding data. Running seed.sql...");
                executeSqlScript(conn, "seed.sql");
                System.out.println("DatabaseInitializerListener: Database successfully initialized and seeded!");
            } else {
                System.out.println("DatabaseInitializerListener: Database tables already exist. Skipping initialization.");
            }
        } catch (Exception e) {
            System.err.println("DatabaseInitializerListener: Error during database initialization.");
            e.printStackTrace();
        }
    }

    private void executeSqlScript(Connection conn, String resourceName) throws Exception {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (is == null) {
                System.err.println("DatabaseInitializerListener: Script " + resourceName + " not found.");
                return;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    String trimmed = line.trim();
                    if (trimmed.isEmpty() || trimmed.startsWith("--") || trimmed.startsWith("#")) {
                        continue;
                    }
                    sb.append(line).append("\n");
                }
                
                String sql = sb.toString();
                String[] statements = sql.split(";");
                try (Statement stmt = conn.createStatement()) {
                    for (String stmtText : statements) {
                        String trimmedStmt = trimmedStmt(stmtText);
                        if (!trimmedStmt.isEmpty()) {
                            stmt.execute(trimmedStmt);
                        }
                    }
                }
            }
        }
    }

    private String trimmedStmt(String stmt) {
        if (stmt == null) return "";
        String trimmed = stmt.trim();
        // Remove trailing semicolons or whitespace
        while (trimmed.endsWith(";")) {
            trimmed = trimmed.substring(0, trimmed.length() - 1).trim();
        }
        return trimmed;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup resources if necessary
    }
}
