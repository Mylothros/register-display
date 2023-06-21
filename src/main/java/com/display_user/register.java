
package com.display_user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Process form data and store user details in the database

        // ...

        // Send a response to the client
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<link rel=\"stylesheet\" href=\"./styling/homepage.scss\">");
        out.println("<body class=\"register-content\">");
        out.println("<h1>Registration Successful</h1>");
        out.println("<p>Thank you for registering!</p>");
        out.println("<div class=\"home-button\">");
        out.println("<button onclick=\"location.href='homepage.jsp'\">Homepage</button>");
        out.println("</div>");
        out.println("</body></html>");
    }
}

