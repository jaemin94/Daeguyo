package com.example.demo.controller;

import com.example.demo.domain.daeguyo.OrderDto;
import com.example.demo.domain.daeguyo.ResDto;
import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.service.OrderService;
import com.example.demo.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping()
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("/orderStatus")
    public String getOrderStatus(Model model) {
        List<OrderDto> orders = orderService.getAllOrders();
        List<UserDto> users= userService.getUserDto();
        model.addAttribute("orders", orders);
        model.addAttribute("users", users);
        log.info("주문 목록을 모델에 추가: " + orders);
        log.info("주문 목록을 모델에 추가: " + users);
        return "orderStatus";
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
