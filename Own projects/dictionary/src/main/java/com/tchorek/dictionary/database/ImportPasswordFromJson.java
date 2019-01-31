package com.tchorek.dictionary.database;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportPasswordFromJson {

    public ImportPasswordFromJson(){
    }

    public String importPassword(String inputPath,String inputFileName) {
        final String PATH = inputPath;
        String passwodFileName = inputFileName;

        BufferedReader br = null;
        try {
            if (!new File(PATH + passwodFileName).exists())
                throw new FileNotFoundException("The password file has not been found");

            Gson gson = new Gson();
            br = new BufferedReader(new FileReader(PATH + passwodFileName));

            return gson.fromJson(br, JsonObject.class).get("password").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
