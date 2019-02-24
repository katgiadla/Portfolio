package com.tchorek.dictionary.database;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.io.*;

public class GetDatabasePassword {


    private  String passwordFilePath;


    private void checkPasswordFileExists() throws IOException {
        if(!new File(passwordFilePath +"Data1.json").exists()){
            Document doc = new Document();
            doc.put("password","");
            try(FileWriter file = new FileWriter(passwordFilePath +"Data1.json")){
                file.write(doc.toJson());
            }
            throw new IOException("created password file");
        }
        return;
    }

    public String importPassword(@Value("${password.path}") String inputPath,String inputFileName) {
        BufferedReader br = null;
        this.passwordFilePath = inputPath;

        try {
            checkPasswordFileExists();
            Gson gson = new Gson();
            br = new BufferedReader(new FileReader(inputPath + inputFileName));

            return gson.fromJson(br, JsonObject.class).get("password").getAsString();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if(br!=null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
