package com.example.demo.domain.service;

import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper mapper;

    public List<UserDto> getUserDto() {
        return mapper.selectAll();
    }



}

