package com.tchorek.dictionary.database;

import org.junit.*;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ImportPasswordFromJsoNTest {
    ImportPasswordFromJson importPasswordFromJson;
    String expectedPassword;

    @BeforeClass
    public void setUpClass(){
        importPasswordFromJson = new ImportPasswordFromJson();
    }

    @Before
    public void setUp(){

    }

    @Test
    public void testImportPasswordSuccess(){
         expectedPassword = importPasswordFromJson.importPassword("E:\\AGH\\Portfolio\\Own projects\\dictionary\\src\\main\\resources\\sensitive\\","password.json");
        assertEquals(expectedPassword,"admin");
    }

    @Test
    public void testImportPasswordFailure(){
        assertNotEquals(
            importPasswordFromJson.importPassword("E:\\AGH\\Portfolio\\Own projects\\dictionary\\src\\main\\resources\\sensitive\\","password2.json"),"admin");
    }

    @Test
    public void testImportPasswordException(){
        assertThrows(FileNotFoundException.class, ()->{
            importPasswordFromJson.importPassword("E:\\AGH\\Portfolio\\Own projects\\dictionary\\src\\main\\resources\\sensitive\\","password3.json");
        } );
    }

    @After
    public void tearDown(){

    }

    @AfterClass
    public void tearDownClass(){
        importPasswordFromJson = null;
    }
}