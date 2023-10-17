package com.example.demo.domain.mapper;

import com.example.demo.domain.daeguyo.CartDto;
import com.example.demo.domain.daeguyo.MenuDto;
import com.example.demo.domain.daeguyo.OrderDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {

    @Select("select * from tbl_cart  ")
    List<CartDto> CartList( );

    @Select("select * from tbl_menu where menu_id = #{menu_id}")
    List<MenuDto> MenuList();

    @Update("UPDATE tbl_cart SET count = #{count} WHERE cart_id = #{cart_id}")
    public void updateOrder(CartDto dto);

    @Insert("INSERT INTO tbl_order (order_id, u_email, menu_id, res_id, selected_option, order_amount, total_price) VALUES (#{order_id}, #{u_email}, #{menu_id}, #{res_id}, #{selected_option}, #{order_amount}, #{total_price})")
    public int insertOrder(OrderDto orderData);


    @Select("SELECT c.res_id AS res_id, u.nickname AS nickname, u.phone AS phone FROM tbl_cart c INNER JOIN tbl_user u ON c.u_email = u.u_email WHERE c.cart_id = #{cart_id}")
    Map<String, Object> selectUserOrderDetails(String cart_id);

    @Delete("DELETE FROM tbl_cart WHERE cart_id = #{cart_id}")
    public int deleteOrder(String cart_id);


}
