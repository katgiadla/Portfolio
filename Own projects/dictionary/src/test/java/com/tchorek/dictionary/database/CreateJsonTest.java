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
        createJson.createDocument("Commissioned",new String[]{"Upoważniony"},"English");
        Document doc = createJson.getDoc()[0];
        assertEquals(doc.get("word"),"Commissioned");
        assertEquals(doc.get("translation"),"Upoważniony");
        assertEquals(doc.get("Language"),"English");
    }

    @Test
    public void createJsonTestFailure() throws NoValueException {
        createJson.createDocument("",new String[]{""},"");
        assertThrows(NoValueException.class, ()-> { createJson.getDoc();});
        createJson.createDocument("Black",new String[]{""},"English");
        assertThrows(NoValueException.class, ()-> { createJson.getDoc();});
        createJson.createDocument("",new String[]{"Czarny"},"English");
        assertThrows(NoValueException.class, ()-> { createJson.getDoc();});
        createJson.createDocument("Black",new String[]{"Czarny"},"");
        assertThrows(NoValueException.class, ()-> { createJson.getDoc();});
    }


    @AfterEach
    private void tearDownClass(){
        createJson = null;
    }

}