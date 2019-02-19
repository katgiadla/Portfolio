package com.tchorek.dictionary.properties;

public class NoValueException extends Exception {

    public NoValueException(String errorMessage){
        super(errorMessage);
    }
}
//  "database_url":"mongodb+srv://mtchorek:<PASSWORD>@information-collections-mz7lu.mongodb.net/test?retryWrites=true"
