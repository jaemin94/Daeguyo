package com.example.demo.domain.service;

import com.example.demo.domain.mapper.CartMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartService {

    @Autowired
    private CartMapper mapper;

}
