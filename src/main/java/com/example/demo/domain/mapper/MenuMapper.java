package com.example.demo.domain.mapper;


import com.example.demo.domain.daeguyo.MenuDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper {

    @Select("select * from tbl_menu")
    public List<MenuDto> selectAll();
    @Select("select * from tbl_menu where menu_id =#{menu_id}")
    public MenuDto selectOne(String menu_id);
    @Insert("")
    public int insertMenu();
    @Update("")
    public int updateMenu();
    @Delete("")
    public int deleteMenu();

}
