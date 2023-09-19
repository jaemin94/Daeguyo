package com.example.demo.C03KakaoAPI;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/th/kakao")
public class C04KakaoChannelAPI {

    @GetMapping("/channel")
    public void channel(){
        log.info("GET/th/kakao/channel");

    }
}
