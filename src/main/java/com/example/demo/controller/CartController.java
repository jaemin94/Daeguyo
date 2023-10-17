package com.example.demo.controller;


import com.example.demo.domain.daeguyo.CartDto;

import com.example.demo.domain.daeguyo.CouponDto;
import com.example.demo.domain.daeguyo.PaymentDto;
import com.example.demo.domain.service.CartService;
import com.example.demo.domain.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@Slf4j
@RequestMapping()
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;


    @PostMapping("/cart/coupon")
    @ResponseBody
    public void couponupdate(@RequestBody CouponDto Cdto){
        System.out.println("Payment saved");
        cartService.couponUpdate(Cdto);


    }


    @GetMapping("/cart")
    public String getCart(Model model, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        List<CartDto> options =cartService.SearchOption((String)session.getValue("username"));
        List<CouponDto> coupon  = cartService.SearchCoupon((String)session.getValue("username"));

        int total = 0;
        for (CartDto option : options) {
            total += option.getPrice() * option.getCount();
        }


        model.addAttribute("total", total);
        model.addAttribute("options",options);
        model.addAttribute("coupon", coupon);

        return "cart";
    }

    @PostMapping("/payment/save")
    @ResponseBody
    public Map<String,Object> paymentsave(@RequestBody PaymentDto payment){
        System.out.println("Payment saved");
        Map<String,Object> detail = cartService.paymentInsert(payment);
        System.out.println(payment);
        return detail; // HTTP 상태 코드 200(OK)을 반환합니다.
    }


    @PostMapping("/cart/delete")
    @ResponseBody
    public int paymentsave(@RequestBody CartDto dto){
        System.out.println(dto.getCart_id());
        System.out.println("Payment saved");
        int detail = cartService.cartDelete(dto);

        return detail; // HTTP 상태 코드 200(OK)을 반환합니다.
    }



}
