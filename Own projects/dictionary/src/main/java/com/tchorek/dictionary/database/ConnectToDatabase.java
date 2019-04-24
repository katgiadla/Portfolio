package com.tchorek.dictionary.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ConnectToDatabase {

    private String inputPassword;
    private String[]inputUrlDB;

    private MongoClientURI uri;
    private MongoClient mongoClient;

    private String databaseFilePath;
    private String passwordFilePath;

    public ConnectToDatabase(@Value("${password.path}") String inputPathToPassword, @Value("${database.path}") String inputPathToDatabase) {
        this.passwordFilePath = inputPathToPassword;
        this.databaseFilePath = inputPathToDatabase;
        launchDbConnection();
    }

    public void launchDbConnection(){
        inputPassword = new GetDatabasePassword().importPassword(passwordFilePath,"Data1.json");
        inputUrlDB = new GetDatabaseUrl(databaseFilePath).importDatabaseUrl().split("<PASSWORD>");
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


}
