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
    @Select("select * from tbl_user where u_email=#{u_email}")
    public UserDto selectOne(@Param("u_email") String u_email);

    // 회원 추가/가입
    @Insert("insert into tbl_user values(#{id},#{u_email},#{addr}, #{password}, #{phone}, #{nickname},#{role},#{user_grade})")
    public int insertUser(UserDto dto);

    // 회원수정
    @Update("update tbl_user set nickname = #{nickname}, addr = #{addr}, phone =#{phone}, password =#{password} where u_email=#{u_email}")
    public int updateUserall(UserDto dto);

    @Update("update tbl_user set password =#{password} , nickname =#{nickname}, phone =#{phone} where u_email=#{u_email}")
    public int updateUsernoaddr(UserDto dto);

    @Update("update tbl_user set nickname =#{nickname}, phone =#{phone} where u_email=#{u_email}")
    public int updateUserSimple(UserDto dto);

    @Update("update tbl_user set nickname = #{nickname}, addr = #{addr}, phone =#{phone}, where u_email=#{u_email}")
    public int updateUserwithAddr(UserDto dto);

    // 회원삭제
    @Delete("delete from tbl_user where u_email=#{u_email}")
    public int deleteUser(String u_email);

}
