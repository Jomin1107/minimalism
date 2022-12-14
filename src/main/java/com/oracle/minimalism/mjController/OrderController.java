package com.oracle.minimalism.mjController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.minimalism.dto.OrderDtoVO;
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
	public String orderPageGET(OrderDtoVO orderVo, Model model, HttpSession session) {
		System.out.println("OrderController orderPageGET 실행");
		System.out.println("getProduct_number => " + orderVo.getProduct_number());
		System.out.println("getProduct_count => " + orderVo.getProduct_count());
		
		ProductDto productDto = new ProductDto();
		productDto.setProduct_number(orderVo.getProduct_number());
		model.addAttribute("product", productService.productDetail(productDto));
		model.addAttribute("order", orderVo);
		
		UserDto user = (UserDto) session.getAttribute("loginUser");
    	String msg;
    	if(user == null) {
    		msg = "로그인이 필요한 서비스입니다.";
    		session.setAttribute("msg", msg);
    		return "/loginForm";
    	}
		return "/order";
	}
	
	/* 주문하기 */
	@PostMapping("/order/create")
	public String createOrder(OrderDtoVO order, HttpSession session) {
		System.out.println("OrderController createOrder 실행");
		System.out.println("orderDto => " + order);
		
		int result = orderService.createOrder(order);
		System.out.println("result => " + result);
		
		String msg;
		if(result == 0) {
			msg = "결제에 실패하였습니다.";
			session.setAttribute("msg", msg);
			return "/order/page";
		} else {
			msg = "결제가 완료되었습니다.";
			session.setAttribute("msg", msg);
			return "redirect:/";
		}
	}
	
}
