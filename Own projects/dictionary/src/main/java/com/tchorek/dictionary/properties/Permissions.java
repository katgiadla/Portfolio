package com.tchorek.dictionary.properties;

public  enum Permissions {
        Deleteone("Deleteone"),Deletemany("Deletemany"); // ZJEBALEM WIEM O TYM NIE MAM WENY ZEBY TO NAPRAWIC
        String container;
        Permissions(String input){
            this.container = input;
        }
}
