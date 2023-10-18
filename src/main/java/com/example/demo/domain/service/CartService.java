package com.example.demo.domain.service;

import com.example.demo.domain.daeguyo.*;

import com.example.demo.domain.mapper.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CartService {


    @Autowired
    private ResMapper resMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderMapper mapper;
     @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private PaymentMapper paymapper;
    @Autowired
    private CouponMapper couponMapper;

    public List<CartDto> SearchOption(String id){

        return cartMapper.CartList(id);


    }
    // 사용자 쿠폰 찾기
    public List<CouponDto> SearchCoupon(String id){
        return couponMapper.userCoupon(id);
    }
    // 수량 조절 서비스
    public void updateOrderAmount(CartDto dto)  {


        cartMapper.updateOrder(dto);
    }



    //tbl_payment에 값 전달
    public Map<String, Object> paymentInsert(PaymentDto paymentData){


        int result = paymapper.insertPayment(paymentData);


        // 결과 값을 포함하는 Map 객체 생성
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }
    //장바구니 삭제
    public int cartDelete(CartDto dto) {
        return cartMapper.deleteOrder(dto.getCart_id());
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

    //쿠폰 사용
    public void couponUpdate(CouponDto cdto) {

        cartMapper.UpdateCoupon(cdto);
    }

    //tbl_order로 값 넘기기
    public int createOrder(CartDto dtos) {

        return mapper.insertOrder(dtos);
    }

    //가게이름 찾기
    public ResDto search_res_name(String res_id) {

        return resMapper.search_res_name(res_id);
    }

    //메뉴이름 찾기
    public List<MenuDto> search_menu_name(List<CartDto> options) {
        List<MenuDto> lst = new ArrayList();
        for(CartDto dto : options){
            MenuDto result = menuMapper.search_menu_name(dto.getMenu_id());
            lst.add(result);
        }

        return lst;
    }
}
