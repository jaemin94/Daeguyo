package com.example.demo.controller;

import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@Slf4j
@RequestMapping()
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(){

        return "login";
    }


    @GetMapping("/myPage")
    public String getMyPage(Model model, HttpSession session) {
        String u_email = (String) session.getAttribute("username");
        log.info("u_email: " + u_email);
        int couponCount = userService.userCouponCount(u_email);
        int reviewCount = userService.userReviewCount(u_email);
        int orderCount = userService.userOrderCount(u_email);

        model.addAttribute("couponCount", couponCount);
        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("orderHistoryCount", orderCount);


        return "mypage";
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
            return "login";
        } else {
            return "memberJoin";
        }
    }

    @GetMapping("/memberUpdate")
    public String memberUpdate( Model model, String email) {
        UserDto userDto = userService.getUserInfoByEmail(email);
        System.out.println(userDto);
        model.addAttribute("user", userDto);

        return "memberUpdate";

    }

}
