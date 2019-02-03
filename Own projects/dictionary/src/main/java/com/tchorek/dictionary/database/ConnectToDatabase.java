package com.tchorek.dictionary.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class ConnectToDatabase {

    private String inputPassword;

    private MongoClientURI uri;
    private MongoClient mongoClient;

    public ConnectToDatabase() throws FileNotFoundException {
        inputPassword = new ImportPasswordFromJson().importPassword("E:\\AGH\\Portfolio\\Own projects\\dictionary\\src\\main\\resources\\db_access\\","data1.json");
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
