package com.example.demo.domain.mapper;

import com.example.demo.domain.daeguyo.ReviewDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {

    @Select("select * from tbl_review")
    public List<ReviewDto> selectAll();
    @Select("select * from tbl_review where r_id=#{r_id}")
    public ReviewDto selectOne(String r_id);
    @Insert("insert into tbl_review values(r_id=#{r_id}, u_email=#{nickname}, res_id=#{menu_name}, order_id=#{order_id}, reviewdate=null, img=#{img}, content=#{content}, rating=#{rating})")
    public int insertReview();
    @Update("update tbl_review")
    public int updateReview();
    @Delete("delete from tbl_review where r_id=#{r_id}")
    public int deleteReview();

}
