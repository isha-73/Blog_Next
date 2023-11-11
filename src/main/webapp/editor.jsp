<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog: Editor</title>

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
<body><%
    java.util.Date now = new java.util.Date();
    String timestamp = now.toString();
%>
<form action="WriteBlog" method="post" enctype="multipart/form-data"> 
    <div class="blog">
        <div class="banner">
            <input type="file" name="bannerFile" accept="image/*" onchange="previewImage(event)">
            <img id="uploadedImage" src="" alt="Uploaded Banner" style="width: 682px; height: 280px;">
        </div>
        <textarea type="text" class="title" name="title" placeholder="Blog title..." style="margin-top:10px" required></textarea>
        <textarea type="text" class="article" name="description" placeholder="Start writing here..." rows="3" cols="50" required></textarea>
        <input type="hidden" name="timeStamp" value="<%= timestamp %>">
        <input type="submit" calss="submit_button" value="Submit blog">
    </div>
</form>

 

<script>
    function previewImage(event) {
        var reader = new FileReader();
        reader.onload = function() {
            var output = document.getElementById('uploadedImage');
            output.src = reader.result;
        }
        reader.readAsDataURL(event.target.files[0]);
    }
</script>

</body>
</html>