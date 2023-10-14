package com.example.demo.domain.mapper;


import com.example.demo.domain.daeguyo.OrderDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from tbl_order")
    public List<OrderDto> selectAll();
    @Select("select * from tbl_order where order_id=#{order_id}")
    public List<OrderDto> selectOne(String order_id);
    @Insert("")
    public int insertOrder();
    @Update("")
    public int updateOrder();

    @Delete("DELETE FROM tbl_order WHERE order_id = #{order_id}")
    int delete(String order_id);



}
