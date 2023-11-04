package Module;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

import Utility.BlogUtility;
import java.util.*;


public class BlogModule {
    MongoDatabase db;

    public BlogModule(MongoDatabase db) {
        this.db = db;
    }

    public boolean addBlog(BlogUtility blog) {
        boolean isblogAdded = false;

        try {
            MongoCollection<Document> collection = db.getCollection("blogs");
            
            Document blogDoc = new Document("writtenBy", blog.getWrittenBy())
            		.append("title", blog.getTitle()).append("description", blog.getDescription())
            		.append("timeStamp", blog.getTimeStamp()).append("imageUrl", blog.getUrlImg());
            
            collection.insertOne(blogDoc);
            isblogAdded= true;
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isblogAdded;
    }
  
    public void likeBlog(String blogTitle) {
        try {
            MongoCollection<Document> collection = db.getCollection("blogs");
            Document blogQuery = new Document("title", blogTitle);
            Document blog = collection.find(blogQuery).first();

            if (blog != null) {
                int currentLikes = blog.getInteger("likes", 0);
                collection.updateOne(blogQuery, new Document("$set", new Document("likes", currentLikes + 1)));
                System.out.println("Blog liked");
            } else {
                System.out.println("Blog not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void commentBlog(String blogTitle, String comment) {
    	
        try {
            MongoCollection<Document> collection = db.getCollection("blogs");
            Document blogQuery = new Document("title", blogTitle);
            Document blog = collection.find(blogQuery).first();

            if (blog != null) {
                ArrayList<String> currentComments = (ArrayList<String>) blog.get("comments");
                currentComments.add(comment);
                int currentCommentCount = blog.getInteger("commentCount", 0);
                collection.updateOne(blogQuery, new Document("$set", new Document("comments", currentComments)
                        .append("commentCount", currentCommentCount + 1)));
                System.out.println("Comment added");
            } else {
                System.out.println("Blog not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}