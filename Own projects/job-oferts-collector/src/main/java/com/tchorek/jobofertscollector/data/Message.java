package com.tchorek.jobofertscollector.data;

import lombok.Data;

@Data
public class Message {

    private static final String[] KEYWORDS = {"Opis stanowiska:", "Wymagania:", "Wymagamy:", "Oczekiwania wobec kandydatów:", "odpowiedzialny za:", "odpowiedzialność", "oczekiwania", "Gdy posiadasz…", "Kwalifikacje i wymagania", "Wymagania"};
    private static String signature = "Mateusz Tchorek";


    private static String occupation = "Domyslna praca";
    private static String CV = "cv.pdf";

    private static final String MESSAGE = "Szanowni Państwo, \n" +
            "Zgłaszam swoją kandydaturę na stanowisko: " + occupation + "\n" +
            "" + "\n" +
            "Wszelkie niezbędne informacje zostały umieszczone w załączniku" + CV + "\n" +
            "" + "\n" +
            "Z wyrazami szacunku" + "\n" +
            signature;



}
