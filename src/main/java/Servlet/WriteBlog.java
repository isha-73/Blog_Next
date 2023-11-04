package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import DBConnect.DBConnection;
import Module.BlogModule;
import Utility.BlogUtility;


public class WriteBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
    public WriteBlog() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String writtenBy = req.getParameter("writtenBy");
		String timeStamp = req.getParameter("timeStamp");
		String imgUrl = req.getParameter("imgurl");
		
		BlogUtility blog = new BlogUtility();
		blog.setTitle(title);
		blog.setDescription(description);
		blog.setWrittenBy(writtenBy);
		blog.setTimeStamp(timeStamp);
		blog.setUrlImg(imgUrl);
		
		BlogModule blogdb= new BlogModule(DBConnection.getDatabase());
		boolean isAdded = blogdb.addBlog(blog);
		
//		 PrintWriter out = res.getWriter();
//        
//		
//		if(isAdded) {
//			 out.print("Blog added succesfully"+blog);
//		}else {
//			 out.print("Blog not added"+blog);
//		}
				
	}

}
