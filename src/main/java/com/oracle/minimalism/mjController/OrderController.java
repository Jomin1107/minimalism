package com.oracle.minimalism.mjController;

import java.util.ArrayList;
import java.util.List;

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
	
	/* 제품상세페이지에서 한가지 상품 구매하기 페이지 이동 */
	@GetMapping("/order/page1")
	public String orderPageGET1(OrderDtoVO orderVo, Model model, HttpSession session) {
		System.out.println("OrderController orderPageGET1 실행");
		System.out.println("OrderController orderPageGET1 orderVo.getProduct_count() ->" + orderVo.getProduct_count());
		int productCount = 0;
		int orderPriceSum = 0;
		productCount = orderVo.getProduct_count();

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

	/* 장바구니에서 여러개의 상품 구매하기 페이지 이동 */
	@GetMapping("/order/page2")
	public String orderPageGET2(String chkSel[], String cart_number[], CartDto cartDto, Model model, HttpSession session) {
        System.out.println("OrderController orderPageGET2 실행");
        List<String> cart_numberList = new ArrayList<String>();

        int orderPrice = 0;
		int orderPriceSum = 0;
		String  cart_numberStr = "";
		
		// 선택한 카트 리스트를  cart_numberList에 넣어주기 
		// -> productCartList에 파라메타로 넣어주기 위해서 
		for(int i = 0; i < cart_number.length; i++) {
			//System.out.println("kkkk.............." + chkSel[i]);
			if (chkSel[i].contains("1")) {
				//System.out.println("cart_number[] " + i + "=> " + cart_number[i]);
				cart_numberList.add(cart_number[i].toString());
			}
	    }

		//  선택한 상품을 cartOrder페이지로 가지고가서 보여준뒤 결제하기를 눌러서 결제
		List<CartDto> cartList = orderService.productCartList(cart_numberList);
		// cartList를 계산해서 결제금액 넣어주기 
		for(CartDto cart : cartList) {
			// 한 로우당 결제 금액
			orderPrice = cart.getProduct_price() * cart.getProduct_count();
			orderPriceSum += orderPrice;
			//System.out.println("orderPrice => " + orderPrice);
			//System.out.println("orderPriceSum => " + orderPriceSum);
		}
		
		System.out.println("OrderController orderPageGET2 cartList.size() => " + cartList.size());
		System.out.println("final orderPriceSum => " + orderPriceSum);
		
     	model.addAttribute("order", cartList);
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
	
	/* 상품상세페이지에서 한가지 상품 주문하기 */
	@PostMapping("/order/create1")
	public String createProductOrder(OrderDtoVO order, HttpSession session) {
		System.out.println("OrderController createOrder 실행");
		System.out.println("OrderDtoVO => " + order);
		
		int result = orderService.createProductOrder(order);
		System.out.println("result => " + result);
		
		String msg;
		if(result == 0) {
			msg = "결제에 실패하였습니다.";
			session.setAttribute("msg", msg);
			return "redirect:/";
		} else {
			msg = "결제가 완료되었습니다. 주문정보는 마이페이지에서 확인 가능합니다.";
			session.setAttribute("msg", msg);
			return "redirect:/";
		}
	}
	
	/* 카트에서 여러가지 상품 주문하기 */
	@PostMapping("/order/create2")
	public String createCartOrder(OrderDtoVO order,
						          int [] cart_number , 
						          int [] product_count , 
						          int [] product_number , 
						          int [] product_price,
			                      HttpSession session) {
		int result = 0;
		int resultTotal = 0;
		int order_number = 0;
		System.out.println("OrderController createCartOrder 실행");
		System.out.println("OrderDtoVO => " + order);
		// 1. order 저장후 order_number 가져오기
		// 2. cartOrder에서 제품에 관한 배열 정보 모으기
		// 3. 배열 정보 모은것을 order정보에 생성하기 -> 카트에서 주문하기
		//    createCartOrder2
		//  -> order_number 이용한 주문상세 저장
		order_number = orderService.createCartOrder1(order);
		System.out.println("order_number => " + order_number);

		for(int i =0; i< product_number.length; i++) {
			System.out.println("cart_number => " + cart_number[i]);
			System.out.println("product_number => " + product_number[i]);
			System.out.println("product_count => " + product_count[i]);
			System.out.println("product_price => " + product_price[i]);
			CartDto cart = new CartDto();
			cart.setCart_number(cart_number[i]);
			cart.setProduct_number(product_number[i]);
			cart.setProduct_count(product_count[i]);
			cart.setProduct_price(product_price[i]);
			cart.setId(order.getId());
			cart.setOrder_number(order_number);
			result = orderService.createCartOrder2(cart);
			resultTotal += result;
		}
		
		System.out.println("resultTotal => " + resultTotal);
		
		String msg;
		if(resultTotal < product_number.length) {
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
