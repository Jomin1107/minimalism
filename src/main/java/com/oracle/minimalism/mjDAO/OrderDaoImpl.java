package com.oracle.minimalism.mjDAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.minimalism.dto.OrderDtoVO;

@Repository
public class OrderDaoImpl implements OrderDao {

		// Mybatis DB 연동 
		@Autowired
		private SqlSession session;

		@Override
		public int createOrder(OrderDtoVO order) {
			int result = 0;
			
			try {
				result = session.insert("createOrder", order);
				session.insert("createDetailOrder", order);
			} catch (Exception e) {
				System.out.println("OrderDaoImpl createOrder Exception => " + e.getMessage());
			}
			return result;
		}
}
