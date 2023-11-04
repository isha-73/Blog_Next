package Module;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import Utility.UserLogin;

public class UserModule {
    MongoDatabase db;

    public UserModule(MongoDatabase db) {
        this.db = db;
    }
    
    public boolean isUserRegistered(UserLogin user) {
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
