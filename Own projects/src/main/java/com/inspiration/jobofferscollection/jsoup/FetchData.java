package com.inspiration.jobofferscollection.jsoup;

import java.util.ArrayList;

public class FetchData {


    ArrayList<String> domainList;

    public FetchData(){
        domainList = new ArrayList<>();
        domainList.add("https://www.olx.pl/praca/informatyka/krakow/");
    }

    public ArrayList<String> getDomainList() {

        return domainList;
    }

    public String getLink(int index){
        return domainList.get(index);
    }


}
