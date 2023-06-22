package com.display_user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api2")
public class displayUsers extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String value = request.getParameter("value");
           
    try {
        String driver = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/projectdb";
        String DB_USERNAME = "root";
        String DB_PASSWORD = "charalampos";
        //Join users and addresses
        String sql = "SELECT u.id, u.name, u.surname, u.gender, u.birthdate, a.workAddress, a.homeAddress FROM users u JOIN addresses a ON u.id = a.user_id WHERE u.name = '" + value + "'";
        PrintWriter out = response.getWriter();
        // Load the JDBC driver
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        // Create a statement object
        Statement statement = conn.createStatement();
        // Execute the SQL query to select items
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            // Retrieve the values from the result set
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String gender = resultSet.getString("gender");
            String birthdate = resultSet.getString("birthdate");
            String workAddress = resultSet.getString("workAddress");
            String homeAddress = resultSet.getString("homeAddress");
            out.println("<p>Name: " + name + "</p>");
            out.println("<p>Surname: " + surname + "</p>");
            out.println("<p>Gender: " + gender + "</p>");
            out.println("<p>Birthdate: " + birthdate + "</p>");
            out.println("<p>WorkAdress: " + workAddress + "</p>");
            out.println("<p>HomeAdress: " + homeAddress + "</p>");
            out.close();
        }
        resultSet.close();
        statement.close();
        conn.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
 }
}