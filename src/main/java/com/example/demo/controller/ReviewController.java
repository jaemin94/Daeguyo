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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
//   getMenu 메서드 전체 다 복사 부탁드려욥.!!
    // import 부분들도 추가 부탁드립니도..!!
    public String getMenu(@RequestParam String res_id, Model model) {
        ResDto res = resService.searchOne(res_id);
        model.addAttribute("res",res);
        log.info("res"+ res);
        // 레스토랑 별점 출력 코드 추가
        int fullStars = (int) Math.floor(res.getRes_rating()); // 정수 앞자리 만큼의 꽉 찬 별
        int halfStars = (res.getRes_rating() - fullStars >= 0.5) ? 1 : 0; // 소숫점 은 반별로
        int emptyStars = 5 - fullStars - halfStars; // 별 총 5개 되도록 빈 별로 개수 채우기

        List<Integer> fullStarsList = IntStream.range(0, fullStars).boxed().collect(Collectors.toList()); // 개수만큼 리스트 만들기
        List<Integer> halfStarsList = halfStars == 1 ? Arrays.asList(1) : new ArrayList<>(); //소숫점 반별있으면 리스트에 halfStarsList 1 cnrk
        List<Integer> emptyStarsList = IntStream.range(0, emptyStars).boxed().collect(Collectors.toList()); // 빈 별 리스트

        model.addAttribute("fullStarsList", fullStarsList);
        model.addAttribute("halfStarsList", halfStarsList);
        model.addAttribute("emptyStarsList", emptyStarsList);


        List<MenuDto> menu = menuService.getResMenu(res_id);
        model.addAttribute("menu", menu);
        log.info("menu"+ menu);
        log.info("GET /review_tab");
        return "review_tab";
    }
}
