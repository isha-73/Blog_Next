package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import DBConnect.DBConnection; // Ensure you have the correct package for DBConnection


public class CommentBlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the blog title and comment from the request
            String blogTitle = request.getParameter("title");
            String comment = request.getParameter("comment");

            // Get a reference to your MongoDB collection
            MongoDatabase db = DBConnection.getDatabase(); // Ensure you have a valid DBConnection class

            if (db != null) {
                MongoCollection<Document> collection = db.getCollection("blogs");

                // Define the query to find the blog by its title
                Document blogQuery = new Document("title", blogTitle);
                Document blog = collection.find(blogQuery).first();

                if (blog != null) {
                    // Update the comments and commentCount
                    ArrayList<String> currentComments = (ArrayList<String>) blog.get("comments");
                    currentComments.add(comment);
                    int currentCommentCount = currentComments.size();

                    collection.updateOne(blogQuery, new Document("$set", new Document("comments", currentComments)
                            .append("commentCount", currentCommentCount)));
                    System.out.println("Comment added");
                } else {
                    System.out.println("Blog not found");
                }
            } else {
                System.out.println("Failed to connect to the MongoDB database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}