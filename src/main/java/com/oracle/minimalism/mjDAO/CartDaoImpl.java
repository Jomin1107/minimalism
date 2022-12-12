package com.oracle.minimalism.mjDAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.minimalism.dto.CartDto;

import lombok.RequiredArgsConstructor;

@Repository
public class CartDaoImpl implements CartDao {

	// Mybatis DB 연동 
	@Autowired
	private SqlSession session;

	@Override
	public int addCart(CartDto cart) throws Exception {
		int result = 0;
		System.out.println("CartDaoImpl addCart 실행");
		System.out.println("CartDaoImpl addCart cart->"+cart);
		
		try {
			result = session.insert("addCart", cart);
		} catch (Exception e) {
			System.out.println("CartDaoImpl addCart Exception => " + e.getMessage());
		}
		return result;
	}

	@Override
	public int deleteCart(CartDto cart) {
		int deleteCart = 0;
		System.out.println("CartDaoImpl deleteCart 실행");
		System.out.println("CartDaoImpl deleteCart cart.getCart_number() => " + cart.getCart_number());
		
		try {
			deleteCart = session.delete("deleteCart", cart);
			//System.out.println("CartDaoImpl deleteCart result => " + result);
		} catch (Exception e) {
			System.out.println("CartDaoImpl deleteCart Exception => " + e.getMessage());
		}
		return deleteCart;
	}

	@Override
	public int modifyCount(CartDto cart) {
		System.out.println("CartDaoImpl modifyCount 실행");
		int modifyCount = 0;
		
		try {
			modifyCount = session.update("modifyCount", cart);
		} catch (Exception e) {
			System.out.println("CartDaoImpl modifyCount Exception => " + e.getMessage());
		}
		return modifyCount;
	}


	@Override
	public List<CartDto> getCart(String id) {
		List<CartDto> cart = null;
		System.out.println("CartDaoImpl getCart 실행");
		System.out.println("CartDaoImpl getCart id-> " + id);
		
		try {
			cart = session.selectList("getCart", id);
			System.out.println("CartDaoImpl getCart cartList.size() => " + cart.size());
		} catch (Exception e) {
			System.out.println("CartDaoImpl getCart Exception => " + e.getMessage());
		}
		return cart;
	}

	@Override
	public int checkCart(CartDto cart) {
		System.out.println("CartDaoImpl checkCart 실행");
		System.out.println("CartDaoImpl checkCart cart.getId() -> " + cart.getId());
		System.out.println("CartDaoImpl checkCart cart.getProduct_number() -> " + cart.getProduct_number());
		int cartCount = 0;
		try {
			cartCount = session.selectOne("checkCart", cart);
			System.out.println("CartDaoImpl checkCart cart -> " + cart);

		} catch (Exception e) {
			System.out.println("CartDaoImpl checkCart Exception => " + e.getMessage());
		}
		return cartCount;
	}

	@Override
	public int deleteAllCart(CartDto cart) {
		int deleteAllCart = 0;
		System.out.println("CartDaoImpl deleteAllCart 실행");
		System.out.println("CartDaoImpl deleteAllCart cart.getId() => " + cart.getId());
		
		try {
			deleteAllCart = session.delete("deleteAllCart", cart);
			//System.out.println("CartDaoImpl deleteCart result => " + result);
		} catch (Exception e) {
			System.out.println("CartDaoImpl deleteAllCart Exception => " + e.getMessage());
		}
		return deleteAllCart;
	}



}
