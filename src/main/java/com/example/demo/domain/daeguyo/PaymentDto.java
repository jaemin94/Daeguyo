package com.example.demo.domain.daeguyo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private String payment_id;
    private String order_id;
    private String pay_method;
    private LocalDateTime pay_date;
    private String pay_status;
}
