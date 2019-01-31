package com.tchorek.dictionary.data;

import com.google.gson.JsonParser;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class connectionProperties {

    private String inputPassword;

    private MongoClientURI uri;
    private MongoClient mongoClient;
    FindIterable<Document> doc;

    public connectionProperties() throws FileNotFoundException {
        inputPassword = importPassword();
        uri = connectToDB(inputPassword);
        mongoClient = new MongoClient(uri);
        doc = getAllObjects();

    }

    private FindIterable<Document> getAllObjects(){
        return mongoClient.getDatabase("Dictionary").getCollection("Vocabulary").find();
    }

    public static void main(String[] args) throws FileNotFoundException {
        connectionProperties connectionProperties = new connectionProperties();

    }

    private MongoClientURI connectToDB(String inputPassword) {
        return new MongoClientURI(
                "mongodb+srv://mtchorek:" + inputPassword + "@information-collections-mz7lu.mongodb.net/test?retryWrites=true"
        );
    }

    private String importPassword() {
        final String PATH = "E:\\AGH\\Portfolio\\Own projects\\dictionary\\src\\main\\resources\\sensitive\\";
        String passwodFileName = "password.json";

        BufferedReader br = null;

        try {
            if (!new File(PATH + passwodFileName).exists())
                throw new FileNotFoundException("The password file has not been found");

            br = new BufferedReader(new FileReader(PATH + passwodFileName));
            JsonParser parser = new JsonParser();
            String password = regexJava(parser.parse(br).toString());

            return password;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String regexJava(String inputString /*,inputRegex*/) {
        Pattern pattern = Pattern.compile("\"(.*?)\"");
        Matcher matcher = pattern.matcher(inputString.split(":")[1]);
        if (matcher.find()) return matcher.group(1).toString();
        else return null;
    }
}
