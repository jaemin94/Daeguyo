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

    @Insert("")
    public int insertCoupon();

    @Update("")
    public int updateCoupon();

    @Delete("")
    public int deleteCoupon();
}
