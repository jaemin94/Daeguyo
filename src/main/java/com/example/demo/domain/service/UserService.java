package com.example.demo.domain.service;

import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper mapper;

    public int memberJoin(UserDto dto) {
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setRole("ROLE_User");
        dto.setPassword(encryptedPassword);
        System.out.println(dto);
        return mapper.insertUser(dto);
    }

    public List<UserDto> getUserDto() {
        return mapper.selectAll();
    }
}

