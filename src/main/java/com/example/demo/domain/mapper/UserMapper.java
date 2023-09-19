package com.example.demo.domain.mapper;

import com.example.demo.domain.daeguyo.UserDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {


    // 회원 전체 조회
    @Select("select * from tbl_user")
    public List<UserDto> selectAll();

    // 회원 단건 조회
    @Select("select * from tbl_user where u_email = #{u_email}")
    public UserDto selectOne(String u_email);

    // 회원 추가/가입
    @Insert("insert into tbl_user values(id=#{id},u_email=#{u_email},addr=#{addr}, password =#{password}, phone=#{phone}, nickname=#{nickname},role=#{role})")
    public int insertUser();

    // 회원수정
    @Update("update tbl_user set addr = =#{addr}, password =#{password} , nickname =#{nickname}, phone =#{phone} where u_email=#{u_email}")
    public int updateUser();

    // 회원삭제
    @Delete("delete from tbl_user where u_email=#{u_email}")
    public int deleteUser();

}
