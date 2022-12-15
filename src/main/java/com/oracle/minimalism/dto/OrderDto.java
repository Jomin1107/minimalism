package com.oracle.minimalism.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OrderDto {
	private int    order_number;        // 주문번호
	private String id;                  // 아이디
	private Date   order_date;          // 주문날짜
	private String receiver_address1;	// 수령자 우편번호
	private String receiver_address2;	// 수령자 주소
	private String receiver_address3;	// 수령자 상세주소
	private String receiver_name;       // 수령자 이름
	private String receiver_phone;      // 수령자 전화번호
	private String order_status;        // 처리상태 
	
	private int    product_number;      // 상품번호
	private int    product_count;       // 상품수량
	private int    product_price;       // 상품가격 
	private String product_name;        // 상품이름
	private String product_color;       // 상품색상
	
	private int    refund_check;        // 취소여부
	
	private String stored_thumbnail;    // 상품이미지
	
	private String username;
}

