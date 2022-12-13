package com.oracle.minimalism.mjDAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {

	// Mybatis DB 연동 
		@Autowired
		private SqlSession session;
}
