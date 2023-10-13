package com.example.demo.domain.service;

import com.example.demo.domain.daeguyo.OrderDto;
import com.example.demo.domain.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class OrderService {

    @Autowired
    private OrderMapper mapper;

    public List<OrderDto> getOrder(){

        return mapper.selectAll();
    }

}
