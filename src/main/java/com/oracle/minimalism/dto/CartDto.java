package com.oracle.minimalism.dto;

import lombok.Data;

@Data
public class CartDto {
	private int    cart_number;    // 장바구니 번호
	private String id;			   // 회원아이디
	private int    product_number; // 상품번호
	private int    product_count;  // 상품수량
	
	// 제품 (제품테이블과 조인을 하여 변수값을 장바구니 페이지에 뿌려주기 위해 추가)
	private String product_name;   // 상품이름
	private int    product_price;  // 상품가격 
	private String product_color;  // 상품색상
	
	private int totalPrice;
	
	private String stored_thumbnail; // 상품이미지
	private int order_number;        // 임시 -> 주문상세를 하기위해서
}
