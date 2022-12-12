package com.oracle.minimalism.mapper;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.oracle.minimalism.dto.UserDto;
import com.oracle.minimalism.dto.UserRequestDto;



@Mapper // xml로 작성한 sql을 맵핑하는 Type 지정 
public interface UserMapper {
	int join(@Valid UserRequestDto user); // 일반 회원가입
	int insert(UserDto user); // 소셜 회원가입
	UserDto login(String id,String password); // 로그인
	UserDto findbyusername(String username); // 이름을 통해 찾기
	UserDto findbyId(String id); // 아이디를 통해  찾기
	UserDto findbyEmail(String email); // 이메일을 통해 찾기
	int update(UserDto user); // 소셜로그인시 정보추가
	int update1(UserDto user); // 발급된 임시비밀번호로 변경시키기 
	int mypageUpdate(UserDto user); // 마이페이지 정보수정
	int mypagePwUpdate(UserDto user); // 마이페이지 비밀번호 변경
	int delete(UserDto user); // 회원탈퇴
	
}