package Servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import DBConnect.DBConnection;
import Module.UserModule;
import Utility.User;

/**
 * Servlet implementation class User_Registration
 */


public class User_Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public User_Registration() {
		super();
		// TODO Auto-generated constructor stub
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String name = request.getParameter("username");
			String email =  request.getParameter("email");
			String password = request.getParameter("password");
			User user = new User(name, email, password);

			UserModule umod = new UserModule(DBConnection.getDatabase());

			/*  The isUserRegistered method is created to */


			// Check if the user exists in the database to further make changes
			boolean userExists = umod.isUserRegistered(user);

			if (userExists) {
				// User is registered, proceed with login
				System.out.println("User successfully logged in!");

				// After successful sign-up
				HttpSession session = request.getSession();
				session.setAttribute("loggedInUser", user.getEmail()); // Store user information in the session

				// Redirect to the sign up page
				response.sendRedirect("login.jsp");

			} else {
				// User is not registered, show an error message
				request.setAttribute("error", "User not registered. Please sign up first.");

				/* Redirect to a sign up page */
				response.sendRedirect("signup.jsp");
			}


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
