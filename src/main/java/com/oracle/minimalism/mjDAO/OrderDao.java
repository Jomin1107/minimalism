package com.oracle.minimalism.mjDAO;

import java.util.List;

import com.oracle.minimalism.dto.CartDto;
import com.oracle.minimalism.dto.OrderDto;
import com.oracle.minimalism.dto.OrderDtoVO;

public interface OrderDao {
	/* 주문 페이지 이동 */
	OrderDtoVO productDetailGet(OrderDtoVO orderVo);
	List<OrderDtoVO> productDetailGetList(OrderDtoVO orderVo);
	
	/* 상품상세페이지에서 주문하기 */
	int createProductOrder(OrderDtoVO order);

	/* 카트에서 주문하기 */
	int createCartOrder1(OrderDtoVO order);
	int createCartOrder2(CartDto cart);
	
	/* 주문 정보 리스트 */
	List<OrderDto> getOrder(String id);

	/* 주문상세조회 */
	OrderDto orderOneDetail(OrderDto orderDto);

	/* 주문 취소 */
	int cancleOrder(OrderDto order);
	
	List<CartDto> cartGetList(List<String> cart_numberList);
	
}
