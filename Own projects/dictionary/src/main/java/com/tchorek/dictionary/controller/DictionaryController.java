package com.tchorek.dictionary.controller;

import com.tchorek.dictionary.database.*;
import com.tchorek.dictionary.properties.NoValueException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;


@Controller
@Component
public class DictionaryController implements InitializingBean {

    @Autowired
    private ImportVocabularyCollection importCollection;

    @Autowired
    private SendWord sendJson;

    @Autowired
    private CreateJson createJson;

    @Autowired
    private ConnectToDatabase connectToDatabase;

    @Autowired
    private DeleteWord deleteWord;

    @Override
    public void afterPropertiesSet() throws FileNotFoundException {
        connectToDatabase = new ConnectToDatabase();
        importCollection = new ImportVocabularyCollection(connectToDatabase.getMongoClient());
        createJson = new CreateJson();
        sendJson = new SendWord();
        deleteWord = new DeleteWord();
    }

    @GetMapping("/")
    public String showVocabularyList(Model model) {
        model.addAttribute("vocabulary", importCollection.checkAndGetDatabaseCollection());
        return "index";
    }

    @PostMapping("/")
    public String sendWord(Model model, @RequestParam("inputWord") String inputWord, @RequestParam("inputTranslation") String inputTranslation, @RequestParam("language") String language) {
        try {
            createJson.createDocument(inputWord, inputTranslation, language);
            sendJson.sendFile(connectToDatabase.getMongoClient(), createJson.getDoc());
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
            createJson.createDocument(inputWord, inputTranslation, language);
            deleteWord.deleteFile(connectToDatabase.getMongoClient(), createJson.getDoc());
            model.addAttribute("vocabulary", importCollection.checkAndGetDatabaseCollection());

            return "index";
        }catch (NoValueException e){
            model.addAttribute("vocabulary", importCollection.checkAndGetDatabaseCollection());
            return "index";
        }
    }
}
