package com.example.demo.restcontroller;


import com.example.demo.domain.service.ResService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping()
public class ResRestController {

    @Autowired
    private ResService resService;


}
