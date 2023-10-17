package com.example.demo.domain.mapper;


import com.example.demo.domain.daeguyo.OrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper{

    @Select("select * from tbl_order ")
    List<OrderDto> selectByUserId();

    @Query("delete from tbl_order where order_id = #{order_id}")
    int deleteOrder(String order_id);











}
