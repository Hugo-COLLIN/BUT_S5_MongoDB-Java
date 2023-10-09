package TD4;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Ex1 {
    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("info");
            MongoCollection<Document> contacts = sampleTrainingDB.getCollection("contacts");

            String json = "{nom:Paul}";

            Document doc = Document.parse(json);
            contacts.insertOne(doc);

            mongoClient.close();
        }
    }
}