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

    // 사용자 리뷰 조회
    @Select("select * from tbl_review where u_email=#{u_email}")
    public ReviewDto userReview(String u_email);
    // 사용자 리뷰 개수 출력
    @Select("select count(*) from tbl_review where u_email=#{u_email}")
    public int userReviewCount(String u_email);
    //review_tab.html page3에서 필요.
    @Select("select * from tbl_review where res_id=#{res_id}")
    public ReviewDto resReview(String res_id);

    @Update("update tbl_review")
    public int updateReview();
    @Delete("delete from tbl_review where r_id=#{r_id}")
    public int deleteReview();

}
