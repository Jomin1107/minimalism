package com.oracle.minimalism.mjService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.minimalism.dto.OrderDtoVO;
import com.oracle.minimalism.mjDAO.OrderDao;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public int createOrder(OrderDtoVO order) {
		int result = orderDao.createOrder(order);
		return result;
	}
}
