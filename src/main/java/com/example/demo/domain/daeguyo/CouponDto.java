package com.example.demo.domain.daeguyo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {

    private int coupon_id;
    private String name;
    private String content;
    private int deductedPrice;
    private LocalDateTime createDate;
    private LocalDateTime expiredDate;
    private String status;
    private String u_mail;



}
