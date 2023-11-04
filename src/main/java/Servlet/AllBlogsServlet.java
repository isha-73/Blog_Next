package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import DBConnect.DBConnection;

import com.mongodb.client.FindIterable;

public class AllBlogsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	MongoDatabase db= DBConnection.getDatabase();
        try {
        	MongoCollection<Document> collection = db.getCollection("blogs");
            FindIterable<Document> cursor = collection.find();


            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("[");
            
            MongoCursor<Document> iterator = cursor.iterator();
            
            while (iterator.hasNext()) {
                Document blog = iterator.next();
                jsonBuilder.append(blog.toJson());
                if (iterator.hasNext()) {
                    jsonBuilder.append(",");
                }
            }

            jsonBuilder.append("]");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jsonBuilder.toString());
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // You can implement doPost method if you want to handle POST requests
}
