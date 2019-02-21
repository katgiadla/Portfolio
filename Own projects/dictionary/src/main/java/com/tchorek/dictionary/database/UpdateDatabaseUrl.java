package com.tchorek.dictionary.database;

import com.google.gson.Gson;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class UpdateDatabaseUrl {
    private final String PATH = "C:\\Private Education\\Portfolio\\Own projects\\dictionary\\src\\main\\java\\com\\tchorek\\dictionary\\properties\\";

    public void updateDatabaseUrl(String databaseUrl){

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(PATH+"AppProperties.json"));
            Document appPropertiesJson =  new Gson().fromJson(br, Document.class);

            if(appPropertiesJson.get("first_launch").equals(true)){
                appPropertiesJson.put("first_launch",false);
            }
            appPropertiesJson.put("database_url",databaseUrl);

            try(FileWriter file = new FileWriter(PATH+"AppProperties.json")){
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
