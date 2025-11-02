package edu.gmu.cs321;
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dataEntry")
public class dataEntry extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException{
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String gender = request.getParameter("gender");
            String immigrantID = request.getParameter("immigrant_ID");
            String dependent = request.getParameter("dependent");
            String dob = request.getParameter("dateOfBirth");
            String email = request.getParameter("email");
            String documentRequested = request.getParameter("requestedDocument");


            
            System.out.println("----Check-----");
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(gender);
            System.out.println(immigrantID);
            System.out.println(dependent);
            System.out.println(dob);
            System.out.println(email);
            System.out.println(documentRequested);


            HttpSession session = request.getSession();
            
            session.setAttribute("firstName", firstName);
            session.setAttribute("lastName", lastName);
            session.setAttribute("gender", gender);
            session.setAttribute("immigrantID", immigrantID);
            session.setAttribute("dependent", dependent);
            session.setAttribute("dob", dob);
            session.setAttribute("email", email);
            session.setAttribute("documentRequested", documentRequested);

            //Khalid change this to the name of the file you want the data to go to.
            //response.sendRedirect("review.html");
        }
}