package com.example.demo.domain.daeguyo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private String cart_id;
    private String u_email;
    private String menu_id;
    private int count;
    private String selected_option;
    private String res_name;
    private int price;
}
