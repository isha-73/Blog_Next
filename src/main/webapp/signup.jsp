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

<body>
    <div class="container">
        <form action="User_Registration" method="post">
            <h1>Sign Up Here</h1>
            <label for="username" class="label">User Name:</label><br> 
            <input type="text" name="username"> <br>
            <label for="email" class="label">Email:</label><br> 
            <input type="email" name="email"> <br>
            <label for="password" class="label">Password:</label><br> 
            <input type="password" name="password" autocomplete="off"> <br>
            
            <br><button id="submit" type="submit">Sign Up</button>
        </form>
    </div>
</body>
</html>
