package com.oracle.minimalism.mjService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.minimalism.dto.CartDto;
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
	public int createProductOrder(OrderDtoVO order) {
		System.out.println("OrderServiceImpl createProductOrder 실행");
		
		int result = orderDao.createProductOrder(order);
		
		return result;
	}
	
	/* 카트에서 주문하기 */
	@Override
	public int createCartOrder1(OrderDtoVO order) {
	System.out.println("OrderServiceImpl createCartOrder1  실행");
		
		System.out.println("order => " + order);
		int order_number = orderDao.createCartOrder1(order);
		System.out.println("OrderServiceImpl createCartOrder1  order_number => " + order_number);
		
		
		return order_number;
	}

	@Override
	public int createCartOrder2(CartDto cart) {
		System.out.println("OrderServiceImpl createCartOrder2 실행");
		// cart를 가지고 order상세를 저장 하러감
		System.out.println("cart => " + cart);
		System.out.println("cart_number => " + cart.getCart_number());
		System.out.println("product_number => " + cart.getProduct_number());
		System.out.println("product_count => " + cart.getProduct_count());
		int result = orderDao.createCartOrder2(cart);
		
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


	@Override
	public List<CartDto> productCartList(List<String> cart_numberList) {
	    System.out.println("OrderServiceImpl productCartList 실행");
		List<CartDto> cartList = orderDao.cartGetList(cart_numberList);
		return cartList;
	}


}
