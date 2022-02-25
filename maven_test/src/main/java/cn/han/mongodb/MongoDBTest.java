package cn.han.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

public class MongoDBTest {
    public static void main(String[] args) {
//        MongoClient mc = new MongoClient("47.100.77.11", 27017);
        MongoClientURI connectionString = new MongoClientURI("mongodb://root:Han#222@47.100.77.11:27017");
        MongoClient mongoClient = new MongoClient(connectionString);
        ListDatabasesIterable<Document> documents = mongoClient.listDatabases();
        for (Document document : documents) {
            System.out.println(document.toJson());
        }
        MongoDatabase han = mongoClient.getDatabase("han");
        String name = han.getName();
        System.out.println(name);

    }
}
