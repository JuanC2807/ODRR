package edu.gmu.cs321;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FormSubmissionDAO {

    public void saveSubmission(FormSubmission submission){
        String sql = "INSERT INTO form_submissions (form_id, submitted_by) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, submission.getFormId());
                ps.setString(2, submission.getSubmittedby());
                ps.executeUpdate();
            } catch (Exception e){
                throw new RuntimeException("Could not save submission", e);
            }
    }

    public List<FormSubmission> getAllSubmissions(){
        String sql = "SELECT * FROM form_submission ORDER BY submitted_date ASC";
        List<FormSubmission> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){

            while(rs.next()){
                FormSubmission fs = new FormSubmission();
                fs.setId(rs.getInt("id"));
                fs.setFormId(rs.getInt("form_id"));
                fs.setSubmittedBy(rs.getString("submitted_by"));

                Timestamp ts = rs.getTimestamp("submitted_date");
                if (ts != null){
                    fs.setSubmittedDate(ts.toLocalDateTime());
                }
                list.add(fs);
            }
        } catch (Exception e){
            throw new RuntimeException("Failed to load submission", e);
        }
        return list;

    }
    
}
