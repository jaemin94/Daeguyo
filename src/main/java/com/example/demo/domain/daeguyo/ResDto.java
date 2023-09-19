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

}
