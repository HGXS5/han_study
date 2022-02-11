package cn.han.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MongoDBTest {
    public static void main(String[] args) {
        MongoClient mc = new MongoClient("47.100.77.11", 27017);

        MongoDatabase admin = mc.getDatabase("admin");
        MongoIterable<String> strings = mc.listDatabaseNames();
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
