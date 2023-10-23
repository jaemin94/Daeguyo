package com.example.demo.domain.daeguyo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String order_id;
    private String coupon_id;
    private String u_email;
    private String menu_name;
    private String res_id;
    private String select_option;
    private int order_amount;
    private int total_price;
    private String order_status;
    private LocalDateTime order_date;

}
