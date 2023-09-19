package com.example.demo.domain.daeguyo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {

    private String menu_id;
    private String res_id;
    private String menu_name;
    private int price;
    private String menu_detail;
    private byte[] img;
    private String options;
    private String menu_catagory;

}
