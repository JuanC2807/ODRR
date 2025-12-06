package edu.gmu.cs321;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

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
        
        HttpSession session = request.getSession();
        ImmigrantData data = null;
        Integer currentFormId = null;

        //Loads reviewer queue from DataBase
        List<ReviewerQueueItem> queue = null;
        try{
            queue = FormDAO.getReviewerQueue();
        } catch(SQLException e){
            e.printStackTrace();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        //if a formId is provided in the URL, load form from the data base
        String formIdParam = request.getParameter("formId");
        if (formIdParam != null){
            try {
                currentFormId = Integer.parseInt(formIdParam);
                ImmigrantData loaded = FormDAO.getFormById(currentFormId);
                if (loaded != null) {
                    data = loaded;
                }
            } catch(NumberFormatException | SQLException e){
                e.printStackTrace();
            }
        }
        //3
        if(data == null && queue != null && !queue.isEmpty()){
            currentFormId = queue.get(0).getFormId();
            try{
                data = FormDAO.getFormById(currentFormId);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        //4
        if(data != null && currentFormId != null) {
            session.setAttribute("formId", currentFormId);
            session.setAttribute("immigrantData", data);
        }

        //5
        if(data == null) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Review Form</title>");
            out.println("</head>");
            out.println("<body style='background-color: #2a2a2a; color: white; padding: 40px; font-family: Arial;'>");
            out.println("<h1>Review Queue</h1>");
            if(queue == null || queue.isEmpty()){
                out.println("<p>There are currently no forms waiting for review.</p>");
            } else {
                out.println("<p>Unable to load the selected form.</p>");
            }
            out.println("<a href='" + request.getContextPath() + "/review' style='color: #00bcd4;'>Reload</a>");
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
        if (queue == null || queue.isEmpty()){
            out.println("<div class='queue-empty'>No forms in queue</div>");
        } else {
            out.println("<ul style='list-style: none; padding: 0; margin: 0;'>");
            for(ReviewerQueueItem item : queue) {
                String label = escapeHtml(item.getApplicantName());
                String submitted = (item.getSubmittedDate() != null) ? item.getSubmittedDate().format(formatter): "N/A";
                out.println("<li style='margin-bottom: 10px;'>");
                out.println("<a href='" + request.getContextPath() + "/review?formId=" 
                            + item.getFormId() + "' " +
                            "style='color: #00bcd4; text-decoration: none; display: block;'>");
                out.println(label + "<br><span style = 'font-size: 12px; color: #aaa;'>Submitted: " + submitted + "</span>");
                out.println("</a>");
                out.println("</li>");
            }
            out.println("</ul>");
        } 
        out.println("</div>");
        //end of queue
        
        // Main content
        out.println("<div class='main-content'>");
        out.println("<div class='form-section'>");
        out.println("<h2>Immigrant Data Entry</h2>");
        out.println("<form method='post' action='" + request.getContextPath() + "/review' id='reviewForm'>");
        
        // First and Last Name row
        out.println("<div class='row'>");
        out.println("<div class='form-group'>");
        out.println("<label for='firstName'>First Name *</label>");
        out.println("<input type='text' id='firstName' name='firstName' value='" + escapeHtml(data.getFirstName()) + "' required>");
        out.println("</div>");
        out.println("<div class='form-group'>");
        out.println("<label for='lastName'>Last Name *</label>");
        out.println("<input type='text' id='lastName' name='lastName' value='" + escapeHtml(data.getLastName()) + "' required>");
        out.println("</div>");
        out.println("</div>");
        
        // Date of Birth
        out.println("<div class='form-group'>");
        out.println("<label for='dateOfBirth'>Date of Birth *</label>");
        out.println("<input type='date' id='dateOfBirth' name='dateOfBirth' value='" + escapeHtml(data.getDateOfBirth()) + "' required>");
        out.println("</div>");
        
        // Gender and Immigrant ID row
        out.println("<div class='row'>");
        out.println("<div class='form-group'>");
        out.println("<label for='gender'>Gender *</label>");
        out.println("<select id='gender' name='gender' required>");
        out.println("<option value=''>Select...</option>");
        String gender = data.getGender();
        out.println("<option value='Male'" + (gender != null && gender.equalsIgnoreCase("Male") ? " selected" : "") + ">Male</option>");
        out.println("<option value='Female'" + (gender != null && gender.equalsIgnoreCase("Female") ? " selected" : "") + ">Female</option>");
        out.println("<option value='Other'" + (gender != null && gender.equalsIgnoreCase("Other") ? " selected" : "") + ">Other</option>");
        out.println("</select>");
        out.println("</div>");
        out.println("<div class='form-group'>");
        out.println("<label for='immigrant_ID'>Immigrant ID *</label>");
        out.println("<input type='text' id='immigrant_ID' name='immigrant_ID' value='" + escapeHtml(data.getImmigrantID()) + "' required>");
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
        out.println("<input type='email' id='email' name='email' value='" + escapeHtml(data.getEmail()) + "' required>");
        out.println("</div>");
        
        // Document Requested
        out.println("<div class='form-group'>");
        out.println("<label for='requestedDocument'>Document Requested *</label>");
        out.println("<select id='requestedDocument' name='requestedDocument' required>");
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
        out.println("<button type='button' class='btn btn-secondary' onclick='revalidateForm()'>Revalidate</button>");
        out.println("<button type='submit' name='action' value='approve' class='btn btn-approve'>Send to Approval</button>");
        out.println("</div>");
        
        out.println("</form>");
        out.println("</div>");
        out.println("</div>");
        
        // Notes section
        //Updated this so notes display saved ones also
        out.println("<div class='notes-section'>");
        out.println("<h2>Reviewer Notes</h2>");
        String existingNotes = escapeHtml(data.getNotesFromReviewer());
        out.println("<textarea id='notesArea' placeholder='Add notes about this review...'>" + existingNotes + "</textarea>");
        out.println("</div>");
        
        out.println("</div>"); // End container
        
        // JavaScript
        out.println("<script>");
        out.println("document.querySelector('form').addEventListener('submit', function(e) {");
        out.println("  if (e.submitter && e.submitter.value !== 'save') {");
        out.println("    return;");
        out.println("  }");
        out.println("  document.getElementById('hiddenNotes').value = document.getElementById('notesArea').value;");
        out.println("});");
        out.println("");
        out.println("function revalidateForm() {");
        out.println("  const form = document.getElementById('reviewForm');");
        out.println("  const firstName = document.getElementById('firstName').value.trim();");
        out.println("  const lastName = document.getElementById('lastName').value.trim();");
        out.println("  const email = document.getElementById('email').value.trim();");
        out.println("  const immigrantID = document.getElementById('immigrant_ID').value.trim();");
        out.println("  const dob = document.getElementById('dateOfBirth').value;");
        out.println("  ");
        out.println("  // Check HTML5 validation first");
        out.println("  if (!form.checkValidity()) {");
        out.println("    form.reportValidity();");
        out.println("    alert('Validation failed: Please fill in all required fields correctly.');");
        out.println("    return;");
        out.println("  }");
        out.println("  ");
        out.println("  // Check if first name starts with capital letter");
        out.println("  if (firstName.length > 0 && firstName[0] !== firstName[0].toUpperCase()) {");
        out.println("    alert('Validation failed: First name must start with a capital letter.');");
        out.println("    return;");
        out.println("  }");
        out.println("  ");
        out.println("  // Check if last name starts with capital letter");
        out.println("  if (lastName.length > 0 && lastName[0] !== lastName[0].toUpperCase()) {");
        out.println("    alert('Validation failed: Last name must start with a capital letter.');");
        out.println("    return;");
        out.println("  }");
        out.println("  ");
        out.println("  // Check if email ends with .com");
        out.println("  if (!email.endsWith('.com')) {");
        out.println("    alert('Validation failed: Email must end with .com');");
        out.println("    return;");
        out.println("  }");
        out.println("  ");
        out.println("  // Check if immigrant ID contains only numbers");
        out.println("  if (!/^[0-9]+$/.test(immigrantID)) {");
        out.println("    alert('Validation failed: Immigrant ID must contain only numbers.');");
        out.println("    return;");
        out.println("  }");
        out.println("  ");
        out.println("  // Check if date of birth is before today");
        out.println("  const today = new Date();");
        out.println("  today.setHours(0, 0, 0, 0);");
        out.println("  const birthDate = new Date(dob);");
        out.println("  if (birthDate >= today) {");
        out.println("    alert('Validation failed: Date of birth must be before today.');");
        out.println("    return;");
        out.println("  }");
        out.println("  ");
        out.println("  // All validations passed - redirect to approval");
        out.println("  document.getElementById('hiddenNotes').value = document.getElementById('notesArea').value;");
        out.println("  ");
        out.println("  // Create a form to submit to approval");
        out.println("  const approvalForm = document.createElement('form');");
        out.println("  approvalForm.method = 'POST';");
        out.println("  approvalForm.action = '" + request.getContextPath() + "/review';");
        out.println("  ");
        out.println("  // Copy all form data");
        out.println("  const formData = new FormData(form);");
        out.println("  formData.forEach((value, key) => {");
        out.println("    const input = document.createElement('input');");
        out.println("    input.type = 'hidden';");
        out.println("    input.name = key;");
        out.println("    input.value = value;");
        out.println("    approvalForm.appendChild(input);");
        out.println("  });");
        out.println("  ");
        out.println("  // Add action for revalidate approval");
        out.println("  const actionInput = document.createElement('input');");
        out.println("  actionInput.type = 'hidden';");
        out.println("  actionInput.name = 'action';");
        out.println("  actionInput.value = 'revalidate';");
        out.println("  approvalForm.appendChild(actionInput);");
        out.println("  ");
        out.println("  document.body.appendChild(approvalForm);");
        out.println("  approvalForm.submit();");
        out.println("}");
        out.println("</script>");
        
        out.println("</body>");
        out.println("</html>");
    }
    
  @Override
public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {

    HttpSession session = request.getSession();
    ImmigrantData data = (ImmigrantData) session.getAttribute("immigrantData");

    if (data == null) {
        // No data in session, redirect back
        response.sendRedirect(request.getContextPath() + "/dataEntry.html");
        return;
    }

    String action = request.getParameter("action");

    // Get updated form values
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    String gender = request.getParameter("gender");
    String immigrantID = request.getParameter("immigrant_ID");
    String dependent = request.getParameter("dependent");
    String dob = request.getParameter("dateOfBirth");
    String email = request.getParameter("email");
    String documentRequested = request.getParameter("requestedDocument");
    String notes = request.getParameter("notes");

    // 1. Update the existing object
    data.setFirstName(firstName);
    data.setLastName(lastName);
    data.setGender(gender);
    data.setImmigrantID(immigrantID);
    data.setDependent(dependent);
    data.setDateOfBirth(dob);
    data.setEmail(email);
    data.setDocumentRequested(documentRequested);

    // 2. Append new notes if present
    if (notes != null && !notes.trim().isEmpty()) {
        String existingNotes = data.getNotesFromReviewer();
        if (existingNotes == null || existingNotes.isEmpty()) {
            data.setNotesFromReviewer(notes.trim());
        } else {
            // Add new notes with a separator (e.g., newline)
            data.setNotesFromReviewer(existingNotes + "\n" + notes.trim());
        }
    }

    // Save back to session
    session.setAttribute("immigrantData", data);

    // Added so that notes actually get saved
    Integer formIdObj = (Integer) session.getAttribute("formId");
    if(formIdObj != null){
        int formId = formIdObj;
        try{
            FormDAO.updateReviewerNotes(formId, data.getNotesFromReviewer());
            if("approve".equals(action) || "revalidate".equals(action)){
                FormDAO.updateStatus(formId, "inApproval");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }


    if ("save".equals(action)) {
        response.sendRedirect(request.getContextPath() + "/review");
    } else if ("approve".equals(action) || "revalidate".equals(action)) {
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