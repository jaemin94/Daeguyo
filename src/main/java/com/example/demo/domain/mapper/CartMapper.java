package com.example.demo.domain.mapper;

import com.example.demo.domain.daeguyo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {


    @Select("select * from tbl_cart  where u_email = #{id} ")
    List<CartDto> CartList(String id);


    @Update("UPDATE tbl_cart SET count = #{count} WHERE cart_id = #{cart_id}")
    public void updateOrder(CartDto dto);




    @Delete("DELETE FROM tbl_cart WHERE cart_id = #{cart_id}")
    public int deleteOrder(String cart_id);



    @Select("select * from tbl_cart where cart_id =#{cart_id}")
    public CartDto selectOne(String cart_id);

    @Select("SELECT * FROM tbl_cart WHERE u_email = #{u_email} AND menu_id = #{menu_id} AND selected_option = #{selected_option}")
    public CartDto  ExistOrNot(@Param("u_email") String u_email, @Param("menu_id") String menu_id, @Param("selected_option") String selected_option);

    @Update("UPDATE tbl_cart SET count = #{count} WHERE cart_id = #{cart_id}")
    void updateCount(@Param("cart_id") String cart_id, @Param("count") int count);

    @Select("SELECT menu_id FROM tbl_cart WHERE u_email = #{u_email} LIMIT 1")
    String findMenuIdByUEmail(String u_email);

    @Delete("DELETE FROM tbl_cart WHERE u_email = #{u_email}")
    void deleteByUEmail(String u_email);

    @Insert("insert into tbl_cart values (#{cart_id}, #{u_email}, #{menu_id}, #{count}, #{selected_option})")
    public int insertCart(CartDto cartDto);

    @Update("UPDATE tbl_coupons SET status = 'y' WHERE coupon_id = #{coupon_id} ")
    void UpdateCoupon(CouponDto cdto);









}
