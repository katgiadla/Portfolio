package com.tchorek.dictionary.database;

import com.tchorek.dictionary.properties.NoValueException;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import static org.junit.jupiter.api.Assertions.*;


class CreateJsonTest {
    private CreateJson createJson;

    @BeforeEach
    private void setUpClass(){
        createJson = new CreateJson();
    }

    @Test
    public void createJsonTestSuccess() throws NoValueException {
        createJson.createDocument("Commissioned","Upoważniony","English");
        Document doc = createJson.getDoc();
        assertEquals(doc.get("word"),"Commissioned");
        assertEquals(doc.get("translation"),"Upoważniony");
        assertEquals(doc.get("Language"),"English");
    }

    @Test
    public void createJsonTestFailure() throws NoValueException {
        createJson.createDocument("","","");
        assertThrows(NoValueException.class, ()-> { createJson.getDoc();});
        createJson.createDocument("Black","","English");
        assertThrows(NoValueException.class, ()-> { createJson.getDoc();});
        createJson.createDocument("","Czarny","English");
        assertThrows(NoValueException.class, ()-> { createJson.getDoc();});
        createJson.createDocument("Black","Czarny","");
        assertThrows(NoValueException.class, ()-> { createJson.getDoc();});
    }


    @AfterEach
    private void tearDownClass(){
        createJson = null;
    }

}