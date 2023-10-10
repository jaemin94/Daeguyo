package com.example.demo.controller;

import com.example.demo.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String login() {
        return "login";
    }

}
