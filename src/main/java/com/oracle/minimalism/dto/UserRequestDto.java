package com.oracle.minimalism.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	@Pattern(regexp = "[a-zA-Z0-9]{6,15}" , message = "아이디 형식에 맞지 않습니다.")
	@Size(min =6, max=10, message = "아이디는 영문소문자/숫자 6~15자 사이 입니다.")
	private String id;			// 아이디
	
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String password;	// 비밀번호 (security)
	
	@NotBlank(message = "비밀번호확인은 필수 입력 값입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String passwordck;
	
	@NotBlank(message = "이름은 필수 입력 값입니다.")
	@Pattern(regexp = "[가-힣]{2,4}" , message = "이름 형식에 맞지 않습니다.")
	private String username;	// 이름 (security)
	
	@NotBlank(message = "생년월일은 필수 입력 값입니다.")
	@Size(min =6, max=6, message = "생년월일 형식을 맞춰주세요.")
	private String age;			// 생년월일
	
	@NotBlank(message = "번호는 필수 입력 값입니다.")
	@Pattern(regexp ="^\\d{2,3}-\\d{3,4}-\\d{4}$" , message = "번호 형식에 맞지 않습니다.")
	private String phone;		// 전화번호
	
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}", message = "이메일 형식에 맞지 않습니다.")
	private String email;		// 이메일
	
	@NotBlank(message = "주소는 필수 입력 값입니다.")
	private String address1;	// 우편번호
	
	@NotBlank(message = "주소는 필수 입력 값입니다.")
	private String address2;	// 주소
	
	@NotBlank(message = "주소는 필수 입력 값입니다.")
	private String address3;	// 상세주소	
	
	private int    user_delete = 0;	// 삭제여부
	private String role; 		// ROLE_USER, ROLE_ADMIN
}
