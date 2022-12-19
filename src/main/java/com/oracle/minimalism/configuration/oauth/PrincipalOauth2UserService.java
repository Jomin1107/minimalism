package com.oracle.minimalism.configuration.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.oracle.minimalism.configuration.auth.PrincipalDetails;
import com.oracle.minimalism.configuration.oauth.provider.KakaoUserInfo;
import com.oracle.minimalism.configuration.oauth.provider.NaverUserInfo;
import com.oracle.minimalism.configuration.oauth.provider.OAuth2UserInfo;
import com.oracle.minimalism.dto.UserDto;
import com.oracle.minimalism.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserMapper userMapper;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	// userRequest 는 code를 받아서 accessToken을 응답 받은 객체
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest); // 회원 프로필 조회

		// code를 통해 구성한 정보
		System.out.println("userRequest clientRegistration : " + userRequest.getClientRegistration());
		
		// token을 통해 응답받은 회원정보
		System.out.println("oAuth2User : " + oAuth2User);
	
		return processOAuth2User(userRequest, oAuth2User);
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

		int insert = 0;
		// Attribute를 파싱해서 공통 객체로 묶는다. 관리가 편함.
		OAuth2UserInfo oAuth2UserInfo = null;
		
		if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){
			System.out.println("네이버 로그인 요청");
			 oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
		}  else if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")){
            System.out.println("카카오 로그인 요청");
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }
		else {
			System.out.println("오류");
		}
		
		// 	token을 통해 응답받은 회원정보 확인가능
		System.out.println("oAuth2UserInfoEmail:" + oAuth2UserInfo.getEmail());
		System.out.println("oAuth2UserInfoName:" + oAuth2UserInfo.getName());
		System.out.println("oAuth2UserInfoUsername:" + oAuth2UserInfo.getUsername());
		System.out.println("oAuth2UserInfo.getProvider() : " + oAuth2UserInfo.getProvider());
		System.out.println("oAuth2UserInfo.getProviderId() : " + oAuth2UserInfo.getProviderId());
		
		String email = oAuth2UserInfo.getEmail();
		String tempemail = email.split("@")[0];
		System.out.println(tempemail);
		String id = oAuth2UserInfo.getProvider() + "_" + tempemail;
		String password = System.currentTimeMillis() +  oAuth2UserInfo.getProviderId();
		System.out.println(password);
		String bCryptpw = bCryptPasswordEncoder.encode(password);
		
		UserDto user = userMapper.findbyId(id);

		if(user == null) {
			user = UserDto.builder()
                    .id(id)
                    .password(bCryptpw)
                    .username(oAuth2UserInfo.getName())
                    .email(oAuth2UserInfo.getEmail())
                    .phone("")
                    .age("")
                    .address1("")
                    .address2("")
                    .address3("")
                    .user_delete(0)
                    .role("role_user")
                    .build();
			System.out.println(user);
		insert = userMapper.insert(user);
		
		}else {
			System.out.println("이미 존재하는 회원입니다.");
		}
		
		if(insert > 0 ) {
			System.out.println("회원가입 / 로그인 성공");
		}
		
		return new PrincipalDetails(user, oAuth2User.getAttributes());
	}
}