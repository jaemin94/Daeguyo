package com.example.demo.domain.mapper;

import com.example.demo.domain.daeguyo.CartDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    @Select("select * from tbl_cart")
    public List<CartDto> selectAll();
    @Select("select * from tbl_cart where cart_id =#{cart_id}")
    public CartDto selectOne(String cart_id);
    @Insert("")
    public int insertCart();
    @Update("")
    public int updateCart();
    @Delete("")
    public int deleteCart();

}
