package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;



import com.example.demo.domain.daeguyo.OrderDto;
import com.example.demo.domain.daeguyo.ResDto;
import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.service.OrderService;
import com.example.demo.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
@RequestMapping("")
public class OrderController {


    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("/orderStatus")
    public String getOrderStatus(Model model, HttpSession session) {
        String u_email = (String)session.getAttribute("username");
        List<OrderDto> orders = orderService.getAllOrders(u_email);
       // UserDto users = userDetails.getUser(); // 로그인한 사용자 정보 가져오기
        model.addAttribute("orders", orders);
        //model.addAttribute("users", users); // 화면에 전달
        log.info("주문 목록을 모델에 추가: " + orders);
        //log.info("사용자 목록을 모델에 추가: " + users);
        return "orderStatus";
    }

    @GetMapping("/orderCheck1")
    public String resOrderCheck1(Model model,@RequestParam(name = "order_id", required = true) String order_id) {
        log.info("Get/orderCheck1 called");
        OrderDto dto = new OrderDto();
        List<OrderDto> orderlist;
        UserDto userdto;
        dto.setOrder_id(order_id);
            dto = orderService.getSearchOrder(order_id);
            String u_email = dto.getU_email();
            System.out.println(u_email);
            userdto = userService.userSearch(u_email);
            System.out.println("dto: "+ dto);
            System.out.println("userdto: "+ userdto);
            System.out.println("order_id: "+ order_id);

            model.addAttribute("order_id", order_id);
            model.addAttribute("orderdto",dto);
            model.addAttribute("userdto",userdto);


        return "resCheck";
    }


    @GetMapping("/orderCheck")
    public String resOrderCheck(Model model,@RequestParam(name = "order_id", required = false) String order_id) {
        log.info("Get/orderCheck called");
          List<OrderDto>  orderdto = orderService.getOrder();
            System.out.println(orderdto);
            model.addAttribute("order_id", order_id);
            model.addAttribute("orderlist", orderdto);
        return "resCheck";
    }





}













