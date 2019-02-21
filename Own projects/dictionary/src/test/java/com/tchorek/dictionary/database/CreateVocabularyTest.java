package com.tchorek.dictionary.database;

import com.tchorek.dictionary.properties.NoValueException;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


class CreateVocabularyTest {
    private CreateVocabulary createVocabulary;

    @BeforeEach
    private void setUpClass(){
        createVocabulary = new CreateVocabulary();
    }

    @Test
    public void createJsonTestSuccess() throws NoValueException {
        createVocabulary.createDocument("Commissioned",new String[]{"Upoważniony"},"English");
        Document doc = createVocabulary.getDoc()[0];
        assertEquals(doc.get("word"),"Commissioned");
        assertEquals(doc.get("translation"),"Upoważniony");
        assertEquals(doc.get("Language"),"English");
    }

    @Test
    public void createJsonTestFailure() throws NoValueException {
        createVocabulary.createDocument("",new String[]{""},"");
        assertThrows(NoValueException.class, ()-> { createVocabulary.getDoc();});
        createVocabulary.createDocument("Black",new String[]{""},"English");
        assertThrows(NoValueException.class, ()-> { createVocabulary.getDoc();});
        createVocabulary.createDocument("",new String[]{"Czarny"},"English");
        assertThrows(NoValueException.class, ()-> { createVocabulary.getDoc();});
        createVocabulary.createDocument("Black",new String[]{"Czarny"},"");
        assertThrows(NoValueException.class, ()-> { createVocabulary.getDoc();});
    }


    @AfterEach
    private void tearDownClass(){
        createVocabulary = null;
    }

}