package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import DBConnect.DBConnection;


public class OneBlogServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Get the blog title from the request
        String blogTitle = request.getParameter("title");
        

        MongoDatabase db = DBConnection.getDatabase();
        try {
            MongoCollection<Document> collection = db.getCollection("blogs");

            // Define a query to find the blog by its title
            Document blogQuery = new Document("title", blogTitle);
            Document blog = collection.find(blogQuery).first();

            if (blog != null) {
                // Create a JSON array with a single entry
                StringBuilder jsonBuilder = new StringBuilder();
                jsonBuilder.append("[");
                jsonBuilder.append(blog.toJson());
                jsonBuilder.append("]");

                // Send the JSON response
                out.print(jsonBuilder.toString());
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                // Handle the case where the blog is not found
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Blog not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
        } finally {
            out.flush();
            out.close();
        }
    }
}