package com.elenx.mtchorek.hello_world.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HelloWorldController {

    @RequestMapping(value = "/")
    public String printHelloWorld() {
        return "home.html";
    }

}


