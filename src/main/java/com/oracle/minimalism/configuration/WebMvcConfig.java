package com.oracle.minimalism.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.oracle.minimalism.interceptor.CartInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new CartInterceptor())
								.addPathPatterns("/cart/**")  // "/cart" url을 타는 모든 요청이 CartInterceptor를 거친다
		                        .excludePathPatterns("/cart/add", "/cart/update");    // /cart/add, /cart/update 경로는 인터셉터가 가로채지 않는다
	}
}
