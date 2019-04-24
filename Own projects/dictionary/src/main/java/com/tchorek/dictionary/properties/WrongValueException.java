package com.tchorek.dictionary.properties;

public class WrongValueException extends Exception {

    public WrongValueException(String errorMessage){
        super(errorMessage);
    }
}
//  "database_url":"mongodb+srv://mtchorek:<PASSWORD>@information-collections-mz7lu.mongodb.net/test?retryWrites=true"
