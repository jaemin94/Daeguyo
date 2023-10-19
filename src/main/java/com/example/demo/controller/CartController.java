package com.example.demo.controller;


import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.domain.daeguyo.*;
import com.example.demo.domain.service.CartService;
import com.example.demo.domain.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
@RequestMapping()
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;



    //cart페이지 접근
    @GetMapping("/cart")
    public String getCart(Model model, HttpSession httpSession){
        //사용자 인증정보
        String username = (String) httpSession.getAttribute("username");



        // cartdto에 있는 정보를 가져옴
        List<CartDto> options = cartService.SearchOption(username);

        // CouponDto에 있는 정보를 가져옴
        List<CouponDto> coupon  = cartService.SearchCoupon(username);

        // ResDto에 있는 정보를 가져옴
        ResDto res = cartService.search_res_name(options.get(0).getRes_id());
        // MenuDto에 있는 정보를 가져옴
        List<MenuDto> menu  = cartService.search_menu_name(options);

        // 가격이랑 수량을 곱해주는 코드
        int total = 0;
        for (CartDto option : options) {
            total += option.getPrice() * option.getCount();
        }

        //cart.html로 값 전달
        model.addAttribute("total", total);
        model.addAttribute("options",options);
        model.addAttribute("coupon", coupon);
        model.addAttribute("res",res);
        model.addAttribute("menu",menu);

        return "cart";
    }



}
