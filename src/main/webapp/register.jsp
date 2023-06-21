<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register New User</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
    <script>
        $(function() {
            $("#birthdate").datepicker({ dateFormat: 'yy-mm-dd' });
        });

        function validateForm() {
            var name = document.getElementById("name").value;
            var surname = document.getElementById("surname").value;
            var gender = document.getElementById("gender").value;
            var birthdate = document.getElementById("birthdate").value;

            var errors = 0;

            if (name === "") {
                document.getElementById("nameError").innerHTML = "<span class='error'>Please fill in the Name field.</span>";
                errors++;
            } else {
                document.getElementById("nameError").innerHTML = "";
            }

            if (surname === "") {
                document.getElementById("surnameError").innerHTML = "<span class='error'>Please fill in the Surname field.</span>";
                errors++;
            } else {
                document.getElementById("surnameError").innerHTML = "";
            }

            if (gender === "") {
                document.getElementById("genderError").innerHTML = "<span class='error'>Please select a Gender.</span>";
                errors++;
            } else {
                document.getElementById("genderError").innerHTML = "";
            }

            if (birthdate === "") {
                document.getElementById("birthdateError").innerHTML = "<span class='error'>Please fill in the Birthdate field.</span>";
                errors++;
            } else {
                document.getElementById("birthdateError").innerHTML = "";
            }

            if (errors > 0) {
                return false;
            }
        }
    </script>
    <link rel="stylesheet" href="./styling/register.scss">
</head>
<body>
    <h1>Register New User</h1>
    <form action="./api" onsubmit="return validateForm()" method="POST">
        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name">
            <span id="nameError"></span>
        </div>
        <div>
            <label for="surname">Surname:</label>
            <input type="text" id="surname" name="surname">
            <span id="surnameError"></span>
        </div>
        <div>
            <label for="gender">Gender:</label>
            <select id="gender" name="gender">
                <option value="">Select</option>
                <option value="M">Male</option>
                <option value="F">Female</option>
            </select>
            <span id="genderError"></span>
        </div>
        <div>
            <label for="birthdate">Birthdate:</label>
            <input type="text" id="birthdate" name="birthdate">
            <span id="birthdateError"></span>
        </div>
        <div>
            <label for="workAddress">Work Address:</label>
            <textarea id="workAddress" name="workAddress"></textarea>
        </div>
        <div>
            <label for="homeAddress">Home Address:</label>
            <textarea id="homeAddress" name="homeAddress"></textarea>
        </div>
        <input type="submit" value="Register">
    </form>
</body>
</html>
