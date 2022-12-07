package com.oracle.minimalism.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserDto {
	private String id;			// 아이디
	private String password;	// 비밀번호 (security)
	private String username;	// 이름 (security)
	private String age;			// 생년월일
	private String phone;		// 전화번호
	private String email;		// 이메일
	private String address1;	// 우편번호
	private String address2;	// 주소
	private String address3;	// 상세주소	
	private int    user_delete = 0;	// 삭제여부
	private String role; 		// ROLE_USER, ROLE_ADMIN
	
	// 소셜로그인을 하면 카카오/네이버에서 제공하는 사용자 정보 토큰을
	// provider로 가공하고 가공된 해당 사용자 정보를 데이터베이스에 넣을때 UserDto 형식으로 넣기 위해 필요
	@Builder
	 public UserDto(String id, String username, String password, String email, String phone, String age, String address1,String address2,String address3,int user_delete,String role) 
	{ 
		 this.id = id;
		 this.username = username; 
		 this.password = password; 
		 this.email =email; 
		 this.phone = phone;
		 this.age = age;
		 this.address1 = address1;
		 this.address2 = address2;
		 this.address3 = address3;
		 this.user_delete = user_delete;
		 this.role = role;
	}
}
