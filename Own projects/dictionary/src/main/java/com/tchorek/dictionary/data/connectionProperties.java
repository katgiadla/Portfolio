package com.tchorek.dictionary.data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.mongodb.MongoClientURI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class connectionProperties {

    private String inputPassword;

    private MongoClientURI uri;

    public connectionProperties() throws FileNotFoundException {
        inputPassword = importPassword();
        uri = new MongoClientURI(
                "mongodb+srv://mtchorek:"+inputPassword+"@information-collections-mz7lu.mongodb.net/test?retryWrites=true"
        );
    }

    private String importPassword() throws FileNotFoundException{

        try {
            BufferedReader br = new BufferedReader(new FileReader("E:\\AGH\\Portfolio\\Own projects\\dictionary\\src\\main\\resources\\sensitive\\password.json"));
            JsonParser parser = new JsonParser();
            String element = parser.parse(br).toString();//note getAsString() throws  "java.lang.UnsupportedOperationException: JsonObject"
            Pattern pattern = Pattern.compile("\"(.*?)\"");
            Matcher matcher = pattern.matcher(element.split(":")[1]);
            if(matcher.find()) return matcher.group(1).toString();
            else return null;
        }catch(FileNotFoundException e){
            throw new FileNotFoundException("The password file has not been included in github");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        connectionProperties connectionProperties = new connectionProperties();
    }
}
