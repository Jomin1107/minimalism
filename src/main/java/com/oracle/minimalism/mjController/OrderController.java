package com.oracle.minimalism.mjController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.minimalism.dto.OrderDto;
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
			msg = "결제가 완료되었습니다. 주문정보는 마이페이지에서 확인 가능합니다.";
			session.setAttribute("msg", msg);
			return "redirect:/";
		}
	}
	
	/* 주문내역 페이지 이동 */
//	@GetMapping(value = "/order/list")
//	public String userOrder() {
//		System.out.println("OrderController userOrder 실행");
//		
//		return "/order/list";
//	}
	
	/* 주문내역 페이지 이동 */
	@GetMapping("/order/list/{id}")
	public String userOrderList(@PathVariable("id") String id, Model model) {
		System.out.println("OrderController userOrderList 실행");
		System.out.println("id => " + id);
		
		model.addAttribute("orderInfo", orderService.getOrderList(id));
		
		return "/orderList";
	}
	
	/* 주문 상세 페이지 이동 */
	@GetMapping("/orderDetail/{order_number}")
	public String orderDetailGET(@PathVariable("order_number") int order_number, OrderDto orderDto, Model model) {
		System.out.println("OrderController orderDetailGET 실행");
		
		OrderDto orderDetail = orderService.orderDetail(orderDto);
		model.addAttribute("order", orderDetail);
		
		return "/orderDetail";
	}
}
