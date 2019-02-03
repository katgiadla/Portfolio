package com.tchorek.dictionary.database;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;


class ImportPasswordFromJsoNTest {
    ImportPasswordFromJson importPasswordFromJson;
    String expectedPassword;
    private final String PATH = "E:\\AGH\\Portfolio\\Own projects\\dictionary\\src\\main\\resources\\db_access\\";

    @BeforeEach
    public void setUpClass(){
        importPasswordFromJson = new ImportPasswordFromJson();
    }

    @Test
    public void testImportPasswordSuccess() throws FileNotFoundException {
        expectedPassword =importPasswordFromJson.importPassword(PATH,"data2.json");
        assertEquals(expectedPassword,"FakePassword");
    }

    @Test
    public void testImportPasswordFailure() throws FileNotFoundException {
        assertNotEquals(
            importPasswordFromJson.importPassword(PATH,"data1.json"),"FakePassword");
    }

    @Test
    public void testImportPasswordException(){
        assertThrows(FileNotFoundException.class, ()->{
            importPasswordFromJson.importPassword(PATH,"NotExistingFile.json"); } );
        assertThrows(FileNotFoundException.class, ()->{
            importPasswordFromJson.importPassword("NoPathToFile","password3.json"); } );

    }

    @AfterEach
    public void tearDownClass(){
        importPasswordFromJson = null;
    }
}