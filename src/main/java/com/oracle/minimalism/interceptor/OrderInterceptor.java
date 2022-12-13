package com.oracle.minimalism.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.minimalism.dto.UserDto;

public class OrderInterceptor implements HandlerInterceptor {
	public OrderInterceptor() {
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("OrderInterceptor preHandle Start...");
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("OrderInterceptor postHandle Start...");
		// session의 "user"를 불러들여서 데이터 있는지 없는지를 통해 로그인 여부를 판단
		HttpSession session = request.getSession();
		
		UserDto userDto = (UserDto)session.getAttribute("loginUser");
		System.out.println("postHandle userDto -> " + userDto);
		
		if(userDto == null) {  // user가 null이면
			System.out.println("User not exists");
			response.sendRedirect("/loginForm");  // 로그인 페이지로 이동
		} else {  // user가 존재하면
			System.out.println("User exists");
			//response.sendRedirect("/cart");  // 장바구니 페이지 이동
		}
		
	}
	
}
