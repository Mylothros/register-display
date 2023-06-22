<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%! String password="charalampos"; %>
<% 
    // this makes the previous page out of the cached pages.
    response.setHeader("Cache-control","no-cache, no-store, must-revalidate");
    
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb","root",password);
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("SELECT name, surname FROM users;"); 
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Display all users</title>
    <link rel="stylesheet" href="./styling/displayusers.scss">
    <script>
        function sendPostRequest(value) {
            // Create a form dynamically
            var form = document.createElement('form');
            form.method = 'GET';
            form.target = '_blank';
            form.action = './api2';

            // Create an input field to hold the value
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'value';
            input.value = value;

            // Add as a child the input inside the form
            form.appendChild(input);

            // Add the form inside the HTML structure
            document.body.appendChild(form);
            form.submit();
        }
    </script>
</head>
<body>
    <div class="home-button home-button-register">
        <button onclick="location.href='homepage.jsp'">Homepage</button>
    </div>
    <table class="user-table">
        <tr>
            <th>Name</th>
            <th>Surname</th>
        </tr>
        <% while (rs.next()) { %>
            <tr class="exclude-tr" onclick="sendPostRequest('<%= rs.getString("name") %>')">
                <th><%= rs.getString("name") %></th>
                <th><%= rs.getString("surname") %></th>
            </tr>
        <% } %>
    </table>
</body>
</html>