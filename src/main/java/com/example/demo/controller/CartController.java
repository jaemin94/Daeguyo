package com.example.demo.controller;

import com.example.demo.domain.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping()
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public void getCart(){

    }
}
