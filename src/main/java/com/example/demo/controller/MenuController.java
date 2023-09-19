package com.example.demo.controller;

import com.example.demo.domain.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping()
public class MenuController {

    @Autowired
    private MenuService menuService;

}
