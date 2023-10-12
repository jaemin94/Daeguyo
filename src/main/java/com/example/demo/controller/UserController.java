package com.example.demo.controller;

import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping()
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/memberJoin")
    public void memberJoinPage(){

    }
    @GetMapping("/memberUpdate")
    public void memberUpdatePage(){

    }

    @GetMapping("/myPage")
    public void getMyPage(){

    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // 사용자 정보를 세션에 저장
        UserDto user = userService.select(userDetails.getUsername());
        model.addAttribute("user", user);
        return "/orderStatus";
    }


}
