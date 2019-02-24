package com.tchorek.dictionary.database;

import com.google.gson.Gson;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class UpdateDatabaseUrl {


    private String databaseFilePath;

    public UpdateDatabaseUrl(@Value("${database.path}") String inputPathDatabase){
        this.databaseFilePath = inputPathDatabase;
    }

    public void updateDatabaseUrl(String databaseUrl){
        if(databaseUrl.equals("") || databaseUrl.equals(null)){
            return;
        }


        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(databaseFilePath +"AppProperties.json"));
            Document appPropertiesJson =  new Gson().fromJson(br, Document.class);

            if(appPropertiesJson.get("first_launch").equals(true)){
                appPropertiesJson.put("first_launch",false);
            }
            appPropertiesJson.put("database_url",databaseUrl);

            try(FileWriter file = new FileWriter(databaseFilePath +"AppProperties.json")){
                file.write(appPropertiesJson.toJson());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(br!=null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
