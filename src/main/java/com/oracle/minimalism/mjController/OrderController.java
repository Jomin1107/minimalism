package com.oracle.minimalism.mjController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oracle.minimalism.dto.OrderDto;
import com.oracle.minimalism.dto.ProductDto;
import com.oracle.minimalism.dto.UserDto;
import com.oracle.minimalism.hjService.ProductDetailService;
import com.oracle.minimalism.mjService.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductDetailService productService;
	
	
	/* 주문 페이지 이동 */
	@GetMapping("/order")
	public String orderPage() {
		System.out.println("OrderController orderPage 실행");
		
		return "/order";
	}
	
	@GetMapping("/order/page")
	public String orderPageGET(OrderDto orderDto, Model model, HttpSession session) {
		System.out.println("OrderController orderPageGET 실행");
		System.out.println("id => " + orderDto.getId());
		System.out.println("getProduct_number => " + orderDto.getProduct_number());
		System.out.println("getProduct_count => " + orderDto.getProduct_count());
		
		ProductDto productDto = new ProductDto();
		productDto.setProduct_number(orderDto.getProduct_number());
		model.addAttribute("product", productService.productDetail(productDto));
		model.addAttribute("order", orderDto);
		
		UserDto user = (UserDto) session.getAttribute("loginUser");
    	String msg;
    	if(user == null) {
    		msg = "로그인 시간이 만료 되었습니다.";
    		session.setAttribute("msg", msg);
    		return "/loginForm";
    	}
		return "/order";
		
	}
	
}
