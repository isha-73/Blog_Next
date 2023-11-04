package Servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.mongodb.client.MongoDatabase;

import DBConnect.DBConnection;
import Module.UserModule;
import Utility.UserLogin;

public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	// constructor for calling 
	public Login () {
		super();
	}
	
	
	// do post method to sent the data at login to the database 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UserLogin user = new UserLogin(email, password);

            // Connect to the MongoDB database
            MongoDatabase db = DBConnection.getDatabase();

            // Create a UserModule instance for database operations
            UserModule umod = new UserModule(db);

            // Check if the user exists in the database
            boolean userExists = umod.isUserRegistered(user);

            if (userExists) {
                // User is registered, proceed with login
            	System.out.println("User successfully logged in!");
            	
            	// After successful login
            	HttpSession session = request.getSession();
            	session.setAttribute("loggedInUser", user.getEmail()); // Store user information in the session

            	
            	// Redirect to the welcome page
                response.sendRedirect("home.jsp");
            	
            } else {
                // User is not registered, show an error message
                request.setAttribute("error", "User not registered. Please sign up first.");
                
                /* Redirect to a sign up page */
                response.sendRedirect("signup.jsp");
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
