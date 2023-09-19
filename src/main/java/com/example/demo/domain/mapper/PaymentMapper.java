package com.example.demo.domain.mapper;

import com.example.demo.domain.daeguyo.PaymentDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaymentMapper {

    @Select("select * from tbl_payment")
    public List<PaymentDto> selectAll();

    @Select("select * from tbl_payment where payment_id= #{payment_id}")
    public PaymentDto selectOne(String payment_id);

    @Insert("")
    public int insertPayment();

    @Update("")
    public int updatePayment();

    @Delete("")
    public int deletePayment();

}
