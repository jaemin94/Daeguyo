package com.example.demo.domain.service;

import com.example.demo.domain.daeguyo.OrderDto;
import com.example.demo.domain.mapper.OrderMapper;
import com.example.demo.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private UserMapper userMapper;

    public List<OrderDto> getAllOrders() {
        return mapper.selectAll();
    }

    public List<OrderDto> getOrdersByUserId(int userId) {
        return mapper.selectByUserId(userId);
    }
}
