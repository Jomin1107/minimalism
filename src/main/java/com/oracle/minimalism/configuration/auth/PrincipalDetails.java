package com.oracle.minimalism.configuration.auth;


import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.oracle.minimalism.dto.UserDto;


public class PrincipalDetails implements UserDetails, OAuth2User {

    private UserDto user;

    private Map<String, Object> attributes;
    
    public PrincipalDetails(UserDto user) {
        this.user = user;
    }
    
	// OAuth2.0 로그인시 사용
	public PrincipalDetails(UserDto user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}

    public UserDto getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	//리소스 서버로 부터 받은 회원 정보
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public String getName() {
		return user.getId();
	}
}