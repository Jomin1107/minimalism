package com.oracle.minimalism.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.minimalism.dto.UserDto;

public class ReviewInterceptor implements HandlerInterceptor {
	
	public ReviewInterceptor() {
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("ReviewInterceptor <preHandle> 1 Start...");
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		System.out.println("Bean   : " + method.getBean());
		System.out.println("method : " + methodObj);
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("ReviewInterceptor <postHandle> 3 Start...");
		
		// session의 "user"를 불러들여서 데이터 있는지 없는지를 통해 로그인 여부를 판단
		HttpSession session = request.getSession();
		session.setAttribute("reviewJspPage", "review.jsp");
		
		UserDto userDto = (UserDto)session.getAttribute("loginUser");
	    System.out.println("postHandle userDto -> " + userDto);  // 로그인 유저가 없기 때문에 userDto가 'null'로 나온다.
		
		if(userDto == null) {  // user가 null이면
			System.out.println("User not exists");
			response.sendRedirect("/loginForm");  // 로그인 페이지로 이동
		} else {  // user가 존재하면
			System.out.println("User exists");
		}
	}
}
