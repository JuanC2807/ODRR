package edu.gmu.cs321;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/review")
public class Review extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Get session and retrieve immigrant data
        HttpSession session = request.getSession();
        ImmigrantData data = (ImmigrantData) session.getAttribute("immigrantData");
        
        // If no data in session, show error message
        if (data == null) {
            out.println("<html><body style='background-color: #2a2a2a; color: white; padding: 40px;'>");
            out.println("<h2>No form data found in session</h2>");
            out.println("<a href='" + request.getContextPath() + "/dataEntry.html' style='color: #00bcd4;'>Go to Data Entry</a>");
            out.println("</body></html>");
            return;
        }
        
        // Generate full styled HTML
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Review Form</title>");
        out.println("<style>");
        out.println("* { margin: 0; padding: 0; box-sizing: border-box; }");
        out.println("body { font-family: Arial, sans-serif; background-color: #2a2a2a; color: #fff; }");
        out.println(".header { background-color: #1a1a1a; padding: 20px 40px; display: flex; justify-content: space-between; align-items: center; border-bottom: 3px solid #00bcd4; }");
        out.println(".header h1 { font-size: 28px; font-weight: normal; }");
        out.println(".new-form-btn { background-color: #e8e8e8; color: #000; border: none; padding: 12px 24px; border-radius: 25px; cursor: pointer; font-size: 14px; font-weight: 500; }");
        out.println(".new-form-btn:hover { background-color: #d0d0d0; }");
        out.println(".container { display: flex; height: calc(100vh - 80px); }");
        out.println(".queue-section { width: 250px; background-color: #1e1e1e; border-right: 1px solid #444; padding: 20px; }");
        out.println(".queue-section h2 { font-size: 18px; margin-bottom: 15px; color: #00bcd4; }");
        out.println(".queue-empty { color: #888; font-size: 14px; font-style: italic; }");
        out.println(".main-content { flex: 1; padding: 40px; overflow-y: auto; }");
        out.println(".form-section { max-width: 800px; background-color: #3a3a3a; padding: 30px; border-radius: 8px; margin-bottom: 20px; }");
        out.println(".form-section h2 { margin-bottom: 25px; font-size: 24px; }");
        out.println(".form-group { margin-bottom: 20px; }");
        out.println("label { display: block; margin-bottom: 8px; font-size: 14px; color: #ccc; }");
        out.println("input[type='text'], input[type='email'], input[type='date'], select { width: 100%; padding: 12px; background-color: #8a8a8a; border: none; border-radius: 4px; color: #000; font-size: 14px; }");
        out.println("input:focus, select:focus { outline: 2px solid #00bcd4; }");
        out.println(".form-actions { display: flex; gap: 15px; margin-top: 30px; }");
        out.println(".btn { padding: 12px 24px; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; font-weight: 500; }");
        out.println(".btn-save { background-color: #00bcd4; color: #fff; }");
        out.println(".btn-save:hover { background-color: #008ba3; }");
        out.println(".btn-approve { background-color: #4caf50; color: #fff; }");
        out.println(".btn-approve:hover { background-color: #388e3c; }");
        out.println(".btn-secondary { background-color: #666; color: #fff; }");
        out.println(".btn-secondary:hover { background-color: #555; }");
        out.println(".notes-section { width: 350px; background-color: #1e1e1e; border-left: 1px solid #444; padding: 20px; }");
        out.println(".notes-section h2 { font-size: 18px; margin-bottom: 15px; color: #00bcd4; }");
        out.println("textarea { width: 100%; height: 300px; padding: 12px; background-color: #3a3a3a; border: 1px solid #555; border-radius: 4px; color: #fff; font-size: 14px; font-family: Arial, sans-serif; resize: vertical; }");
        out.println("textarea:focus { outline: 2px solid #00bcd4; }");
        out.println(".row { display: flex; gap: 20px; }");
        out.println(".row .form-group { flex: 1; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        
        // Header
        out.println("<div class='header'>");
        out.println("<h1>Review Form</h1>");
        out.println("<button class='new-form-btn' onclick='window.location.href=\"" + request.getContextPath() + "/dataEntry.html\"'>New Form</button>");
        out.println("</div>");
        
        // Container
        out.println("<div class='container'>");
        
        // Queue section
        out.println("<div class='queue-section'>");
        out.println("<h2>Review Queue</h2>");
        out.println("<div class='queue-empty'>No forms in queue</div>");
        out.println("</div>");
        
        // Main content
        out.println("<div class='main-content'>");
        out.println("<div class='form-section'>");
        out.println("<h2>Immigrant Data Entry</h2>");
        out.println("<form method='post' action='" + request.getContextPath() + "/review'>");
        
        // First and Last Name row
        out.println("<div class='row'>");
        out.println("<div class='form-group'>");
        out.println("<label for='firstName'>First Name *</label>");
        out.println("<input type='text' id='firstName' name='firstName' value='" + escapeHtml(data.getFirstName()) + "'>");
        out.println("</div>");
        out.println("<div class='form-group'>");
        out.println("<label for='lastName'>Last Name *</label>");
        out.println("<input type='text' id='lastName' name='lastName' value='" + escapeHtml(data.getLastName()) + "'>");
        out.println("</div>");
        out.println("</div>");
        
        // Date of Birth
        out.println("<div class='form-group'>");
        out.println("<label for='dateOfBirth'>Date of Birth *</label>");
        out.println("<input type='date' id='dateOfBirth' name='dateOfBirth' value='" + escapeHtml(data.getDateOfBirth()) + "'>");
        out.println("</div>");
        
        // Gender and Immigrant ID row
        out.println("<div class='row'>");
        out.println("<div class='form-group'>");
        out.println("<label for='gender'>Gender *</label>");
        out.println("<select id='gender' name='gender'>");
        out.println("<option value=''>Select...</option>");
        String gender = data.getGender();
        out.println("<option value='Male'" + (gender != null && gender.equalsIgnoreCase("Male") ? " selected" : "") + ">Male</option>");
        out.println("<option value='Female'" + (gender != null && gender.equalsIgnoreCase("Female") ? " selected" : "") + ">Female</option>");
        out.println("<option value='Other'" + (gender != null && gender.equalsIgnoreCase("Other") ? " selected" : "") + ">Other</option>");
        out.println("</select>");
        out.println("</div>");
        out.println("<div class='form-group'>");
        out.println("<label for='immigrant_ID'>Immigrant ID *</label>");
        out.println("<input type='text' id='immigrant_ID' name='immigrant_ID' value='" + escapeHtml(data.getImmigrantID()) + "'>");
        out.println("</div>");
        out.println("</div>");
        
        // Dependent
        out.println("<div class='form-group'>");
        out.println("<label for='dependent'>Dependent</label>");
        out.println("<input type='text' id='dependent' name='dependent' value='" + escapeHtml(data.getDependent()) + "'>");
        out.println("</div>");
        
        // Email
        out.println("<div class='form-group'>");
        out.println("<label for='email'>Email *</label>");
        out.println("<input type='email' id='email' name='email' value='" + escapeHtml(data.getEmail()) + "'>");
        out.println("</div>");
        
        // Document Requested
        out.println("<div class='form-group'>");
        out.println("<label for='requestedDocument'>Document Requested *</label>");
        out.println("<select id='requestedDocument' name='requestedDocument'>");
        out.println("<option value=''>Select...</option>");
        out.println("<option value='Birth Certificate'" + (data.getDocumentRequested().equals("Birth Certificate") ? " selected" : "") + ">Birth Certificate</option>");
        out.println("<option value='Passport'" + (data.getDocumentRequested().equals("Passport") ? " selected" : "") + ">Passport</option>");
        out.println("</select>");
        out.println("</div>");
        
        // Hidden field for notes
        out.println("<input type='hidden' id='hiddenNotes' name='notes' value=''>");
        
        // Action buttons
        out.println("<div class='form-actions'>");
        out.println("<button type='submit' name='action' value='save' class='btn btn-save'>Save Changes</button>");
        out.println("<button type='button' class='btn btn-secondary' onclick='alert(\"Revalidating form...\")'>Revalidate</button>");
        out.println("<button type='submit' name='action' value='approve' class='btn btn-approve'>Send to Approval</button>");
        out.println("</div>");
        
        out.println("</form>");
        out.println("</div>");
        out.println("</div>");
        
        // Notes section
        out.println("<div class='notes-section'>");
        out.println("<h2>Reviewer Notes</h2>");
        out.println("<textarea id='notesArea' placeholder='Add notes about this review...'></textarea>");
        out.println("</div>");
        
        out.println("</div>"); // End container
        
        // JavaScript
        out.println("<script>");
        out.println("document.querySelector('form').addEventListener('submit', function() {");
        out.println("  document.getElementById('hiddenNotes').value = document.getElementById('notesArea').value;");
        out.println("});");
        out.println("</script>");
        
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        // Update the immigrant data with edited values
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String immigrantID = request.getParameter("immigrant_ID");
        String dependent = request.getParameter("dependent");
        String dob = request.getParameter("dateOfBirth");
        String email = request.getParameter("email");
        String documentRequested = request.getParameter("requestedDocument");
        String notes = request.getParameter("notes");
        
        ImmigrantData updatedData = new ImmigrantData(firstName, lastName, gender, immigrantID,
                                                       dependent, dob, email, documentRequested);
        
        session.setAttribute("immigrantData", updatedData);
        
        // Store notes in session if provided
        if (notes != null && !notes.isEmpty()) {
            session.setAttribute("reviewerNotes", notes);
        }
        
        if ("save".equals(action)) {
            // Redirect back to review page
            response.sendRedirect(request.getContextPath() + "/review");
        } else if ("approve".equals(action)) {
            // Send to approval
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body style='background-color: #2a2a2a; color: white; padding: 40px; font-family: Arial;'>");
            out.println("<h1 style='color: #4caf50;'>Form Sent to Approval!</h1>");
            out.println("<p>The form has been successfully sent to the approval queue.</p>");
            out.println("<a href='" + request.getContextPath() + "/review' style='color: #00bcd4;'>Review Another Form</a>");
            out.println("</body></html>");
        }
    }
    
    // Helper method to escape HTML
    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#x27;");
    }
}