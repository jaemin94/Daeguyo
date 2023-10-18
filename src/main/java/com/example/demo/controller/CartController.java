package com.example.demo.controller;


import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.domain.daeguyo.CartDto;
import com.example.demo.domain.daeguyo.CouponDto;
import com.example.demo.domain.daeguyo.PaymentDto;
import com.example.demo.domain.service.CartService;
import com.example.demo.domain.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/cart/coupon")
    @ResponseBody
    public void couponupdate(@RequestBody CouponDto Cdto){
        System.out.println("Payment saved");
        cartService.couponUpdate(Cdto);


    }


    @GetMapping("/cart")
    public String getCart(Model model, Authentication authentication){
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        System.out.println("이메일" + username);
        List<CartDto> options = cartService.SearchOption(username);
        List<CouponDto> coupon  = cartService.SearchCoupon(username);

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
