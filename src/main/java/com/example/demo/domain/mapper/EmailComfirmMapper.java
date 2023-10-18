package com.example.demo.domain.mapper;

import com.example.demo.domain.dto.EmailComfirmDto;
import com.example.demo.domain.dto.SmsComfirmDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmailComfirmMapper {

    @Insert("insert into tbl_emailcheck values(#{email},#{emailComfirm})")
    public int addEmailComfirm(EmailComfirmDto dto);

    @Select("select * from tbl_emailcheck where email=#{email}")
    public EmailComfirmDto selectOne(String email);
}
