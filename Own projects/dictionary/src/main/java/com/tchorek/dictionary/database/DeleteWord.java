package com.tchorek.dictionary.database;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.tchorek.dictionary.properties.Permissions;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class DeleteWord {

    public void deleteFile(MongoClient mongoClient, Document doc) {
        switch(Permissions.valueOf(doc.get("Language").toString())){
            case Deleteone: //force delete the first matching word uneffective
                mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").findOneAndDelete(new BasicDBObject().append("word",doc.get("word")));
                break;
            case Deletemany: //force delete every matching word
                mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").deleteMany(new BasicDBObject().append("word",doc.get("word")));
                break;
            default: //normal delete
                mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").findOneAndDelete(doc);
        }
    }
}