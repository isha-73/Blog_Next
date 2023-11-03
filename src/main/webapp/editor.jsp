<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog:Editor</title>

    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/editor.css">
</head>
<body>
    <div class="banner">
        <form action="upload.jsp" method="post" enctype="multipart/form-data">
            <input type="file" name="bannerFile" accept="image/*">
            <input type="submit" value="Upload Banner">
        </form>
        <img src="<%= request.getAttribute("bannerImage") %>" alt="Uploaded Banner">
    </div>

    <div class="blog">
        <textarea type="text" class="title" placeholder="Blog title..."></textarea>
        <textarea type="text" class="article" placeholder="Start writing here..."></textarea>
    </div>

    <div class="blog-options">
        <form action="upload.jsp" method="post" enctype="multipart/form-data">
            <input type="submit" value="Submit blog">
        </form>
    </div>
</body>
</html>
