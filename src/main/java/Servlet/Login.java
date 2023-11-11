package Servlet;

import java.io.IOException;

import org.bson.Document;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import DBConnect.DBConnection;
import Module.UserModule;
import Utility.User;




public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	// constructor for calling 
	public Login () {
		super();
	}
	
	
	// do post method to sent the data at login to the database 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		 response.setContentType("application/json"); // Set the content type to JSON

	        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");


            // Connect to the MongoDB database
            MongoDatabase db = DBConnection.getDatabase();
            
     
            
            User user = new User("", password,email);
            // Create a UserModule instance for database operations
            UserModule umod = new UserModule(db);

            // Check if the user exists in the database
            boolean userExists = umod.isUserRegistered(user);


            if (userExists) {
                // User is registered, proceed with login
                System.out.println("User successfully logged in!");
                
                MongoCollection<Document> collection = db.getCollection("users");
                Document query = new Document("email", email);
                Document result = collection.find(query).first();

                String userName = result.getString("username");
                

                // After successful login
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", userName); // Store user information in the session

                // Set an attribute to be forwarded to the JSP
                request.setAttribute("status", "success");
            } else {
                // User is not registered, show an error message
                request.setAttribute("status", "error");
            }

            // Forward the request and response objects to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status", "error");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
        }
    }