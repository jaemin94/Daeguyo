package com.example.demo.domain.service;

import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.mapper.CouponMapper;
import com.example.demo.domain.mapper.OrderMapper;
import com.example.demo.domain.mapper.ReviewMapper;
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
    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private OrderMapper orderMapper;


    public int memberJoin(UserDto dto) {
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setRole("ROLE_User");
        dto.setUser_grade("VIP");
        dto.setPassword(encryptedPassword);
        System.out.println(dto);
        return mapper.insertUser(dto);
    }



    // myPage.html
    public UserDto userSearch(String u_email){
        log.info("UserService's user search at u_email: " + u_email);
        return mapper.selectOne(u_email);
    }
    //근데 요 아래 세개 mapper에서 가져오는 거라서 service에 넣긴 했는데
    // 혹시 넘므 지저분할랑가요....?
    public int userCouponCount(String u_email){
        return couponMapper.userCouponCount(u_email);
    }

    public int userReviewCount(String u_email){
        return reviewMapper.userReviewCount(u_email);
    }

    public int userOrderCount(String u_email){
        return orderMapper.userOrderCount(u_email);
    }


    public UserDto getUserInfoByEmail(String email) {
        return mapper.selectOne(email);

    }
    public int memberUpdate(UserDto dto){
        System.out.println(dto);
        if (dto.getPassword() != null&&dto.getAddr()!=null){
            String encryptedPassword = passwordEncoder.encode(dto.getPassword());
            dto.setPassword(encryptedPassword);
            return mapper.updateUserall(dto);

        }
        else if(dto.getPassword() != null) {
            String encryptedPassword = passwordEncoder.encode(dto.getPassword());
            dto.setPassword(encryptedPassword);
            return mapper.updateUsernoaddr(dto);
        }
        else if (dto.getAddr() != null) {
            return mapper.updateUserwithAddr(dto);
        }else {
            return mapper.updateUserSimple(dto);
        }
    }
    public int memberDelete( String u_email){
        log.info(u_email);
        return mapper.deleteUser(u_email);

    }
}



