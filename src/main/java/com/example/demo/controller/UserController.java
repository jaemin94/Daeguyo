package com.example.demo.controller;

import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@Slf4j
@RequestMapping()
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public void loginPage(){

    }


    @GetMapping("/myPage")
    public String getMyPage(Principal principal, Model model) {
        String u_email = principal.getName();

        UserDto user = userService.userSearch(u_email);
        int couponCount = userService.userCouponCount(u_email);
        int reviewCount = userService.userReviewCount(u_email);
        int orderCount = userService.userOrderCount(u_email);

        model.addAttribute("couponCount", couponCount);
        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("orderHistoryCount", orderCount);
        model.addAttribute("user", user);

        return "myPage";
    }




    @GetMapping("/memberJoin")
    public void memberJoinPage(){

    }

    @PostMapping("/memberJoin")
    public String memberJoin(@RequestBody UserDto dto)
    {
        System.out.println(dto);
        int result = userService.memberJoin(dto); // service를 사용하여 memberJoin 메서드 호출
        if (result > 0) {
            return "/login";
        } else {
            return "/memberJoin";
        }
    }

}
