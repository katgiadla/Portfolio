package com.tchorek.dictionary.database;

import com.tchorek.dictionary.properties.NoValueException;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class CreateJson {
    private Document[] doc;

    public void createDocument(String inputWord, String[] translationList, String language) throws NoValueException {
        doc = new Document[translationList.length];
        for(int index =0;index<translationList.length;index++) {
            doc[index]= new Document();
            doc[index].put("word",formatWord(inputWord));
            doc[index].put("translation",formatWord(translationList[index]));
            doc[index].put("Language",formatWord(language));
        }
    }

    private String formatWord(String input) throws NoValueException {
        checkIfNoEmptyValue(input);
        return input.replaceAll("\\s+","").substring(0,1).toUpperCase()+input.replaceAll("\\s+","").substring(1).toLowerCase().replaceAll("\\_+"," ").replaceAll("\\-+"," ");
    }

    private void checkIfNoEmptyValue(String suspicious)throws NoValueException {
        if(suspicious.equals("") )throw new NoValueException("Lack of String in checkIfNoEmptyValue");
    }

        private void checkIfNoEmptyValue(int index)throws NoValueException{
        if(doc[index].get("word").equals("")||doc[index].get("translation").equals("")||doc[index].get("Language").equals(""))throw new NoValueException("Lack of a certain value in index: "+index);
    }

    public Document[] getDoc() throws NoValueException{
        for(int index=0;index<doc.length;index++){
            checkIfNoEmptyValue(index);
        }
        return doc;
    }
}
