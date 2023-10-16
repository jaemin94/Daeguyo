package com.example.demo.controller;

import com.example.demo.domain.daeguyo.CartDto;
import com.example.demo.domain.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
