package com.tchorek.dictionary.database;

import com.google.gson.Gson;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class UpdateDatabasePassword {


    private String passwordFilePath;

    public UpdateDatabasePassword(@Value("${password.path}") String inputPasswordPath){
        this.passwordFilePath = inputPasswordPath;
    }

    private void checkPasswordFileExists(String inputPassword) throws IOException {
        if(!new File(passwordFilePath +"Data1.json").exists()){
            Document doc = new Document();
            doc.put("password",inputPassword);
            try(FileWriter file = new FileWriter(passwordFilePath +"Data1.json")){
                file.write(doc.toJson());
            }
        }
        return;
    }

    public void updatePassword(String inputPassword) {
        if(inputPassword.equals("")|| inputPassword.equals(null)){
            return;
        }

        BufferedReader br = null;
        try {
            checkPasswordFileExists(inputPassword);

            br = new BufferedReader(new FileReader(passwordFilePath +"Data1.json"));
            Document data1Json =  new Gson().fromJson(br, Document.class);

            data1Json.put("password",inputPassword);

            try(FileWriter file = new FileWriter(passwordFilePath +"Data1.json")){
                file.write(data1Json.toJson());
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
