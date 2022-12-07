package com.oracle.minimalism.configuration.oauth.provider;

import java.util.Map;

public interface OAuth2UserInfo {
	String getProviderId();
	String getProvider();
	String getEmail();
	String getName();
	String getUsername();
	Map<String, Object> getAttributes();
}
