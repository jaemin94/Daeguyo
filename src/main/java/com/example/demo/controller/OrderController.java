package com.example.demo.controller;

import com.example.demo.domain.daeguyo.OrderDto;
import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.mapper.UserMapper;
import com.example.demo.domain.service.OrderService;
import com.example.demo.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;



    @Controller
    @Slf4j
    @RequestMapping()
    public class OrderController {

        @Autowired
        private OrderService orderService;
        @Autowired
        private UserService userService;
        @Autowired
        private UserMapper userMapper;

        @GetMapping("/orderStatus")
        public String getOrderStatus(Model model, @AuthenticationPrincipal UserDetails userDetails) {
            if (userDetails != null) {
                String user_email = userDetails.getUsername();
                UserDto user = userService.select(user_email);

                if (user != null) {
                    int userId = user.getId(); // 사용자의 ID를 가져옴
                    List<OrderDto> orders = orderService.getOrdersByUserId(userId); // 사용자의 ID를 기반으로 주문을 조회
                    List<UserDto> users = userService.getUserDto();

                    model.addAttribute("user_email", user_email);
                    model.addAttribute("user", user);
                    model.addAttribute("orders", orders);
                    model.addAttribute("users", users);

                    log.info("주문 목록을 모델에 추가: " + orders);
                    log.info("주문 목록을 모델에 추가: " + users);
                }
            }

            return "orderStatus";
        }
    }









