<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Blog :</title>

<link rel="stylesheet" href="css/blog.css">


</head>

<body>

    <div class="banner"></div>

<div class="blog">

    <img src="path_to_your_image.jpg" class="blog-image" alt="Your Image">
    <h1 class="title"><%= request.getAttribute("title") %></h1>
    <p class="writtenBy">Written by: <%= request.getAttribute("writtenBy") %></p>
    <p class="timeStamp">Time Stamp: <%= request.getAttribute("timeStamp") %></p>
    <p class="description"><%= request.getAttribute("description") %></p>

    <div class="comments">
        <div>
            Enter the comment and the name of the user
        </div>
    </div>
</div>



    <!-- Floating window with buttons and counters -->
    <div class="floating-window">
        <div class="button" onclick="likePost()">Like</div>
        <div class="likes">0</div>
        <div class="button" onclick="scrollToComments()">Comment</div>
        <div class="comments">0</div>
        <div class="button" onclick="sharePost()">Share</div>
        <div class="shares">0</div>
    </div>

    <script>
        function likePost() {
            // Add logic for like functionality
        }

        function scrollToComments() {
            // Add logic to scroll to the comments section
            var commentsSection = document.querySelector('.comments');
            commentsSection.scrollIntoView({ behavior: 'smooth' });
        }

        function sharePost() {
            // Add logic for sharing the post on platforms
        }
        
        window.onscroll = function() {
            var floatingWindow = document.getElementById('floating-window');
            floatingWindow.style.top = (window.pageYOffset + window.innerHeight / 2) + 'px';
        };
    </script>

</body>
</html>