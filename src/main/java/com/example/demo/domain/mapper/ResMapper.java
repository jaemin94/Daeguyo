package com.example.demo.domain.mapper;

import com.example.demo.domain.daeguyo.ResDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResMapper {

    @Select("select * from tbl_res")
    public List<ResDto> selectAll();
    @Select("select * from tbl_res where res_id =#{res_id}")
    public ResDto selectOne(String res_id);
    @Insert("")
    public int insertRes();
    @Update("")
    public int updateRes();
    @Delete("")
    public int deleteRes();

}
