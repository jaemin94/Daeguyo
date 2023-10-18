package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String main(HttpServletRequest httpServletRequest, Model model){
        log.info("Get/index");
        return "index copy";

    }

    @GetMapping("/Import")
    public  String main1(){
        return "Import";
    }

}
