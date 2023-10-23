package com.example.demo.domain.mapper;


import com.example.demo.domain.daeguyo.MenuDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;

import java.awt.*;
import java.util.List;

@Mapper
public interface MenuMapper {

    @Select("select * from tbl_menu")
    public List<MenuDto> selectAll();
    @Select("select * from tbl_menu where menu_id =#{menu_id}")
    public List<MenuDto> selectOne(String menu_id);

    // review_tab.html page1에서 필요
    @Select("select * from tbl_menu where res_id =#{res_id}")
    public List<MenuDto> ResMenu(String res_id);

    @Select("SELECT res_id FROM tbl_menu WHERE menu_id = #{menu_id}")
    String findResIdByMenuId(String menu_id);

    @Select("SELECT * FROM tbl_menu WHERE menu_id = #{menu_id}")
    public List<MenuDto> findResIdByMenuId1(String menu_id);

    @Insert("")
    public int insertMenu();
    @Update("")
    public int updateMenu();
    @Delete("")
    public int deleteMenu();

}
