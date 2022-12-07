package com.oracle.minimalism.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.oracle.minimalism.dto.UserDto;



@Mapper // xml로 작성한 sql을 맵핑하는 Type 지정 
public interface UserMapper {
	int insert(UserDto user); // 회원가입
	UserDto login(String id,String password); // 로그인
	UserDto findbyId(String id);
	UserDto findbyusername(String username);
	int update(UserDto user);
	int update1(UserDto user);
	
}