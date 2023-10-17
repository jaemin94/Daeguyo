package com.example.demo.domain.mapper;

import com.example.demo.domain.dto.SmsComfirmDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SmsComfirmMapper {

    @Insert("insert into tbl_phonecheck values(#{phone},#{smsComfirmnum})")
    public int addSmsComfirmNum(SmsComfirmDto dto);

    @Select("select * from tbl_phonecheck where phone=#{phone}")
    public SmsComfirmDto selectOne(String phone);
}
