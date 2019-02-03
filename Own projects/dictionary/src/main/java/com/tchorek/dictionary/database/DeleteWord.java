package com.tchorek.dictionary.database;

import com.mongodb.MongoClient;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class DeleteWord {

    public void deleteFile(MongoClient mongoClient, Document doc){
        mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").findOneAndDelete(doc);
    }
}
