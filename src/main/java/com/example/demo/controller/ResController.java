package com.example.demo.controller;

import com.example.demo.domain.daeguyo.ResDto;
import com.example.demo.domain.service.ResService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping()
public class ResController {

    @Autowired
    private ResService resService;

    @GetMapping("/selectRest")
    public String restList(Model model){
        List<ResDto> restaurant = resService.searchAll();
        log.info("Get/restList called");

        System.out.println(restaurant);
        model.addAttribute("restaurant",restaurant);
        return "/selectRest";
    }




}
