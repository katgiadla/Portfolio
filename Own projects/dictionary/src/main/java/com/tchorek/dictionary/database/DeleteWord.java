package com.tchorek.dictionary.database;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.tchorek.dictionary.properties.Permissions;
import com.tchorek.dictionary.properties.WrongValueException;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class DeleteWord {

    public void deleteFile(MongoClient mongoClient, Document doc) throws WrongValueException {
        try {
            switch (Permissions.valueOf(doc.get("Language").toString().toUpperCase())) {
                case DELETEONE: //force delete the first matching word uneffective
                    mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").findOneAndDelete(new BasicDBObject().append("word", doc.get("word")));
                    break;
                case DELETEMANY: //force delete every matching word
                    mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").deleteMany(new BasicDBObject().append("word", doc.get("word")));
                    break;
            }
        } catch (IllegalArgumentException e) {
            switch (doc.get("Language").toString()) {
                case "English":
                case "German":
                case "Polish":
                    mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").findOneAndDelete(doc);
                    break;
                default:
                    throw new WrongValueException("Language argument does not suit to the pattern or not included in dictionary scope");
            }
        }
    }
}