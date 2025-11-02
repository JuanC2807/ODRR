package edu.gmu.cs321;
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dataEntry")
public class dataEntry extends HttpServlet {
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

        // Create a single object holding all data
        ImmigrantData data = new ImmigrantData(firstName, lastName, gender, immigrantID,
                                               dependent, dob, email, documentRequested);

        // Store the object in the session
        HttpSession session = request.getSession();
        session.setAttribute("immigrantData", data);

        // System.out.println("Data saved to session: " + data.getFirstName() + " " + data.getLastName());

        // Redirect to review page
        response.sendRedirect(request.getContextPath() + "/review");

    }
}