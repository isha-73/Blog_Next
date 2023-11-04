<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog: Editor</title>

    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/editor.css">

    <style>
        .blog form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .blog form .title, .blog form .article {
            margin-bottom: 10px;
        }

        .blog form input[type="submit"] {
            padding: 10px 20px;
            background-color: #000;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
   

    <div class="blog">
        <form action="WriteBlog" method="post">
        
         <div class="banner">
            <input type="file" name="bannerFile" accept="image/*">
            <input type="submit" value="Upload Banner">
        	<img src="<%= request.getAttribute("bannerImage") %>" alt="Uploaded Banner">
    	</div>
    	
            <textarea type="text" class="title" name="title" placeholder="Blog title..." required></textarea>
            <textarea type="text" class="article" name="description" placeholder="Start writing here..." rows="3" cols="50" required></textarea>
            <input type="submit" value="Submit blog">
            
        </form>
    </div>
</body>
</html>
