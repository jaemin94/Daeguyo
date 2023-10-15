package com.example.demo.domain.mapper;

import com.example.demo.domain.daeguyo.CouponDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CouponMapper {

    @Select("select * from tbl_coupon")
    public List<CouponDto> selectAll();

    @Select("select * from tbl_coupon where coupon_id = #{coupon_id}")
    public CouponDto selectOne(String coupon_id);

    //회원이 가지는 모든 쿠폰 검색
    @Select("select * from tbl_coupons where u_email = #{u_email}")
    public CouponDto userCoupon(String u_email);

    //회원이 가지는 모든 쿠폰 개수 검색
    // myPage.html에서 쿠폰 개수 출력해야
    @Select("select count(*) from tbl_coupons where u_email = #{u_email}")
    public int userCouponCount(String u_email);

    @Insert("")
    public int insertCoupon();

    @Update("")
    public int updateCoupon();

    @Delete("")
    public int deleteCoupon();
}
