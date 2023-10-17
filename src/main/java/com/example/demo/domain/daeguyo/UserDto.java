package com.example.demo.domain.daeguyo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private int id;
    private String u_email;
    private String addr;
    private String password;
    private String phone;
    private String nickname;
    private String role;
}


