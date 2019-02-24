package com.tchorek.dictionary.properties;

public  enum Permissions {
        DELETEONE("DELETEONE"),DELETEMANY("DELETEMANY"); // ZJEBALEM DO NA PRAWY
        String container;
        Permissions(String input){
            this.container = input;
        }
}