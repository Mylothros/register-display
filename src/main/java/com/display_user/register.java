package com.display_user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api")
public class register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("birthdate");
        String workAddress = request.getParameter("workAddress");
        String homeAddress = request.getParameter("homeAddress");
        // Perform validation which will take you back to register.jsp and the error will be in the url
        if (name.isEmpty() || surname.isEmpty() || gender.isEmpty() || birthdate.isEmpty()) {
            response.sendRedirect("register.jsp?error=Fill the mandatory fields.");
            return;
        }
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String DB_URL = "jdbc:mysql://localhost:3306/projectdb";
            String DB_USERNAME = "root";
            String DB_PASSWORD = "charalampos";
            UUID uuid = UUID.randomUUID();
            String randomId = uuid.toString();
            // Load the JDBC driver
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            // Prepare the SQL statement
            String sqlusers = "INSERT INTO users (id, name, surname, gender, birthdate) VALUES " + "('" + randomId + "', '" + name + "', '" + surname + "', '" + gender + "', '" + birthdate + "')";
            PreparedStatement statement = conn.prepareStatement(sqlusers);
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<link rel=\"stylesheet\" href=\"./styling/register.scss\">");
            out.println("<body class=\"register-content\">");
            out.println("<h1>Registration Successful</h1>");
            out.println("<p>Name: " + name + "</p>");
            out.println("<p>Surname: " + surname + "</p>");
            out.println("<p>Gender: " + gender + "</p>");
            out.println("<p>Birthdate: " + birthdate + "</p>");
            out.println("<p>Work Address: " + workAddress + "</p>");
            out.println("<p>Home Address: " + homeAddress + "</p>");
            out.println("<div class=\"home-button\">");
            out.println("<button onclick=\"location.href='homepage.jsp'\">Homepage</button>");
            out.println("</div>");
            out.println("</body></html>");
            out.close();
            // Execute the SQL query to modify data in the database
            statement.executeUpdate();
            statement.close();
            String sqladress = "INSERT INTO addresses (user_id, workAddress, homeAddress) VALUES " + "('" + randomId + "', '" + workAddress + "', '" + homeAddress + "')";     
            PreparedStatement statement2 = conn.prepareStatement(sqladress);
            statement2.executeUpdate();
            statement2.close();
            conn.close();
            
        }  catch (Exception e) {
        e.printStackTrace();
    }
    }
}