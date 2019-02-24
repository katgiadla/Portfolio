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

    private void checkDatabaseExists(String inputDatabaseUrl) throws IOException {
        if(!new File(databaseFilePath +"AppProperties.json").exists()){
            Document doc = new Document();
            doc.put("first_launch",false);
            doc.put("database_url",inputDatabaseUrl);
            try(FileWriter file = new FileWriter(databaseFilePath +"AppProperties.json")){
                file.write(doc.toJson());
            }
        }
        return;
    }

    public void updateDatabaseUrl(String databaseUrl) {
        if(databaseUrl.equals("") || databaseUrl.equals(null)){
            return;
        }
        BufferedReader br = null;

        try {
            checkDatabaseExists(databaseUrl);
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
            System.err.println(e.getMessage());
        }finally {
            try {
                if(br!=null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
