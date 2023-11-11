package Servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
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
	            String email = request.getParameter("email");
	            String password = request.getParameter("password");
	            User user = new User(name, password, email);

	            UserModule umod = new UserModule(DBConnection.getDatabase());

	            boolean userExists = umod.isUserRegistered(user);

	            if (!userExists) {
	                umod.addUser(user);

	                // Set an attribute for successful registration
	                request.setAttribute("status", "success");
	                RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
	                dispatcher.forward(request, response);
	            } else {
	                // User is already registered, show an error message
	                request.setAttribute("status", "error");
	                RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
	                dispatcher.forward(request, response);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("status", "error");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
	            dispatcher.forward(request, response);
	        }
	}

}