package com.example.demo.domain.service;

import com.example.demo.domain.daeguyo.CartDto;
import com.example.demo.domain.daeguyo.OrderDto;
import com.example.demo.domain.daeguyo.PaymentDto;
import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.mapper.CartMapper;
import com.example.demo.domain.mapper.OrderMapper;
import com.example.demo.domain.mapper.PaymentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CartService {


    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderMapper mapper;

    @Autowired
    private PaymentMapper paymapper;

    public List<CartDto> SearchOption( ){

//        CartDto cart = new CartDto();
//        cart.setCart_id(UUID.randomUUID().toString());
//        cart.setMenu_id(cart.getMenu_id());
//        cart.setCount(cart.getCount());
//        cart.setSelected_option(cart.getSelected_option());
        return cartMapper.CartList();

    }
    public void updateOrderAmount(CartDto dto)  {


        cartMapper.updateOrder(dto);
    }


    public void addOrder(CartDto cartData) {
        // 주문 정보 데이터베이스에 저장
        OrderDto orderData = new OrderDto();
        orderData.setMenu_id(cartData.getMenu_name());
        orderData.setU_email(cartData.getU_email());
        orderData.setOrder_amount(cartData.getCount());
        orderData.setTotal_price(cartData.getPrice());
        orderData.setOrder_id(UUID.randomUUID().toString());
        orderData.setOrder_date(LocalDateTime.now());
        orderData.setSelected_option(cartData.getSelected_option());
        orderData.setRes_id(cartData.getRes_name());
        orderData.setCounpon_id("default_coupon");


        cartMapper.insertOrder(orderData);


    }




    public Map<String, Object> getUserAndOrderDetails(String cart_id) {
        Map<String, Object> details = cartMapper.selectUserOrderDetails(cart_id);

        if (details != null && !details.isEmpty()) {
            CartDto orders = new CartDto();
            UserDto users = new UserDto();

            // 데이터베이스에서 조회한 결과를 기반으로 DTO 객체의 필드 설정
            orders.setRes_name((String) details.get("res_id"));
            users.setNickname((String) details.get("nickname"));
            users.setPhone((String) details.get("phone"));

            // DTO 객체의 값들을 다시 맵에 넣음
            details.put("orderName", orders.getRes_name());
            details.put("customerId", users.getNickname());
            details.put("phoneNumber", users.getPhone());

            System.out.println(details.size());
            System.out.println("null?2 ="+details);
        } else {
            // 주문 정보가 없는 경우 메시지 설정
            details = new HashMap<>();
            details.put("message", "Order not found");
        }

        return details;
    }

    public List<OrderDto> getCartItems() {
        List<OrderDto> asd = mapper.selectByUserId();

        if (asd == null) {
            // 데이터베이스에서 주문 데이터를 가져올 수 없는 경우, 빈 리스트 또는 다른 처리를 수행
            System.out.println("null? ="+asd);
            System.out.println(asd.size());
            return Collections.emptyList(); // 빈 리스트를 반환하거나 다른 적절한 처리 수행
        }
        System.out.println("null? ="+asd);
        System.out.println(asd.size());
        return asd;
    }

    public Map<String, Object> paymentInsert(PaymentDto paymentData){
        System.out.println("pay? ="+paymentData);

        int result = paymapper.insertPayment(paymentData);
        System.out.println("pay2? ="+paymentData);

        // 결과 값을 포함하는 Map 객체 생성
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }

    public int cartDelete(CartDto dto) {
        return cartMapper.deleteOrder(dto.getCart_id());
    }


}
