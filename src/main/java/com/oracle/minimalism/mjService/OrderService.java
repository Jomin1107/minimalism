package com.oracle.minimalism.mjService;

import java.util.List;

import com.oracle.minimalism.dto.OrderDto;
import com.oracle.minimalism.dto.OrderDtoVO;

public interface OrderService {
	/* 주문 페이지 이동 */
	//OrderDtoVO productDetailOrder(OrderDtoVO orderVo);
	public OrderDtoVO productDetailOrder(OrderDtoVO orderVo);
	
	/* 상품 주문하기 */
	int createOrder(OrderDtoVO order);

	/* 주문 정보 리스트 */
	public List<OrderDto> getOrderList(String id);

	/* 주문 상세 조회 */
	OrderDto orderDetail(OrderDto orderDto);

	/* 주문 취소 */
	int cancleOrder(OrderDto order);

	public List<OrderDtoVO> productDetailOrderList(OrderDtoVO orderVo);



}
