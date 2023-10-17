package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("")
public class OrderController {


    @GetMapping("/orderStatus")
    public void getOrderStatus() {
        log.info("getOrderStatus");
    }

    @GetMapping("/home")
    public String getErrorPath() {
        return "/error";
    }

}

//    @GetMapping("/selectOptions")
//    public String restList(Model model){
//        List<CartDto> options = cartService.SearchOption();
//        log.info("Get/selectOptions called");
//
//        System.out.println(options);
//        model.addAttribute("options",options);
//        return "/cart";
//    }












