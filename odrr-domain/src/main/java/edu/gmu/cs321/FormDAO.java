package edu.gmu.cs321;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Date;

public class FormDAO {

    public static int insertForm(ImmigrantData data) throws SQLException {

        String sql = """
            INSERT INTO forms
            (first_name, last_name, gender, immigrant_id, dependent, date_of_birth, email, document_requested)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, data.getFirstName());
            ps.setString(2, data.getLastName());
            ps.setString(3, data.getGender());
            ps.setString(4, data.getImmigrantID());
            ps.setString(5, data.getDependent());
            ps.setDate(6, Date.valueOf(data.getDateOfBirth()));
            ps.setString(7, data.getEmail());
            ps.setString(8, data.getDocumentRequested());

            ps.executeUpdate();

            var keys = ps.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);   // return generated form_id
            }
        }

        throw new SQLException("Failed to insert form");
    }
}
