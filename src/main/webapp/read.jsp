<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Blogs</title>
<link rel="stylesheet" href="css/read.css">
</head>
<body>
	<nav class="navbar">
 		<img src="img/blog_logo.png" class="logo" alt="">

		<ul class="links-container">
			<li class="link-item"><a href="home.jsp" class="link">home</a></li>
			<li class="link-item"><a href="editor.jsp" class="link">Write</a></li>
			<li class="link-item"><a href="read.jsp" class="link">Read</a></li>
		</ul>
	</nav>
	
	<section class="blog" id="blog">
		<div class="container">
			<div class="title">
				<h2>Latest Blog</h2>
				<p>recent blogs about art & design</p>
			</div>
			<div class="blog-content" id="all_blog_container">
				
			<!--  	<div class="blog-img">
						<img src="img/header_img.jpg" alt=""> <span><i
							class="far fa-heart"></i></span>
					</div>
					<div class="blog-text">
						<span>20 January, 2020</span>
						<h2>Lorem ipsum, dolor sit amet consectetur adipisicing</h2>
						<p>Lorem ipsum dolor, sit amet consectetur adipisicing elit.
							Omnis libero quas ipsum laudantium nihil! Quaerat.</p>
						<a href="#">Read More</a>
					</div>-->
				
			</div>
		</div>
	</section>
</body>


<script>
$(document).ready(function() {
    function truncateText(text, maxLength) {
        if (text && text.length > maxLength) { // Check if 'text' is not null or undefined
            return text.slice(0, maxLength) + '.....';
        } else if (text) { // Check if 'text' is not null or undefined
            return text;
        } else {
            return ''; // Handle the case where 'text' is null or undefined
        }
    }

    function displayBlogData() {
        $.ajax({
            type: 'GET',
            url: 'AllBlogsServlet',
            dataType: 'json',
            success: function(data) {
                try {
                    if (data && data.length > 0) { // Check if 'data' is not null or undefined
                        var jsonData = data;
                        
                        //SORTING IN DESCENDING ORDER ON BASIS OF LIKES
                        jsonData.sort(function(a, b) {
                            return b['likes'] - a['likes']; // Sort in descending order
                        });
                        
                        var blogContainer = document.getElementById("all_blog_container");
                        console.log(data);
                        for (var i = 0; i < jsonData.length; i++) {
                            var blog = jsonData[i];
							
                            var blogElement = document.createElement("div");
                            blogElement.classList.add("blog-item"); // Change the class name to match your CSS

                            blogElement.innerHTML =
                                '<div class="blog-img">' +
                                '<img src="ImageServlet?blogTitle=' + encodeURIComponent(blog.title) + '" class="blog-image" alt="Blog Image">'+
                                '<span><i class="far fa-heart"></i></span>' +
                                '</div>' +
                                '<div class="blog-text">' +
                                '<span>Published on: ' + blog['timeStamp'] + '</span>' + 
                                '<h2>' + blog['title'] + '</h2>' + 
                                '<p>' + truncateText(blog['description'], 100) + '</p>' + 
                                '<p>Written By: ' + blog['writtenBy'] + '</p>' + 
                                '<a href="/" class="btn dark">read</a>' + 
                                '</div>';

                            blogContainer.appendChild(blogElement);
                        }
                    } else {
                        console.log('Data is null or empty.');
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