package com.tchorek.dictionary.database;

import com.tchorek.dictionary.properties.NoValueException;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class CreateJson {
    private Document doc;
    public  void createDocument(String inputWord, String inputTranslation, String language){
        doc = new Document();
        doc.put("word",inputWord);
        doc.put("translation",inputTranslation);
        doc.put("Language",language);
    }

    public Document getDoc() throws NoValueException {
        if(doc.get("word").equals("")||doc.get("translation").equals("")||doc.get("Language").equals("")||doc.get("word").equals(null)||doc.get("translation").equals(null)||doc.get("Language").equals(null))throw new NoValueException("Lack of a certain value in document");
        return doc;
    }
}
