package com.oracle.minimalism.mjDAO;

import java.util.List;

import com.oracle.minimalism.dto.OrderDto;
import com.oracle.minimalism.dto.OrderDtoVO;

public interface OrderDao {
	
	/* 상품 주문하기 */
	int createOrder(OrderDtoVO order);

	/* 주문 정보 리스트 */
	List<OrderDto> getOrder(String id);

	/* 주문상세조회 */
	OrderDto orderOneDetail(OrderDto orderDto);

	/* 주문 취소 */
	int cancleOrder(OrderDto order);

}
