package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import org.bson.types.Binary;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import DBConnect.DBConnection;

public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the blog title from the request parameters
        String blogTitle = request.getParameter("blogTitle");

        // Ensure that the blogTitle is not null
        if (blogTitle == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid blogTitle");
            return;
        }

        // Get the database and the collection where the binary images are stored
        MongoDatabase db = DBConnection.getDatabase();
        MongoCollection<org.bson.Document> collection = db.getCollection("blogs");

        // Find the document with the specified title
        org.bson.Document blog = collection.find(Filters.eq("title", blogTitle)).first();

        if (blog != null) {
            // Get the binary image data from the document
            Binary imageBinary = blog.get("imageUrl", Binary.class);

            if (imageBinary != null) {
                // Set the response content type based on the image format (e.g., image/jpeg, image/png, etc.)
                // You may need to adjust this based on your actual image format.
                response.setContentType("image/jpeg");

                // Get the image data and write it to the response output stream
                try (OutputStream out = response.getOutputStream()) {
                    out.write(imageBinary.getData());
                } catch (IOException e) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to retrieve the image.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found for blogTitle: " + blogTitle);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Blog not found for blogTitle: " + blogTitle);
        }
    }
}
