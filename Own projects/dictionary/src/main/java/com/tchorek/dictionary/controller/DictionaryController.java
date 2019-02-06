package com.tchorek.dictionary.controller;

import com.mongodb.MongoSocketReadException;
import com.tchorek.dictionary.database.*;
import com.tchorek.dictionary.properties.NoValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PreDestroy;

@Controller
@Component
public class DictionaryController{

    private final ImportVocabularyCollection importCollection;

    private final SendWord sendWord;

    private final CreateJson createJson;

    private final ConnectToDatabase connectToDatabase;

    private final DeleteWord deleteWord;

    @Autowired
    public DictionaryController(SendWord sendWord, CreateJson createJson, ConnectToDatabase connectToDatabase, DeleteWord deleteWord) {
        this.sendWord = sendWord;
        this.createJson = createJson;
        this.connectToDatabase = connectToDatabase;
        this.deleteWord = deleteWord;
        this.importCollection = new ImportVocabularyCollection(this.connectToDatabase.getMongoClient());
    }

    @PreDestroy
    public void closeAllStreams(){
        try{
          if(!connectToDatabase.getMongoClient().isLocked())connectToDatabase.getMongoClient().close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public String showVocabularyList(Model model) {
        model.addAttribute("vocabulary", importCollection.checkAndGetDatabaseCollection());
        return "index";
    }

    @PostMapping("/")
    public String sendWord(Model model, @RequestParam("inputWord") String inputWord, @RequestParam("inputTranslation") String inputTranslation, @RequestParam("language") String language) {
        try {
            if(inputTranslation.contains("/")){
                String [] translationArray = inputTranslation.split("/");
                createJson.createDocument(inputWord,translationArray,language);
                sendWord.sendFile(connectToDatabase.getMongoClient(),createJson.getDoc());
            }
            else {
                createJson.createDocument(inputWord, new String[]{inputTranslation}, language);
                sendWord.sendFile(connectToDatabase.getMongoClient(), createJson.getDoc());
            }
            model.addAttribute("vocabulary", importCollection.checkAndGetDatabaseCollection());
            return "index";
        }catch (NoValueException e){
            model.addAttribute("vocabulary", importCollection.checkAndGetDatabaseCollection());
            return "index";
        }
    }

    @PostMapping("/delete")
    public String deleteWord(Model model, @RequestParam("inputWordDelete") String inputWord, @RequestParam("inputTranslationDelete") String inputTranslation, @RequestParam("languageDelete") String language)  {

        try {
            createJson.createDocument(inputWord, new String[]{inputTranslation}, language);
            deleteWord.deleteFile(connectToDatabase.getMongoClient(), createJson.getDoc()[0]);
            model.addAttribute("vocabulary", importCollection.checkAndGetDatabaseCollection());

            return "index";
        }catch (NoValueException | MongoSocketReadException e ){
            model.addAttribute("vocabulary", importCollection.checkAndGetDatabaseCollection());
            return "index";
        }
    }
}
