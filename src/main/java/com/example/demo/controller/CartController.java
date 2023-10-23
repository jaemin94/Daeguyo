package com.example.demo.controller;


import com.example.demo.domain.daeguyo.CartDto;

import com.example.demo.domain.daeguyo.MenuDto;
import com.example.demo.domain.daeguyo.PaymentDto;
import com.example.demo.domain.daeguyo.ResDto;
import com.example.demo.domain.service.CartService;
import com.example.demo.domain.service.MenuService;
import com.example.demo.domain.service.OrderService;
import com.example.demo.domain.service.ResService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@Slf4j
@RequestMapping()
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ResService resService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/cart")
    public String getCart(Model model, HttpSession session){
        String u_email = (String) session.getAttribute("username");
        String menu_id = null;
        List<CartDto> cartDto = cartService.selectAllByEmail(u_email);
        List<MenuDto> menuDtoList = new ArrayList<>();
        List<CartDto> cartDtoList = new ArrayList<>();
        int totalPrice = 0;

        for (CartDto item : cartDto) {
            menu_id = item.getMenu_id();
            List<MenuDto> menuDto = menuService.getAllMenuFromCart(menu_id);
            String res_id = menuService.getRes_id(menu_id);
            ResDto resDto = resService.searchOne(res_id);
            model.addAttribute("resDto",resDto);
            menuDtoList.addAll(menuDto);
            for (MenuDto menu : menuDto) {
                totalPrice += menu.getPrice();
            }
        }


        List<MenuDto> menuDto = menuService.getAllMenuFromCart(menu_id);
        model.addAttribute("cartDto",cartDto);
        model.addAttribute("menuDto",menuDtoList);
        model.addAttribute("totalPrice",totalPrice);
        return "cart";
    }


}
