package com.tchorek.dictionary.properties;

public  class emergencySituations {
    public enum Permissions{
        Delete("Delete");
        String container;
        Permissions(String input){
            this.container = input;
        }
    }
}
