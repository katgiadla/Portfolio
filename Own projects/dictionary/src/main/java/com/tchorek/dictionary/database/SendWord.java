package com.tchorek.dictionary.database;

import com.mongodb.MongoClient;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class SendWord {

    public void sendFile(MongoClient mongoClient,Document documentArray[]) {
        for (Document document : documentArray) {
            if (mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").countDocuments(document) > 0) return;
            mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").insertOne(document);
        }
    }
}