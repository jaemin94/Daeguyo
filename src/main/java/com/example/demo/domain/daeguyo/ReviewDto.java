package com.example.demo.domain.daeguyo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private String r_id;
    private String u_email;
    private String order_id;
    private String res_id;
    private double rating;
    private byte[] img;
    private String content;
    private LocalDateTime reviewDate;


}
