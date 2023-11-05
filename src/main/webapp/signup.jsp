<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blog: Sign Up Page</title>
<link
    href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,600;1,600&display=swap"
    rel="stylesheet">
<link rel="stylesheet" href="css/signup.css">
</head>

<div class="container">
        <form action="User_Registration" method="post" onsubmit="return validateForm()">
            <h1>Sign Up Here</h1>
            <label for="username" class="label">User Name:</label><br>
            <input type="text" name="username" id="username"> <br>
            <label for="email" class="label">Email:</label><br>
            <input type="email" name="email" id="email"> <br>
            <label for="password" class="label">Password:</label><br>
            <input type="password" name="password" id="password" autocomplete="off"> <br>

            <br><button id="submit" type="submit">Sign Up</button>
        </form>
    </div>

    <script>
        function validateForm() {
            var username = document.getElementById("username").value;
            var email = document.getElementById("email").value;
            var password = document.getElementById("password").value;

            if (username === "" || email === "" || password === "") {
                alert("All fields must be filled out");
                return false;
            }
            return true;
        }
    </script>
    
<% 
    String status = (String) request.getAttribute("status");
    if (status != null) {
        if (status.equals("success")) {
%>
            <script>alert("Successfully registered!");</script>
            <script>
            window.location.replace("login.jsp");
        </script>
<%
        } else if (status.equals("error")) {
%>
            <script>alert("User already registered. Please log in.");</script>
<%
        }
    }
%>
</html>