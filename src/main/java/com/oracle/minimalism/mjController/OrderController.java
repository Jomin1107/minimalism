package com.oracle.minimalism.mjController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.minimalism.dto.CartDto;
import com.oracle.minimalism.dto.OrderDto;
import com.oracle.minimalism.dto.OrderDtoVO;
import com.oracle.minimalism.dto.UserDto;
import com.oracle.minimalism.mjService.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	/* 주문 페이지 이동 */
	@GetMapping("/order")
	public String orderPage() {
		System.out.println("OrderController orderPage 실행");
		
		return "/order";
	}
	
	/* 제품상세페이지에서 한가지 상품 구매하기 */
	   @GetMapping("/order/page1")
	   public String orderPageGET1(OrderDtoVO orderVo, Model model, HttpSession session) {
	      System.out.println("OrderController orderPageGET1 실행");
	      int productCount = 0;
	      int orderPriceSum = 0;
	      productCount = orderVo.getProduct_count();
	      System.out.println("orderVo.getProduct_count() "+ orderVo.getProduct_count());

	      // KTG 단일로우라는 전제(비즈니스적으로)
	      OrderDtoVO order = orderService.productDetailOrder(orderVo);

	      order.setProduct_count(productCount);
	      orderPriceSum = order.getProduct_price() * order.getProduct_count();
	      
	      model.addAttribute("order", order);
	      model.addAttribute("orderPriceSum", orderPriceSum);
	      
	      UserDto user = (UserDto) session.getAttribute("loginUser");
	       String msg;
	       if(user == null) {
	          msg = "로그인이 필요한 서비스입니다.";
	          session.setAttribute("msg", msg);
	          return "/loginForm";
	       }
	      return "/order";
	   }

	/* 장바구니에서 구매하기(여러개를 가져와서 계산) */
	@GetMapping("/order/page2")
	public String orderPageGET2(String chkSel[], String cart_number[], CartDto cartDto, Model model, HttpSession session) {
        System.out.println("OrderController orderPageGET2 실행");
        
        int orderPrice = 0;
		int orderPriceSum = 0;
		
		for(int i = 0; i < cart_number.length; i++) {
			System.out.println("kkkk.............." + chkSel[i]);
			if (chkSel[i].contains("1")) {
				System.out.println("cart_number[] " + i + "=> " + cart_number[i]);
				// cart_number를 가지고 상품이미지,상품번호,상품이름,상품가격,상품색상,수량
			    
			}
	    }

//		List<CartDto> orderList = orderService.productDetailOrderList(cartDto);
		
		System.out.println("orderPriceSum => " + orderPriceSum);
		
//		model.addAttribute("order",         orderList);
		model.addAttribute("orderPriceSum", orderPriceSum);
		
		UserDto user = (UserDto) session.getAttribute("loginUser");
    	String msg;
    	if(user == null) {
    		msg = "로그인이 필요한 서비스입니다.";
    		session.setAttribute("msg", msg);
    		return "/loginForm";
    	}
		return "/cartOrder";
	}
	
	/* 주문하기 */
	@PostMapping("/order/create")
	public String createOrder(OrderDtoVO order, HttpSession session) {
		System.out.println("OrderController createOrder 실행");
		System.out.println("OrderDtoVO => " + order);
		
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
	
	/* 주문 취소 */
	@PostMapping("/order/cancle")
	@ResponseBody
	public String cancleOrderPOST(OrderDto order) {
		System.out.println("OrderController cancleOrderPOST 실행");
		System.out.println("OrderController cancleOrderPOST order.getOrder_number() ->" + order.getOrder_number());
		
		int cancleCount = orderService.cancleOrder(order);
		String cancleCntStr = String.valueOf(cancleCount);
		
		return cancleCntStr;
	}
}
