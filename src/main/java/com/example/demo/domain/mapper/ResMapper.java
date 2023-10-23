package com.example.demo.domain.mapper;

import com.example.demo.domain.daeguyo.ResDto;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface ResMapper {

    @Select("select * from tbl_res")
    public List<ResDto> selectAll();
    @Select("select * from tbl_res where res_id =#{res_id}")
    public ResDto selectOne(String res_id);

    @Select("select * from tbl_res where food_catagory =#{food_catagory}")
    public List<ResDto> selectCatagory(String food_catagory);
    @Insert("")
    public int insertRes();
    @Update("")
    public int updateRes();
    @Delete("")
    public int deleteRes();


}
