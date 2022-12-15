<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OrderList</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/orderList.css">
<script type="text/javascript">
$(document).ready(function() {
		/* 주문상태표시 */
		var orderState = $(".orderState").val();
		$(".orderState").each(function() {
			//alert($(this).text());
			if($(this).text() == 1) {
				$(this).text('주문완료');
			} 
			else if($(this).text() == 5) {
				$(this).text('취소완료');
			}
		});
		
		/* 취소/교환/반품 여부 표시 */
		var refundCheck = $(".refundCheck").val();
		$(".refundCheck").each(function() {
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
<main class="box">
<h4>MY ORDER</h4>
	<div class="content_totalCount_section">
		<table class="subject_table">
			<thead>
				<tr>
					<th class="td_width_2">주문일자<br>[주문번호]</th>
					<th class="td_width_0"></th>
					<th class="td_width_2">이미지</th>
					<th class="td_width_3">상품정보</th>
					<th class="td_width_1">상품구매수량</th>
					<th class="td_width_2">상품구매금액</th>
					<th class="td_width_2">주문처리상태</th>
					<th class="td_width_2">취소/교환/반품</th>
					<th class="td_width_1">선택</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty orderInfo}">
					<tr>	
						<td colspan="9">주문 정보가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:forEach items="${orderInfo}" var="oi" varStatus="status">
					<tr>
						<td class="td_width_2"><!-- 주문일자, [주문번호] -->
							<fmt:formatDate value="${oi.order_date}" pattern="YYYY-MM-dd"/><br>
							<a href="/orderDetail/${oi.order_number}">[${oi.order_number}]</a>
						</td>
						<td class="td_width_0"><!-- 주문정보 가져오기-->
							<input type="hidden" value="${oi.product_price}">
							<input type="hidden" id="totalCount${status.index}" value="${oi.product_count}">
							<input type="hidden" id="totalPrice${status.index}" value="${oi.product_price * oi.product_count}">
							<input type="hidden" value="${ci.product_number}">
							<input type="hidden" id="order_number${status.index}" value="${oi.order_number}">
						</td>
						<td class="td_width_2"><!-- 이미지 -->
							<a href="/productDetail/${oi.product_number}">
								<img alt="product_img" src="${oi.stored_thumbnail}" class="thumbnail">
							</a>
						</td>
						<td class="td_width_3"><!-- 상품정보 -->
							<a href="/productDetail/${oi.product_number}">${oi.product_name}</a>
							<br>${oi.product_color}<!-- 색상 -->
						</td>
						<td class="td_width_2"><!-- 수량 -->
							<div class="table_text_align_center quantity_div">
								<span id="numberUpDown${status.index}">${oi.product_count}</span>
							</div>
						</td>
						<td class="td_width_1"><!-- 상품구매금액 -->
							<fmt:formatNumber value="${oi.product_price * oi.product_count}" pattern="#,###원"/>
						</td>
						<td class="td_width_2"><!-- 주문처리상태 -->
							<input type="hidden" id="orderState${status.index}" value="${oi.order_status}">
							<span id="showState${status.index}" class="orderState">${oi.order_status}</span>
						</td>
						<td class="td_width_2"><!-- 취소/교환/반품 -->
							<span class="refundCheck">${oi.refund_check}</span>
						</td>
						<td rowspan="1" class="td_width_1"><!-- 선택 -->
							<div class="table_text_align_center select_div">
								<button class="cancle_btn" id="remove${status.index}" onclick="cancleOrder(${status.index})">취소</button>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</main>
</body>
</html>
<%@ include file="footer.jsp" %>