/**
 * Hugo COLLIN - 09/10/2023
 */

import com.mongodb.client.model.Filters;
import org.bson.*;
import org.bson.conversions.Bson;
import org.bson.types.*;
import com.mongodb.client.*;

public class JavaMongoConnection {
    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("info");
            MongoCollection<Document> products = sampleTrainingDB.getCollection("produits");


            //Q3.1
            System.out.println("--- Q3.1 ---");
            FindIterable<Document> allProducts = products.find();
            for (Document product : allProducts)
                System.out.println(product.toJson());

            //Q3.2
            System.out.println("--- Q3.2 ---");
            Document firstProduct = products.find().first();
            assert firstProduct != null;
            System.out.println(firstProduct.toJson());
            System.out.println("---");

            //Q3.3
            System.out.println("--- Q3.3 ---");
            Bson filterThinkpad = Filters.eq("_id", new ObjectId("6523b01d9062348d1cd73750"));
            FindIterable<Document> thinkpad = products.find(filterThinkpad);

            for (Document doc : thinkpad)
                System.out.println(doc.toJson());


            //Q3.4
            System.out.println("--- Q3.4 ---");
            Bson filterPriceGt = Filters.gt("prix", 13723);
            FindIterable<Document> gt13723 = products.find(filterPriceGt);

            for (Document doc : gt13723)
                System.out.println(doc.toJson());


            //Q3.5
            System.out.println("--- Q3.5 ---");
            Bson filterUltrabook = Filters.eq("ultrabook", true);
            FindIterable<Document> ultrabooks = products.find(filterUltrabook);

            assert ultrabooks.first() != null;
            System.out.println(ultrabooks.first().toJson());


            //Q3.6
            System.out.println("--- Q3.6 ---");
            Bson filterMacbook = Filters.regex("nom", "Macbook");
            FindIterable<Document> macbook = products.find(filterMacbook);

            assert macbook.first() != null;
            System.out.println(macbook.first().toJson());


            //Q3.7
            System.out.println("--- Q3.7 ---");
            Bson filterStartWithMacbook = Filters.regex("nom", "^Macbook");
            FindIterable<Document> macbooks = products.find(filterStartWithMacbook);

            for (Document doc : macbooks)
                System.out.println(doc.toJson());


            //Q3.8
            System.out.println("--- Q3.8 ---");
            Bson filterApple = Filters.eq("fabriquant", "Apple");
            products.deleteMany(filterApple);

            //Check Apple products are deleted
            for (Document product : allProducts)
                System.out.println(product.toJson());

            //Q3.9
            System.out.println("--- Q3.9 ---");
            Bson filterLenovo = Filters.eq("_id", new ObjectId("6523b01d9062348d1cd73750"));
            products.deleteOne(filterLenovo);

            //Check Lenovo product is deleted
            for (Document product : allProducts)
                System.out.println(product.toJson());

            System.out.println("---");

            mongoClient.close();
        }
    }
}