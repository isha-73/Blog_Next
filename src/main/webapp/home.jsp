<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blog : Home Page</title>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,400;0,500;0,600;1,600&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="css/home.css">
</head>
<body>

	<!-- NAVBAR -->

	<nav class="navbar">
		<img src="img/blog_logo.png" class="logo" alt="">
		<ul class="links-container">
			<li class="link-item"><a href="home.jsp" class="link">Home</a></li>
			<li class="link-item"><a href="editor.jsp" class="link">Write</a></li>
			<li class="link-item"><a href="read.jsp" class="link">Read</a></li>
			<li class="link-item"><a href="login.jsp" class="login-button">Login</a></li>
		</ul>
	</nav>


	<!-- HEADER -->


	<header class="header">
		<div class="content">
			<h1 class="heading">
				<span class="small">welcome in the world of</span> blog 
				<span class="no-fill">writing</span>
			</h1>
			<a href="editor.jsp" class="btn">write a blog</a>
		</div>
	</header>
	

	<!-- BLOG SECTION CARD (MADE ONE CARD , AS WE WILL MAKE CARDS WITH JSP LATER) -->


	<section class="blogs-section" id="blogContainer">
	
	</section>

</body>

<script>
$(document).ready(function() {
    function truncateText(text, maxLength) {
        if (text.length > maxLength) {
            return text.slice(0, maxLength) + '.....';
        } else {
            return text;
        }
    }

    function displayBlogData() {
        $.ajax({
            type: 'GET',
            url: 'AllBlogsServlet',
            dataType: 'json',
            success: function(data) {
                try {
                    var jsonData = data;
                    
                    jsonData.sort(function(a, b) {
                        if (a['timeStamp'] && b['timeStamp']) {
                            // Assuming 'timestamp' is in the format 'DD/MM/YYYY'
                            const dateA = new Date(a['timeStamp'].split('/').reverse().join('/'));
                            const dateB = new Date(b['timeStamp'].split('/').reverse().join('/'));
                            return dateB - dateA;
                        }
                        return 0; 
                    });
                    
                    
                    var blogContainer = document.getElementById("blogContainer");

                    for (var i = 0; i < jsonData.length; i++) {
                        var blog = jsonData[i];

                        var blogElement = document.createElement("div");
                        blogElement.classList.add("blog-card");

                        blogElement.innerHTML =
                        	'<img src="ImageServlet?blogTitle=' + encodeURIComponent(blog.title) + '" class="blog-image" alt="Blog Image">' +
                            '<h1 class="blog-title">' + blog['title'] + '</h1>' +
                            '<p class="blog-overview">' + truncateText(blog['description'], 100) + '</p>' + 
                            '<p class="blog-writtenBy">Written By: ' + blog['writtenBy'] + '</p>' +
                            '<p class="blog-timestamp">Published on: ' + blog['timeStamp'] + '</p>' +
                            '<a href="/" class="btn dark">read</a>';

                            
                        (function(blog) {
                            blogElement.addEventListener("click", function(event) {
                                // Get the title of the clicked blog associated with the clicked element
                                var clickedBlogTitle = blog['title'];

                                // Redirect to blog.jsp with the title as a query parameter
                                window.location.href = 'blog.jsp?title=' + encodeURIComponent(clickedBlogTitle);
                            });
                        })(blog);
                            
                            
                        blogContainer.appendChild(blogElement);
                    }
                } catch (error) {
                    console.log('Error parsing JSON data: ' + error);
                }
            },
            error: function() {
                console.log('Failed to retrieve data from the servlet.');
            }
        });
    }

    displayBlogData();
});

</script>

</html>