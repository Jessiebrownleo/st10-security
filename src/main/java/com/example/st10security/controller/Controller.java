package com.example.st10security.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller

public class Controller {
    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/home")
    public String home(){
        return "home";

    }


    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
