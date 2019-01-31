package com.tchorek.dictionary.data;

import com.mongodb.MongoClientURI;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class connectionPropertiesTest {

    @Test
    public void testTonnectToDB(){
        String inputPassword = null;
         new MongoClientURI(
                "mongodb+srv://mtchorek:"+inputPassword+"@information-collections-mz7lu.mongodb.net/test?retryWrites=true"
        );


    }


}