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
		<form action="Login" method="post">
			<h1>Login Here</h1>
			<label for="name" class="label">Email :</label><br> 
			<input type="email" name="email"> <br>
			<label for="name" class="label">Password :</label><br> 
			<input type="password" name="password" autocomplete="off"> <br>
			
			<br><button id="submit" type="submit"> Login </button>
		</form>
	</div>
</body>
</html>