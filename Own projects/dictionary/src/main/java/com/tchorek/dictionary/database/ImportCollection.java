package com.tchorek.dictionary.database;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.io.FileNotFoundException;

public class ImportCollection {

    private FindIterable<Document> doc;
    private MongoClient mongoClient;

    public ImportCollection() {
        mongoClient = new ConnectToDatabase().getMongoClient();
        doc = getAllObjects();
    }

    private FindIterable<Document> getAllObjects(){
        return mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").find();
    }

    public FindIterable<Document> getDoc() {
        return doc;
    }
}
