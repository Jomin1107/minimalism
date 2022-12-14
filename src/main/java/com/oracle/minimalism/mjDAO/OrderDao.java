package com.oracle.minimalism.mjDAO;

import com.oracle.minimalism.dto.OrderDtoVO;

public interface OrderDao {

	int createOrder(OrderDtoVO order);

}
