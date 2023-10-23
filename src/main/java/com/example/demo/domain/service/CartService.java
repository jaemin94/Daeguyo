package com.example.demo.domain.service;

import com.example.demo.domain.daeguyo.CartDto;

import com.example.demo.domain.daeguyo.OrderDto;
import com.example.demo.domain.daeguyo.PaymentDto;
import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.mapper.CartMapper;
import com.example.demo.domain.mapper.OrderMapper;
import com.example.demo.domain.mapper.PaymentMapper;

import com.example.demo.domain.daeguyo.MenuDto;
import com.example.demo.domain.mapper.CartMapper;
import com.example.demo.domain.mapper.MenuMapper;

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
    private MenuMapper menuMapper;
    @Autowired
    private PaymentMapper paymapper;

    public List<CartDto> selectAllByEmail(String u_email){

        return cartMapper.selectAll(u_email);
    }


    public void updateOrderAmount(CartDto dto)  {


        cartMapper.updateOrder(dto);
    }


    public boolean addToCart(CartDto cartDto) {
        String u_email = cartDto.getU_email();

        // u_email을 기반으로 기존의 menu_id 검색
        String existingMenuId = cartMapper.findMenuIdByUEmail(u_email);

        // menu_id를 기반으로 res_id 검색
        String currentResId = menuMapper.findResIdByMenuId(cartDto.getMenu_id());
        String existingResId = existingMenuId != null ? menuMapper.findResIdByMenuId(existingMenuId) : null;

        // 현재 항목의 res_id와 기존 항목의 res_id가 다르면 기존 항목 삭제
        if (existingResId != null && !existingResId.equals(currentResId)) {
            cartMapper.deleteByUEmail(u_email);
        }

        // 기존에 있는 메뉴+옵션 조합인지 검색하고,
        CartDto existingItem = cartMapper.ExistOrNot(u_email, cartDto.getMenu_id(), cartDto.getSelected_option());

        if (existingItem != null) {
            int newCount = existingItem.getCount() + 1; //그렇다면 -> 수량추가 (update)
            cartMapper.updateCount(existingItem.getCart_id(), newCount);
        } else {
            cartMapper.insertCart(cartDto); // 아니라면 -> 데이터 입력 (insert)
        }

        return true;
    }

    public int deleteFromCart(String cart_id){
        return cartMapper.deleteOrder(cart_id);
    }


}