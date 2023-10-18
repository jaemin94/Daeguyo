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
    //myPage 접속 시 뜨는 회원 등급 저장 컬럼 추가
    // 일반회원, 단골회원, VIP 로 나눔
    private String user_grade;
    private String role;

}


