package com.tchorek.dictionary.database;

import com.google.gson.JsonParser;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class importPassword {

    private String password;

    public importPassword(){
       password = importPassword();
    }

    private String importPassword() {
        final String PATH = "E:\\AGH\\Portfolio\\Own projects\\dictionary\\src\\main\\resources\\sensitive\\";
        String passwodFileName = "password.json";

        BufferedReader br = null;

        try {
            if (!new File(PATH + passwodFileName).exists())
                throw new FileNotFoundException("The password file has not been found");

            br = new BufferedReader(new FileReader(PATH + passwodFileName));
            JsonParser parser = new JsonParser();
            String password = regexJava(parser.parse(br).toString());

            return password;
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

    private String regexJava(String inputString /*,inputRegex*/) {
        Pattern pattern = Pattern.compile("\"(.*?)\"");
        Matcher matcher = pattern.matcher(inputString.split(":")[1]);
        if (matcher.find()) return matcher.group(1).toString();
        else return null;
    }

    public String getPassword() {
        return password;
    }
}
