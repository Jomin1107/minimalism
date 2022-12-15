package com.oracle.minimalism.mjDAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.minimalism.dto.OrderDto;
import com.oracle.minimalism.dto.OrderDtoVO;

@Repository
public class OrderDaoImpl implements OrderDao {

		// Mybatis DB 연동 
		@Autowired
		private SqlSession session;

		@Override
		public int createOrder(OrderDtoVO order) {
			System.out.println("OrderDaoImpl createOrder 실행");
			int result = 0;
			
			try {
				result = session.insert("createOrder", order);
				session.insert("createDetailOrder", order);
			} catch (Exception e) {
				System.out.println("OrderDaoImpl createOrder Exception => " + e.getMessage());
			}
			return result;
		}

		@Override
		public List<OrderDto> getOrder(String id) {
			System.out.println("OrderDaoImpl getOrder 실행");
			System.out.println("OrderDaoImpl getOrder id -> " + id);
			List<OrderDto> order = null;
			
			try {
				order = session.selectList("getOrder", id);
				System.out.println("OrderDaoImpl getOrder order.size() => " + order.size());
			} catch (Exception e) {
				System.out.println("OrderDaoImpl getOrder Exception => " + e.getMessage());
			}
			return order;
		}

		@Override
		public OrderDto orderOneDetail(OrderDto orderDto) {
			System.out.println("OrderDaoImpl orderOneDetail 실행");
			OrderDto orderDetail = null;
			
			try {
				orderDetail = session.selectOne("orderOneDetail", orderDto);
				System.out.println("OrderDaoImpl orderOneDetail order_number -> " + orderDetail.getOrder_number());
			} catch (Exception e) {
				System.out.println("OrderDaoImpl orderOneDetail Exception => " + e.getMessage());
			}
			return orderDetail;
		}
}
