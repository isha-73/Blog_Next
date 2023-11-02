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

    public boolean userRegister(User u) {
        boolean isUserAdded = false;

        try {
            MongoCollection<Document> collection = db.getCollection("users");
            Document userDocument = new Document("userName", u.getUserName())
                    .append("password", u.getPassword())
                    .append("email", u.getEmail());

            collection.insertOne(userDocument);
            isUserAdded = true;
            System.out.println("Data added");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isUserAdded;
    }
}