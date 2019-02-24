package com.tchorek.dictionary.database;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

@Component
@Configuration
public class GetDatabaseUrl {
    /** TODO: 14.02.2019
     *  The function will receive 2 arguments
     *  First: database url
     *  Second: password
     *  It will update AppProperties.json file and change first_launch boolean
     *  It will create Data1.json file with current password
     *      If Data1.json exist it will swap previous password with the current
     *
     *  Add javascript that will check AppProperties.json file in order to pop the pop-window
    */


    private String databaseFilePath;

    public GetDatabaseUrl(@Value("${database.path}") String inputDatabasePath){
        this.databaseFilePath = inputDatabasePath;
    }

    private void checkPropertiesFileExists() throws IOException {

        if(!new File(databaseFilePath +"AppProperties.json").exists()){
            Document doc = new Document();
            doc.put("first_launch",true);
            doc.put("database_url","");
            try(FileWriter file = new FileWriter(databaseFilePath +"AppProperties.json")){
                file.write(doc.toJson());
            }
            throw new IOException("created new Properties File");
        }
    }

    public String importDatabaseUrl(){

        BufferedReader br = null;
        try{
            checkPropertiesFileExists();
            br = new BufferedReader(new FileReader(databaseFilePath +"AppProperties.json"));

            return new Gson().fromJson(br, JsonObject.class).get("database_url").getAsString();

        }catch(IOException aa ){
            System.err.println(aa.getMessage());
        }finally {
            try {
                if(br!=null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
