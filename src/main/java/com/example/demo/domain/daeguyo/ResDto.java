package com.example.demo.domain.daeguyo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResDto {

    private String res_id;
    private String res_name;
    private String addr;
    private String phone;
    private String password;
    private double res_rating;
    private boolean open_status;
    private String food_catagory;
    private String res_image;
    private String min_order_price;
    private String order_tax;
    private String keywords;
    //식당 logo 이미지 == res_image
    // 식당 메인 배너 이미지 컬럼 추가 / == res_main_image
    // review_tab.html에서 사용
    private String res_main_image;
}
