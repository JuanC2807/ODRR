package edu.gmu.cs321;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    private static final String CREATE_FORMS_TABLE = 
        "CREATE TABLE IF NOT EXISTS forms (" +
        "   id INT AUTO_INCREMENT PRIMARY KEY," +
        "   first_name VARCHAR(255) NOT NULL," +
        "   last_name VARCHAR(255) NOT NULL," +
        "   gender VARCHAR(50) NOT NULL," +
        "   immigrant_id VARCHAR(50) NOT NULL," +
        "   dependent VARCHAR(255)," +
        "   date_of_birth DATE NOT NULL," +
        "   email VARCHAR(255) NOT NULL," +
        "   document_requested VARCHAR(255) NOT NULL," +
        "   status VARCHAR(50) DEFAULT 'notSent'," +
        "   reviewer_notes TEXT," +
        "   created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
        "   modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
        ");";

    private static final String CREATE_FORM_SUBMISSIONS_TABLE = 
        "CREATE TABLE IF NOT EXISTS form_submissions (" +
        "   id INT AUTO_INCREMENT PRIMARY KEY," +
        "   form_id INT NOT NULL," +
        "   submitted_by VARCHAR(255) NOT NULL," +
        "   submitted_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
        "   FOREIGN KEY (form_id) REFERENCES forms(id)" +
        ");";

    public static void initialize() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(CREATE_FORMS_TABLE);

            stmt.executeUpdate(CREATE_FORM_SUBMISSIONS_TABLE);

            System.out.println("Database initialization complete: tables created or already exists.");


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database", e);
        }
    }
}
