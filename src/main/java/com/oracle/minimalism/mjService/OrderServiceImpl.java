package com.oracle.minimalism.mjService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.minimalism.dto.OrderDto;
import com.oracle.minimalism.dto.OrderDtoVO;
import com.oracle.minimalism.mjDAO.OrderDao;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public OrderDtoVO productDetailOrder(OrderDtoVO orderVo) {
		System.out.println("OrderServiceImpl productDetailOrder 실행");
		
		OrderDtoVO productDetailOrder = orderDao.productDetailGet(orderVo);
		
		return productDetailOrder;
	}
	

	@Override
	public List<OrderDtoVO> productDetailOrderList(OrderDtoVO orderVo) {
	    System.out.println("OrderServiceImpl productDetailOrder 실행");
		List<OrderDtoVO> productDetailOrderList = orderDao.productDetailGetList(orderVo);
		return productDetailOrderList;	
		}

//	public OrderDtoVO productDetailOrder(OrderDtoVO orderVo) {
//		System.out.println("OrderServiceImpl productDetailOrder 실행");
//		
//		OrderDtoVO productDetailOrder = null;
//		
//		productDetailOrder = orderDao.productDetailGet(orderVo);
//		
//		return productDetailOrder;
//	}
	
	@Override
	public int createOrder(OrderDtoVO order) {
		System.out.println("OrderServiceImpl createOrder 실행");
		
		int result = orderDao.createOrder(order);
		
		return result;
	}

	@Override
	public List<OrderDto> getOrderList(String id) {
		System.out.println("OrderServiceImpl getOrderList 실행");
		
		List<OrderDto> order = orderDao.getOrder(id);
		
		return order;
	}

	@Override
	public OrderDto orderDetail(OrderDto orderDto) {
		System.out.println("OrderServiceImpl orderDetail 실행");
		
		OrderDto orderDetail = null;
		orderDetail = orderDao.orderOneDetail(orderDto);
		System.out.println("OrderServiceImpl orderDetail -> " + orderDetail);
		
		return orderDetail;
	}

	@Override
	public int cancleOrder(OrderDto order) {
		System.out.println("OrderServiceImpl cancleOrder 실행");
		
		return orderDao.cancleOrder(order);
	}


}
