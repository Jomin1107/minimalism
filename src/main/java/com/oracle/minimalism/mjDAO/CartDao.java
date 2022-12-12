package com.oracle.minimalism.mjDAO;

import java.util.List;

import com.oracle.minimalism.dto.CartDto;

public interface CartDao {
	
	/* 카트 추가 */
	int addCart(CartDto cart) throws Exception;
	
	/* 카트 삭제 */
	int deleteCart(CartDto cart);
	
	/* 카트 수량 수정 */
	int modifyCount(CartDto cart);
	
	/* 카트 목록 */
	List<CartDto> getCart(String id);	
	
	/* 카트 확인 */
	int checkCart(CartDto cart);

	/* 카트 전체 삭제 */
	int deleteAllCart(CartDto cart);
}
