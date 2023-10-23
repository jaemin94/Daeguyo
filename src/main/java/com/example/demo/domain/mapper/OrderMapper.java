package com.example.demo.domain.mapper;


import com.example.demo.domain.daeguyo.OrderDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    @Select("select * from tbl_order")
    public List<OrderDto> selectAll();

    @Select("select * from tbl_order where order_id=#{order_id}")
    public List<OrderDto> selectOne(String order_id);

    @Select("select * from tbl_order where order_id=#{order_id}")
    public OrderDto selectOne1(String order_id);


    //myPage.html에서 사용자 주문 기록 조회
    @Select("select * from tbl_order where u_email=#{u_email}")
    public List<OrderDto> userOrder(String u_email);

    //myPage.html에서 사용자 주문 횟수 출력
    @Select("select count(*) from tbl_order where u_email=#{u_email}")
    public int userOrderCount(String u_email);

    @Insert("insert into tbl_order values(#{order_id},null,#{u_email},#{menu_name},#{res_id},#{select_option},#{order_amount},#{total_price},#{order_status},now())")
    public int addOrder(OrderDto dto);

    @Delete("DELETE FROM tbl_order WHERE order_id = #{order_id}")
    int delete(String order_id);



}
