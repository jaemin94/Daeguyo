package com.example.demo.controller;

import com.example.demo.domain.daeguyo.MenuDto;
import com.example.demo.domain.daeguyo.ResDto;
import com.example.demo.domain.service.MenuService;
import com.example.demo.domain.service.ResService;
import com.example.demo.domain.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping()
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private MenuService menuService;

    @Autowired
    private ResService resService;;

    //review_tab page1 에서 쓸 내용들
    @GetMapping("/review_tab")
    public String getMenu(@RequestParam String res_id, Model model) {
        ResDto res = resService.searchOne(res_id);
        model.addAttribute("res",res);
        log.info("res"+ res);
        List<MenuDto> menu = menuService.getResMenu(res_id);
        model.addAttribute("menu", menu);
        log.info("menu"+ menu);
        log.info("GET /review_tab");
        return "review_tab";
    }
}
