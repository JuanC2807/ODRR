package edu.gmu.cs321;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dataEntry")
public class dataEntry extends HttpServlet {
     @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Running DB initializer from dataEntry servlet...");
        DatabaseInitializer.initialize();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {

        // Collect all the form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String immigrantID = request.getParameter("immigrant_ID");
        String dependent = request.getParameter("dependent");
        String dob = request.getParameter("dateOfBirth");
        String email = request.getParameter("email");
        String documentRequested = request.getParameter("requestedDocument");

        // Perform server-side validation
        String validationError = validateFormData(firstName, lastName, email, immigrantID, dob);
        
        if (validationError != null) {
            // Validation failed - show error page
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body style='background-color: #2a2a2a; color: white; padding: 40px; font-family: Arial;'>");
            out.println("<h1 style='color: #ff4444;'>Validation Error</h1>");
            out.println("<p style='font-size: 18px; margin: 20px 0;'>" + validationError + "</p>");
            out.println("<button onclick='history.back()' style='background-color: #00bcd4; color: white; padding: 12px 24px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px;'>Go Back</button>");
            out.println("</body></html>");
            return;
        }

        // Create a single object holding all data
        ImmigrantData data = new ImmigrantData(firstName, lastName, gender, immigrantID,
                                               dependent, dob, email, documentRequested);

        // Store the object in the session
        HttpSession session = request.getSession();
        session.setAttribute("immigrantData", data);

        // Insert into DB
        try {
            int formId = FormDAO.insertForm(data);
            session.setAttribute("formId", formId);

            //submission
            String submittedBy = firstName + " " + lastName;
            FormSubmission submission = new FormSubmission(formId, submittedBy);
            FormSubmissionDAO submissionDAO = new FormSubmissionDAO();
            submissionDAO.saveSubmission(submission);

            //Mark as submitted for review
            FormDAO.updateStatus(formId, "submitted");
            //Redirect to this new form in the review screen
            response.sendRedirect(request.getContextPath() + "/review?formId=" + formId);
            return;

        } catch (SQLException e) {
            throw new RuntimeException("Database insert failed", e);
        }
    }

    
    /**
     * Validates the form data according to business rules
     * @return null if valid, error message string if invalid
     */
    private String validateFormData(String firstName, String lastName, String email, 
                                    String immigrantID, String dob) {
        
        // Check if first name starts with capital letter
        if (firstName == null || firstName.isEmpty()) {
            return "First name is required.";
        }
        if (!Character.isUpperCase(firstName.charAt(0))) {
            return "First name must start with a capital letter.";
        }
        
        // Check if last name starts with capital letter
        if (lastName == null || lastName.isEmpty()) {
            return "Last name is required.";
        }
        if (!Character.isUpperCase(lastName.charAt(0))) {
            return "Last name must start with a capital letter.";
        }
        
        // Check if email ends with .com
        if (email == null || email.isEmpty()) {
            return "Email is required.";
        }
        if (!email.endsWith(".com")) {
            return "Email must end with .com";
        }
        
        // Check if immigrant ID contains only numbers
        if (immigrantID == null || immigrantID.isEmpty()) {
            return "Immigrant ID is required.";
        }
        if (!immigrantID.matches("^[0-9]+$")) {
            return "Immigrant ID must contain only numbers.";
        }
        
        // Check if date of birth is before today
        if (dob == null || dob.isEmpty()) {
            return "Date of birth is required.";
        }
        try {
            LocalDate birthDate = LocalDate.parse(dob);
            LocalDate today = LocalDate.now();
            if (!birthDate.isBefore(today)) {
                return "Date of birth must be before today.";
            }
        } catch (DateTimeParseException e) {
            return "Invalid date format for date of birth.";
        }
        
        // All validations passed
        return null;
    }
}