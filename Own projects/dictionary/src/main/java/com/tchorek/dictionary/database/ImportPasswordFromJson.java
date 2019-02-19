package com.tchorek.dictionary.database;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bson.Document;

import java.io.*;


public class ImportPasswordFromJson {

    private void checkPasswordFileExists() throws IOException {
        if(!new File("E:\\AGH\\Portfolio\\Own projects\\dictionary\\src\\main\\resources\\db_access\\Data1.json").exists()){
            Document doc = new Document();
            doc.put("password","");
            try(FileWriter file = new FileWriter("E:\\AGH\\Portfolio\\Own projects\\dictionary\\src\\main\\java\\com\\tchorek\\dictionary\\properties\\AppProperties.json")){
                file.write(doc.toJson());
            }
            throw new IOException("created password file");
        }
        return;
    }


    public String importPassword(String inputPath,String inputFileName) {
        BufferedReader br = null;

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
