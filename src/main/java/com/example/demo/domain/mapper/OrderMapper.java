package com.example.demo.domain.mapper;


import com.example.demo.domain.daeguyo.OrderDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from tbl_order")
    public List<OrderDto> selectAll();
    @Select("select * from tbl_order where order_id=#{order_id}")
    public OrderDto selectOne(String order_id);

    //myPage.html에서 사용자 주문 기록 조회
    @Select("select * from tbl_order where u_email=#{u_email}")
    public OrderDto userOrder(String u_email);

    //myPage.html에서 사용자 주문 횟수 출력
    @Select("select count(*) from tbl_order where u_email=#{u_email}")
    public int userOrderCount(String u_email);

    @Select("select * from tbl_order where res_id=#{res_id}")
    public OrderDto resOrder(String res_id);

    @Insert("")
    public int insertOrder();
    @Update("")
    public int updateOrder();
    @Delete("")
    public int deleteOrder();


}
