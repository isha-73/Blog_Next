package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DBConnect.DBConnection;
import Module.UserModule;
import Utility.User;


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
			User user = new User ( name, email, password);
			
			UserModule umod =new UserModule(DBConnection.getDatabase());
			
			boolean u = umod.userRegister(user);
			if(u) {
				System.out.println("Data Added");
			}
			else {
				System.out.println("Data not added");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
