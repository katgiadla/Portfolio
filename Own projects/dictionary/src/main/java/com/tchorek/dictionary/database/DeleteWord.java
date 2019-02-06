package com.tchorek.dictionary.database;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.tchorek.dictionary.properties.emergencySituations;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class DeleteWord {

    public void deleteFile(MongoClient mongoClient, Document doc) {
        if (doc.get("Language").toString().equals(emergencySituations.Permissions.Delete.toString())) {
            mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").findOneAndDelete(new BasicDBObject().append("word",doc.get("word")));
        } else {
            mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").findOneAndDelete(doc);
        }
    }
}