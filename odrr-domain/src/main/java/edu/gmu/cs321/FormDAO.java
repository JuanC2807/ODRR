package edu.gmu.cs321;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

    public static void updateStatus(int formId, String status) throws SQLException {
        String sql = "UPDATE forms SET status = ? WHERE id = ?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, status);
                ps.setInt(2, formId);
                ps.executeUpdate();
            }
        
    }

    public static List<ReviewerQueueItem> getReviewerQueue() throws SQLException {
        String sql = """
            SELECT f.id,
                   f.first_name,
                   f.last_name,
                   f.status,
                   fs.submitted_by,
                   fs.submitted_date
            FROM forms f
            JOIN form_submissions fs ON f.id = fs.form_id
            WHERE f.status = 'submitted'
            ORDER BY fs.submitted_date ASC
            """;

        List<ReviewerQueueItem> queue = new ArrayList<>();

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

                while (rs.next()) {
                    ReviewerQueueItem item = new ReviewerQueueItem();
                    item.setFormId(rs.getInt("id"));
                    item.setApplicantName(rs.getString("first_name") + " " + rs.getString("last_name"));
                    item.setStatus(rs.getString("status"));
                    item.setSubmittedBy(rs.getString("submitted_by"));

                    java.sql.Timestamp ts = rs.getTimestamp("submitted_date");
                    if (ts != null){
                        item.setSubmittedDate(ts.toLocalDateTime());
                    }
                    queue.add(item);
                }
            }
            return queue;
    }

    public static ImmigrantData getFormById(int id) throws SQLException {
        String sql = """
                SELECT first_name,
                       last_name,
                       gender,
                       immigrant_id,
                       dependent,
                       date_of_birth,
                       email,
                       document_requested,
                       reviewer_notes
                FROM forms
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, id);

                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        ImmigrantData data = new ImmigrantData();
                        data.setFirstName(rs.getString("first_name"));
                        data.setLastName(rs.getString("last_name"));
                        data.setGender(rs.getString("gender"));
                        data.setImmigrantID(rs.getString("immigrant_id"));
                        data.setDependent(rs.getString("dependent"));

                        Date dob = rs.getDate("date_of_birth");
                        if(dob != null){
                            data.setDateOfBirth(dob.toLocalDate().toString());
                        }

                        data.setEmail(rs.getString("email"));
                        data.setDocumentRequested(rs.getString("document_requested"));

                        String reviewerNotes = rs.getString("reviewer_notes");
                        if(reviewerNotes != null){
                            data.setNotesFromReviewer(reviewerNotes);
                        }
                        return data;
                    }
                }
            }


        //If a row was not found
        return null;
    }

    public static void updateReviewerNotes(int formId, String notes) throws SQLException{
        String sql = "UPDATE forms SET reviewer_notes = ? WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, notes);
                ps.setInt(2, formId);
                ps.executeUpdate();
            }
    }
}
