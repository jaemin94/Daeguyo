package com.example.demo.restcontroller;

import com.example.demo.domain.daeguyo.CartDto;
import com.example.demo.domain.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("")
public class CartRestController {

    @Autowired
    private CartService cartService;

    @PostMapping("/updateOrder")
    @ResponseBody
    public void updateOrder(@RequestBody CartDto dto) {
        System.out.println(dto.getCount());
        cartService.updateOrderAmount(dto);


    }
}
