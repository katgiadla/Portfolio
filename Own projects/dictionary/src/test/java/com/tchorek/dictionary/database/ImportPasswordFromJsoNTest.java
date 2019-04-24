package com.tchorek.dictionary.database;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;


class ImportPasswordFromJsoNTest {
    GetDatabasePassword getDatabasePassword;
    String expectedPassword;
    private final String PATH = "E:\\AGH\\Portfolio\\Own projects\\dictionary\\src\\main\\resources\\db_access\\";

    @BeforeEach
    public void setUpClass(){
        getDatabasePassword = new GetDatabasePassword();
    }

    @Test
    public void testImportPasswordSuccess() throws FileNotFoundException {
        expectedPassword = getDatabasePassword.importPassword(PATH,"Data2.json");
        assertEquals(expectedPassword,"FakePassword");
    }

    @Test
    public void testImportPasswordFailure() throws FileNotFoundException {
        assertNotEquals(
            getDatabasePassword.importPassword(PATH,"Data1.json"),"FakePassword");
    }

    @Test
    public void testImportPasswordException(){
        assertThrows(FileNotFoundException.class, ()->{
            getDatabasePassword.importPassword(PATH,"NotExistingFile.json"); } );
        assertThrows(FileNotFoundException.class, ()->{
            getDatabasePassword.importPassword("NoPathToFile","password3.json"); } );

    }

    @AfterEach
    public void tearDownClass(){
        getDatabasePassword = null;
    }
}