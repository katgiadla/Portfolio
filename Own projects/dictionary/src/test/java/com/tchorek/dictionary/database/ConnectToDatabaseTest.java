package com.tchorek.dictionary.database;

import com.mongodb.MongoClientURI;
import org.junit.*;
import org.junit.Assert;

class ConnectToDatabaseTest {

    @Test
    public void testTonnectToDbProperly(String input){
        String password = input;
         new MongoClientURI(
                "mongodb+srv://mtchorek:"+password+"@information-collections-mz7lu.mongodb.net/test?retryWrites=true"
        );


    }


}