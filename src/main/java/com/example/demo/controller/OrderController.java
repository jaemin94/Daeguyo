package com.example.demo.controller;

import com.example.demo.domain.daeguyo.OrderDto;
import com.example.demo.domain.daeguyo.ResDto;
import com.example.demo.domain.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping()
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orderStatus")
    public void getOrderStatus(){

    }

    @GetMapping("/orderCheck")
    public String resOrderCheck(Model model){
        List<OrderDto> orderdto = orderService.getOrder();
        log.info("Get/restList called");

        System.out.println(orderdto);
        model.addAttribute("orderdto",orderdto);


        return "resCheck";
    }

}
