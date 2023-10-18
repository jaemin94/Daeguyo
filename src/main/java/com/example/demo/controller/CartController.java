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

    //쿠폰 사용 update
    @PostMapping("/cart/coupon")
    @ResponseBody
    public void couponupdate(@RequestBody CouponDto Cdto){

        cartService.couponUpdate(Cdto);


    }

    //cart페이지 접근
    @GetMapping("/cart")
    public String getCart(Model model, Authentication authentication){
        //사용자 인증정보
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        //로그인한 u_email로 서비스 실행
        String username = principal.getUsername();

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

    //결제정보 저장
    @PostMapping("/payment/save")
    @ResponseBody
    public Map<String,Object> paymentsave(@RequestBody PaymentDto payment){


        Map<String,Object> detail = cartService.paymentInsert(payment);

        return detail;
    }

    //장바구니 삭제
    @PostMapping("/cart/delete")
    @ResponseBody
    public int paymentsave(@RequestBody CartDto dto){


        int detail = cartService.cartDelete(dto);

        return detail;
    }

    //tbl_order에 값 전달
    @PostMapping("/create")
    @ResponseBody
    public int create(@RequestBody CartDto dtos) {

        return cartService.createOrder(dtos);
    }

}
