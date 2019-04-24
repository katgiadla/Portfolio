package com.tchorek.jobofertscollector.olx;

import lombok.Data;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Data
@Component
@Controller
public class OffersFetcher implements InitializingBean {

    private static String location = "krakow";
    private static final String URL = "https://www.olx.pl/praca/informatyka/" + location + "/";


    Document olxPlMainPageData;
    Elements olxPlListOfOffersData;


    private List<String> allOferts, websitesList;
    private HashMap<String, String> interestingOferts;


    @GetMapping(value = "/home")
    public String homePage(Model model, ModelMap modelMap) {
        model.addAttribute("websites", websitesList);
        modelMap.put("websites", websitesList);
        return "home";
    }

    @GetMapping(value = "/choice")
    public String chosenWebsite(@RequestParam(name = "buttonChoice") String chosenUrl, ModelMap modelMap, Model model) {
        model.addAttribute("url", chosenUrl);
        modelMap.put("url", chosenUrl);
        return "JobOffersChecker";
    }


    private void catchingMainPage() {
        olxPlMainPageData = new Document(URL);
        olxPlListOfOffersData = olxPlMainPageData.getElementsByTag("a");
        allOferts = new LinkedList<>();
        interestingOferts = new HashMap<>();
        this.collectingAllJobOffers();
    }

    private void collectingAllJobOffers() {
        for (Element element : olxPlListOfOffersData) {
            String jobUrl = element.attr("href");
            if (jobUrl.contains("oferta"))
                allOferts.add(jobUrl);
        }
        //this.collectingInterestingJobOffers();
    }

    private void collectingInterestingJobOffers() {
        for (String jobUrl : allOferts) {
            Document ofertSite = new Document(jobUrl);
            /*Elements*/
        }


    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.catchingMainPage();
    }
}
