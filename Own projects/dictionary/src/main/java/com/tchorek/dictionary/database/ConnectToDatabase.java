package com.tchorek.dictionary.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class ConnectToDatabase {

    private String inputPassword;

    private MongoClientURI uri;
    private MongoClient mongoClient;

    public ConnectToDatabase() {
        inputPassword = new importPassword().getPassword();
        uri = connectToDB(inputPassword);
        mongoClient = new MongoClient(uri);
    }

    private MongoClientURI connectToDB(String inputPassword) {
        return new MongoClientURI(
                "mongodb+srv://mtchorek:" + inputPassword + "@information-collections-mz7lu.mongodb.net/test?retryWrites=true"
        );
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }
}
