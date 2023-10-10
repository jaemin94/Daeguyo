package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String main(){
        log.info("Get/index");
        return "index copy";
    }

    @GetMapping("/Import")
    public  String main1(){
        return "Import";
    }

}
