package com.tchorek.dictionary.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class ConnectToDatabase {

    private String inputPassword;
    private String[]inputUrlDB;

    private MongoClientURI uri;
    private MongoClient mongoClient;

    public ConnectToDatabase() {
        inputPassword = new ImportPasswordFromJson().importPassword("E:\\AGH\\Portfolio\\Own projects\\dictionary\\src\\main\\resources\\db_access\\","Data1.json");
        inputUrlDB = new GetDatabaseUrl().importDatabaseUrl().split("<PASSWORD>");
        uri = connectToDB(inputPassword);
        mongoClient = new MongoClient(uri);
    }

    private MongoClientURI connectToDB(String inputPassword) {
        return new MongoClientURI(
                inputUrlDB[0] + inputPassword + inputUrlDB[1]
        );
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }
}
