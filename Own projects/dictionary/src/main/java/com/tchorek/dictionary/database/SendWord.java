package com.tchorek.dictionary.database;

import com.mongodb.MongoClient;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class SendWord {

    public void sendFile(MongoClient mongoClient,Document doc){
        if(mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").countDocuments(doc)>0) return;
        mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").insertOne(doc);
    }
}