package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import org.bson.types.Binary;

import DBConnect.DBConnection;
import Module.BlogModule;
import Utility.BlogUtility;


@MultipartConfig
public class WriteBlog extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public WriteBlog() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Binary bytes = null;
        
        HttpSession session = req.getSession();
	    String writtenBy = (String) session.getAttribute("loggedInUser");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    String timeStamp = formatter.format(new java.util.Date());
        
        Part filePart = req.getPart("bannerFile");

        // Check if the file is present
        if (filePart != null && filePart.getSize() > 0) {
            // Access the input stream
            InputStream fileContent = filePart.getInputStream();
            bytes = new Binary(fileContent.readAllBytes());
        }

        BlogUtility blog = new BlogUtility();
        blog.setTitle(title);
        blog.setDescription(description);
        blog.setWrittenBy(writtenBy);
        blog.setTimeStamp(timeStamp);
        blog.setUrlImg(bytes);

        BlogModule blogdb = new BlogModule(DBConnection.getDatabase());
        boolean isAdded = blogdb.addBlog(blog);

        PrintWriter out = res.getWriter();

        if (isAdded) {
            out.print("Blog added successfully" + blog);
        } else {
            out.print("Blog not added" + blog);
        }

    }
}