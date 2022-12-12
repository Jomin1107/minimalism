package com.oracle.minimalism.mjController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.minimalism.dto.CartDto;
import com.oracle.minimalism.dto.UserDto;
import com.oracle.minimalism.mjService.CartService;


@Controller
public class CartController {

	@Autowired
	private CartService cartService;

	/* 장바구니 추가
	 * 0 : 등록 실패 , 1 : 등록 성공 , 2 : 등록된 데이터 존재 , 5 : 로그인 필요  */
	@PostMapping("/cart/add")
	@ResponseBody  //  이 메서드는 화면을 반환하는 것이 아니라 데이터를 바로 반환하기 때문에 @ResponseBody를 추가
	public String addCartPOST(CartDto cart, HttpServletRequest request) {
		System.out.println("CartController addCartPOST 실행");
		System.out.println("CartController addCartPOST cart.getProduct_number()->"+cart.getProduct_number());
		System.out.println("CartController addCartPOST cart.getProduct_count()->"+cart.getProduct_count());
		
		// 로그인 체크
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("loginUser");
		System.out.println("CartController addCartPOST userDto->"+userDto);
		
		if(userDto == null) {
			System.out.println("CartController addCartPOST userDto => null");
			return "5";  // 로그인되지 않았을 경우 5 반환 
		}
		// 세션에서 받아온 유저아이디 세팅
		cart.setId(userDto.getId());
	
		// 카트 등록
		int result = cartService.addCart(cart);
		System.out.println("CartController addCartPOST result => " + result);
		
		return result + "";
	}
	
	/* 장바구니 페이지 이동 */
	@GetMapping("/cart")
	public String cartPage() {
		System.out.println("CartController cartPage 실행");
		
		return "/cart";
	}
	
	@GetMapping("/cart/{id}")
	public String cartPageGET(@PathVariable("id") String id, Model model) {
		System.out.println("CartController cartPageGET 실행");
		System.out.println("id => " + id);
		
		model.addAttribute("cartInfo", cartService.getCartList(id));
		
		return "/cart";
	}
	
	/* 장바구니 수량 수정 */
	@PostMapping("/cart/update")
	@ResponseBody  //  이 메서드는 화면을 반환하는 것이 아니라 데이터를 바로 반환하기 때문에 @ResponseBody를 추가
	public String updateCartPOST(CartDto cart) {
		System.out.println("CartController updateCartPOST 실행");
		System.out.println("CartController updateCartPOST cart.getCart_number() -> " + cart.getCart_number());
		System.out.println("CartController updateCartPOST cart.getProduct_count() -> " + cart.getProduct_count());
		
		int modCount = cartService.modifyCount(cart);
		String modCntStr = String.valueOf(modCount);
		
		return modCntStr; 
	}
	
	/* 장바구니 삭제 */
	@PostMapping("/cart/delete")
	@ResponseBody  //  이 메서드는 화면을 반환하는 것이 아니라 데이터를 바로 반환하기 때문에 @ResponseBody를 추가
	public String deleteCartPOST(CartDto cart) {
		System.out.println("CartController deleteCartPOST 실행");
		System.out.println("CartController deleteCartPOST cart.getCart_number() ->" + cart.getCart_number());
		
		int delCount = cartService.deleteCart(cart);
		String delCntStr = String.valueOf(delCount);
		//cartService.deleteCart(cart.getCart_number());
		
		return delCntStr;
	}
	
	/* 장바구니 전체삭제 */
	@PostMapping("/cart/deleteAll")
	@ResponseBody  //  이 메서드는 화면을 반환하는 것이 아니라 데이터를 바로 반환하기 때문에 @ResponseBody를 추가
	public String deleteAllCartPOST(CartDto cart) {
		System.out.println("CartController deleteAllCartPOST 실행");
		System.out.println("CartController deleteAllCartPOST cart.getId() ->" + cart.getId());
		
		int deleteAll = cartService.deleteAllCart(cart);
		String delAllStr = String.valueOf(deleteAll);
		//cartService.deleteCart(cart.getCart_number());
		
		return delAllStr;
	}
}
