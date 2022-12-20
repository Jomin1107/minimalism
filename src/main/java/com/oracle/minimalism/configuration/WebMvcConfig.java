package com.oracle.minimalism.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.oracle.minimalism.interceptor.QnAInterceptor;
import com.oracle.minimalism.interceptor.ReviewInterceptor;
import com.oracle.minimalism.interceptor.CartInterceptor;
import com.oracle.minimalism.interceptor.OrderInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new CartInterceptor())
								.addPathPatterns("/cart/**")  // "/cart" url을 타는 모든 요청이 CartInterceptor를 거친다
		                        .excludePathPatterns("/cart/add", "/cart/update");    // /cart/add, /cart/update 경로는 인터셉터가 가로채지 않는다
		
		registry.addInterceptor(new OrderInterceptor())
								.addPathPatterns("/order/**")  // "/order" url을 타는 모든 요청이 OrderInterceptor를 거친다
						        .excludePathPatterns("/order/page1", "/order/page2");

		/* 로그인 안된 유저가 URL에 "/reviewWriteForm"을 누르면 "ReviewInterceptor"가 처리해준다. */
		registry.addInterceptor(new QnAInterceptor())
								.addPathPatterns("/board/WriteQnAForm");
		
		/*로그인 안된 유저가  URL에  "/reviewWriteForm"을 누르면  "ReviewInterceptor"가 처리해준다.*/
		registry.addInterceptor(new ReviewInterceptor())
								.addPathPatterns("/board/WriteReviewForm");


	}
	
	
	
}
