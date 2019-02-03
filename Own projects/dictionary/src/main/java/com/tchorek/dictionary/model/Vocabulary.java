package com.tchorek.dictionary.model;
import org.bson.Document;

public class Vocabulary {
    private String _id;
    private String word;
    private String translation;
    private String language;

    public Vocabulary(Document doc){
        _id = doc.getObjectId("_id").toHexString();
        word = doc.getString("word");
        translation= doc.getString("translation");
        language =  doc.getString("Language");
    }

    public Vocabulary(){
        _id="-1";
        word="none";
        translation="none";
        language="unknown";
    }

    public String get_id() {
        return _id;
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public String getLanguage() {
        return language;
    }

 }
