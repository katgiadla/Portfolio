package com.tchorek.dictionary.database;

import com.google.gson.Gson;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class UpdateDatabasePassword {
    private final String PATH = "C:\\Private Education\\Portfolio\\Own projects\\dictionary\\src\\main\\resources\\db_access\\";

    public void updatePassword(String inputPassword){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(PATH+"Data1.json"));
            Document data1Json =  new Gson().fromJson(br, Document.class);

            data1Json.put("password",inputPassword);

            try(FileWriter file = new FileWriter(PATH+"Data1.json")){
                file.write(data1Json.toJson());
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
