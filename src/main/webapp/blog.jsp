<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog</title>
    <link rel="stylesheet" href="css/blog.css">
</head>

<body>
    <div class="blog">
            <img src="" class="blog-image" alt="Your Image">
     
        <h1 class="title"></h1>
        <p class="writtenBy"></p>
        <p class="timeStamp"></p>
        <p class="description"></p>
    </div>

    <!-- Floating window with buttons and counters -->
    
        <div class="placement">
            <div class="like-button">
                <div class="heart"></div>
                <p class="likes"></p>
            </div>
        </div>
        
        <div class="share-comment-container">
            <img src="img/comment_img.png" class="comment-image" alt="Comment">
            <p class="comments"></p>
            <img src="img/share_img.png" class="share-image" alt="Share">
        </div>
               
        <p class="shares"></p>
 

    <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        const urlParams = new URLSearchParams(window.location.search);
        const titlee = urlParams.get('title');

        // JavaScript code for liking the blog
        $(function () {
            $(".like-button").on("click", function () {
                $(this).find(".heart").toggleClass("is-active");
                likePost(); // Call the likePost function when the like button is clicked
            });
        });

        $(".comment-image").on("click", function () {
            scrollToComments();
        });

        $(".share-image").on("click", function () {
            sharePost();
        });

        function likePost() {
            $.ajax({
                type: 'POST',
                url: 'LikeBlogServlet',
                data: {
                    title: titlee
                },
                success: function (data) {
                    try {
                        console.log("Blog liked");
                        // Update the likes count on the frontend
                        var likesElement = document.querySelector('.likes');
                        var currentLikes = parseInt(likesElement.textContent.split(":")[1]);
                        likesElement.textContent = "Likes: " + (currentLikes + 1);
                    } catch (error) {
                        console.log('Error incrementing likes count: ' + error);
                    }
                },
                error: function () {
                    console.log('Failed to like the blog. Please try again.');
                }
            });
        }

        function scrollToComments() {
            var commentInput = prompt("Enter your comment:");
            if (commentInput) {
                $.ajax({
                    type: 'POST',
                    url: 'CommentBlogServlet',
                    data: {
                        title: titlee,
                        comment: commentInput
                    },
                    success: function (data) {
                        try {
                            console.log("Comment added successfully");
                            // Update the comments count on the frontend
                            var commentsElement = document.querySelector('.comments');
                            var currentComments = parseInt(commentsElement.textContent.split(":")[1]);
                            commentsElement.textContent = "Comments: " + (currentComments + 1);
                        } catch (error) {
                            console.log('Error adding the comment: ' + error);
                        }
                    },
                    error: function () {
                        console.log('Failed to add the comment. Please try again.');
                    }
                });
            }
        }
        
        function sharePost() {
      	  if (navigator.share) {
      	    // Use the Web Share API to share the page
      	    navigator
      	      .share({
      	        title: 'Blog Title', // Replace with your blog title
      	        text: 'Check out this blog post:', // Replace with your message
      	        url: window.location.href,
      	      })
      	      .then(() => {
      	        console.log('Shared successfully.');
      	      })
      	      .catch((error) => {
      	        console.error('Error sharing:', error);
      	      });
      	  } else {
      	    // Fallback: Show a custom sharing dialog
      	    const shareDialog = document.querySelector('.share-dialog');
      	    shareDialog.classList.add('is-open');
      	  }
      	}

        function displayBlogData() {
            $.ajax({
                type: 'GET',
                url: 'OneBlogServlet',
                data: {
                    title: titlee
                },
                dataType: 'json',
                success: function (data) {
                    try {
                        if (data[0] && data[0].comments) {
                            var currentComments = data[0].comments.length;
                            // Update the comments count on the frontend
                            document.querySelector('.comments').textContent = "Comments: " + currentComments;
                        } else {
                            console.log('No comments data found in JSON response.');
                        }
                        
                        // Update the HTML elements with the retrieved data
                        // Update the HTML elements with the retrieved data
						const imgElement = document.querySelector('.blog-image');
						imgElement.src = 'ImageServlet?blogTitle=' + encodeURIComponent(data[0].title);

                        document.querySelector('.title').textContent = data[0].title;
                        document.querySelector('.writtenBy').textContent = "Written By: " + data[0].writtenBy;
                        document.querySelector('.timeStamp').textContent = "Published on: " + data[0].timeStamp;
                        document.querySelector('.description').textContent = data[0].description;
                        document.querySelector('.likes').textContent = "" + data[0].likes;
                        // ... Update other elements as needed
                    } catch (error) {
                        console.log('Error parsing JSON data: ' + error);
                    }
                },
                error: function () {
                    console.log('Failed to retrieve data from the servlet.');
                }
            });
        }

        displayBlogData();
    </script>
</body>
</html>
