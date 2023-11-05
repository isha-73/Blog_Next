<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blog : Login Page</title>
<link
    href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,600;1,600&display=swap"
    rel="stylesheet">
<link rel="stylesheet" href="css/login.css">
</head>

<body>

    <div class="container">
        <form action="Login" method="post" name="loginForm" onsubmit="return validateForm()">
            <h1>Login Here</h1>
            <label for="name" class="label">Email :</label><br> 
            <input type="email" name="email"> <br>
            <label for="name" class="label">Password :</label><br> 
            <input type="password" name="password" autocomplete="off"> <br>
                        
            <div style="margin-top:10px"><span>New Member?  </span><a href="signup.jsp" class="register-link">Register Here!</a></div>
            <br><button id="submit" type="submit"> Login </button>
        </form>
    </div>    
</body>
<% 
    String status = (String) request.getAttribute("status");
    if (status != null) {
        if (status.equals("success")) {
%>
            <script>alert("Successfully logged in!");</script>
            <script>
            // Redirect to the home page
            window.location.replace("home.jsp");
        </script>
<%
        } else if (status.equals("error")) {
%>
            <script>alert("Make sure you entered correct username and password!!!");</script>
<%
        }
    }
%>
<script>
    function validateForm() {
        var email = document.forms["loginForm"]["email"].value;
        var password = document.forms["loginForm"]["password"].value;

        if (email === "" || password === "") {
            alert("Email and Password must be filled out");
            return false;
        }
 
 
    }
    
</script>
</html>