package com.oracle.minimalism.mjService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.minimalism.dto.CartDto;
import com.oracle.minimalism.mjDAO.CartDao;

import lombok.RequiredArgsConstructor;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;
	
	@Override
	public int addCart(CartDto cart) {
		System.out.println("CartServiceImpl addCart 실행");
		
		// 장바구니  데이터 체크
		int checkCartCount = cartDao.checkCart(cart);
		// 상품이 담겨있으면 		
		if(checkCartCount > 0) {
			return 2;
		} else {  // 상품이 담겨있지 않으면
			// 장바구니 등록 & 에러 시 0 반환
			try {
				return cartDao.addCart(cart);
			} catch (Exception e) {
				return 0;
			}
		}
	}

	@Override
	public List<CartDto> getCartList(String id) {
		System.out.println("CartServiceImpl getCartList 실행");
		
		List<CartDto> cart = cartDao.getCart(id);
		
		return cart;
	}

	@Override
	public int modifyCount(CartDto cart) {
		System.out.println("CartServiceImpl modifyCount 실행");
		
		return cartDao.modifyCount(cart);
	}

	@Override
	public int deleteCart(CartDto cart) {
		System.out.println("CartServiceImpl deleteCart 실행");
		
		return cartDao.deleteCart(cart);
	}

	@Override
	public int deleteAllCart(CartDto cart) {
		System.out.println("CartServiceImpl deleteAllCart 실행");
		
		return cartDao.deleteAllCart(cart);
	}

}
