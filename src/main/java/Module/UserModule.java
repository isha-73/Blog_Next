package Module;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import Utility.User;


public class UserModule {
    MongoDatabase db;

    public UserModule(MongoDatabase db) {
        this.db = db;
    }
    
    // Add a new user to the database
    public void addUser(User user) {
        MongoCollection<Document> collection = db.getCollection("users");
        Document newUser = new Document("username", user.getUserName())
                .append("email", user.getEmail())
                .append("password", user.getPassword());
        collection.insertOne(newUser);
    }
    
    
    public boolean isUserRegistered(User user) {
        boolean isUserExists = false;

        try {
            MongoCollection<Document> collection = db.getCollection("users");
            Document query = new Document("email", user.getEmail())
                .append("password", user.getPassword());
            
            long count = collection.countDocuments(query);
            if (count > 0) {
                isUserExists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isUserExists;
    }
    
    
  
}