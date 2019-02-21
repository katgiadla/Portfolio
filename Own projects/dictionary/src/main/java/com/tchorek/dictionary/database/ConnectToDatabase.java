package com.tchorek.dictionary.database;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
@Configuration
public class ConnectToDatabase {

    private String inputPassword;
    private String[]inputUrlDB;

    private MongoClientURI uri;
    private MongoClient mongoClient;

    // resources
    private final String PATH = "C:\\Private Education\\Portfolio\\Own projects\\dictionary\\src\\main\\resources\\db_access\\";

    public ConnectToDatabase() {

        inputPassword = new ImportPasswordFromJson().importPassword(PATH,"Data1.json");
        inputUrlDB = new GetDatabaseUrl().importDatabaseUrl().split("<PASSWORD>");
        uri = connectToDB(inputPassword);
        mongoClient = new MongoClient(uri);
    }

    private MongoClientURI connectToDB(String inputPassword) {
        return new MongoClientURI(
                inputUrlDB[0] + inputPassword + inputUrlDB[1]
        );
    }

    @Bean
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();

    }
}
