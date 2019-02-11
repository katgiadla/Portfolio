package com.tchorek.dictionary.properties;

public  enum Permissions {
        Deleteone("Deleteone"),Deletemany("Deletemany");
        String container;
        Permissions(String input){
            this.container = input;
        }
}
