package com.oracle.minimalism.dto;

import lombok.Getter;

@Getter
public enum OrderStatus {
	ORDER(1),     // 주문완료
	READY(2),     // 배송준비
	SHIPPING(3),  // 배송중   ,   OrderStatus.SHIPPING으로 사용하면 DB에 3으로 update 또는 insert 됨
	COMPLETE(4),  // 배송완료
	CANCLE(5);    // 주문취소
	
	private final int state;  //  parameter로 받을 state를 선언
	
	private OrderStatus(int state) {  // 생성자로 받아 필드와 매핑
		this.state = state;
	}
}
