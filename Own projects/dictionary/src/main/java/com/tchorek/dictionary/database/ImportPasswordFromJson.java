package com.tchorek.dictionary.database;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;


public class ImportPasswordFromJson {

    public ImportPasswordFromJson(){
    }

    public String importPassword(String inputPath,String inputFileName) throws FileNotFoundException {
        final String PATH = inputPath;
        String passwodFileName = inputFileName;

        BufferedReader br = null;

        if (!new File(PATH + passwodFileName).exists())
            throw new FileNotFoundException("The password file has not been found");

        try {
            Gson gson = new Gson();
            br = new BufferedReader(new FileReader(PATH + passwodFileName));

            return gson.fromJson(br, JsonObject.class).get("password").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
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
