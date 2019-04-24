package com.tchorek.dictionary.database;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.tchorek.dictionary.model.Vocabulary;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ImportVocabularyCollection {

    private FindIterable<Document> doc;
    private MongoClient mongoClient;
    private ArrayList<Vocabulary> vocabulary = new ArrayList<>();

    public ImportVocabularyCollection(MongoClient mongoClient){
        this.setDbClient(mongoClient);
    }

    public void setDbClient(MongoClient mongoClient){
        this.mongoClient = mongoClient;
        this.checkAndGetDatabaseCollection();    }

    public ArrayList<Vocabulary> checkAndGetDatabaseCollection(){
        this.doc = getAllObjects();
        this.vocabulary.clear();
        this.convertDocumentToVocabulary();
        return this.getVocabulary();
    }

    private FindIterable<Document> getAllObjects() {
        return mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").find();
    }

    private void convertDocumentToVocabulary() {
        for (Document document : doc) vocabulary.add(new Vocabulary(document));
    }

    public ArrayList<Vocabulary> getVocabulary() {
        return vocabulary;
    }
}
