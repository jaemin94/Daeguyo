package com.example.demo.domain.mapper;


import com.example.demo.domain.daeguyo.OrderDto;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.testng.annotations.Optional;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    @Select("select * from tbl_order1")
    public List<OrderDto> selectAll();

    @Select("select * from tbl_order1 where order_id=#{order_id}")
    public List<OrderDto> selectOne(String order_id);

    @Select("select * from tbl_order1 where order_id=#{order_id}")
    public OrderDto selectOne1(String order_id);


    //myPage.html에서 사용자 주문 기록 조회
    @Select("select * from tbl_order where u_email=#{u_email}")
    public OrderDto userOrder(String u_email);

    //myPage.html에서 사용자 주문 횟수 출력
    @Select("select count(*) from tbl_order where u_email=#{u_email}")
    public int userOrderCount(String u_email);


    @Delete("DELETE FROM tbl_order1 WHERE order_id = #{order_id}")
    int delete(String order_id);

    @Insert("INSERT INTO tbl_order (order_id, coupon_id,u_email, menu_name, res_id, selecte_option, order_amount, total_price, order_status, order_date) values (#{order_id}, ${coupon_id}, #{u_email}, #{menu_name}, #{res_id}, #{selected_option}, #{order_amount}, #{total_price},  #{order_status}, #{order_date})")
    @Value("${orderData.order_id},${orderData.u_email},${orderData.coupon_id}, ${orderData.menu_name} ,${orderData.res_id}, ${orderData.selecte_option} ,${orderData.order_amount}, ${orderData.total_price}, ${orderData.order_status}, ${orderData.order_date}")
    public int insertOrder(OrderDto orderData);

}
