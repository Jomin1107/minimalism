<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/orderDetail.css">
<script type="text/javascript">
$(document).ready(function() {
	/* 주문상태표시 */
	var orderState1 = $("#orderState1").val();
	var orderState2 = $("#orderState2").val();
	$("#orderState1").each(function() {
		//alert($(this).text());
		if($(this).text() == 1) {
			$(this).text('주문완료');
		} 
		else if($(this).text() == 5) {
			$(this).text('취소완료');
		}
	});
	$("#orderState2").each(function() {
		//alert($(this).text());
		if($(this).text() == 1) {
			$(this).text('주문완료');
		} 
		else if($(this).text() == 5) {
			$(this).text('취소완료');
		}
	});
	
	
	/* 취소/교환/반품 여부 표시 */
	var refundCheck = $("#refundCheck").val();
	$("#refundCheck").each(function() {
		//alert($(this).text());
		if($(this).text() == 1) {
			$(this).text('-');
		} 
		else if($(this).text() == 5) {
			$(this).text('취소');
		}
	});

});	
</script>
</head>
<body>
	<main>
		<div class="box">
		<h4>ORDER DETAIL</h4>
			<div class="order">
				<div class="title">주문정보</div>
				<table>
					<tr class="product">
						<th scope="row" class="side">주문번호</th>
						<td class="line">${order.order_number}</td>
					<tr/>
					<tr>
				  		<th scope="row" class="side">주문일자</th>
				      	<td class="line">
				      		<fmt:formatDate value="${order.order_date}" pattern="YYYY-MM-dd HH:mm:ss"/>
				      	</td>
			   		</tr>
			   		<tr>
			   			<th scope="row" class="side">주문자</th>
			   			<td class="line">${order.username}</td>
					</tr>
					<tr>
						<th scope="row" class="side">주문처리상태</th>
			   			<td class="line" id="orderState1">${order.order_status}</td>
			   		</tr>
				</table>	
			</div>
			<div class="order">
				<div class="title">주문 상품 정보</div>
				<table>
				<thead>
						<tr>
							<th class="td_width_2">이미지</th>
							<th class="td_width_3">상품정보</th>
							<th class="td_width_1">수량</th>
							<th class="td_width_2">상품구매금액</th>
							<th class="td_width_1">배송비</th>
							<th class="td_width_1">주문처리상태</th>
							<th class="td_width_1">취소/교환/반품</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="free">
								<a href="/productDetail/${order.product_number}">
									<img alt="product_img" src="${order.stored_thumbnail}" class="thumbnail">
								</a>
							</td>
							<td class="free">
								<a href="/productDetail/${order.product_number}">${order.product_name}</a>
								<br>${order.product_color}
							</td>
							<td class="free">${order.product_count}</td>
							<td class="free">
								<fmt:formatNumber value="${order.product_price * order.product_count}" pattern="#,###원"/>
							</td>
							<td class="free">무료</td>
							<td class="free" id="orderState2">${order.order_status}</td>
							<td class="free" id="refundCheck">${order.refund_check}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="order">
			<div class="title">배송지 정보</div>
				<table>
					<tr>
						<th scope="row" class="side">받으시는분</th>
						<td class="line">${order.receiver_name}</td>
					<tr/>
					<tr>
				  		<th scope="row" class="side">우편번호</th>
				      	<td class="line">${order.receiver_address1}</td>
					</tr>
					<tr>
						<th scope="row" class="side">주소</th>
						<td class="line">${order.receiver_address2}</td>
					</tr>
					<tr>
						<th scope="row" class="side">상세주소</th>
						<td class="line">${order.receiver_address3}</td>
			   		</tr>
			   		<tr>
			   			<th scope="row" class="side">전화번호</th>
			   			<td class="line">${order.receiver_phone}</td>
					</tr>
				</table>
			</div>
		</div>
	</main>
</body>
</html>
<%@ include file="footer.jsp" %>