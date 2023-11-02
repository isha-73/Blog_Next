package DBConnect;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DBConnection {

    private static final String connectionString = "mongodb+srv://blognest:blognest123@cluster0.xxexgvi.mongodb.net/?retryWrites=true&w=majority";
    private static final String databaseName = "BlogNext";

    private static MongoClient mongoClient;
    private static MongoDatabase database;

  

    public static MongoDatabase getDatabase() {
    	
    	try {
    		 mongoClient = MongoClients.create(connectionString);
    	     database = mongoClient.getDatabase(databaseName);
    	     System.out.println("Connected to MongoDB database: " + databaseName);
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}        return database;
    }
}
