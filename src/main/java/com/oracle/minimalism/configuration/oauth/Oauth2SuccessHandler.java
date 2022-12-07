package com.oracle.minimalism.configuration.oauth;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.oracle.minimalism.configuration.auth.PrincipalDetails;

@Component
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        PrincipalDetails principalDetail = (PrincipalDetails) authentication.getPrincipal();
        String username = principalDetail.getUsername();
        System.out.println(username);
        String url = makeRedirectUrl(username);
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("url: " + url);
        getRedirectStrategy().sendRedirect(request, response, url);

    }
    private String makeRedirectUrl(String username) {
        return UriComponentsBuilder.fromUriString("/Oauth2Login/"+username)
                .build().toUriString();
    }
}
