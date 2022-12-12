<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>viewBucket</title>
<link rel="stylesheet" type="text/css" href="/css/viewAll.css">
</head>
<body>
	<main>
		<div class="area">
			<div class="select_area">
			<select class="select_box">
				<option disabled="disabled">-정렬방식-</option>	
				<option value="">신상품</option>
				<option value="">낮은가격</option>
				<option value="">높은가격</option>
			</select>
			</div>
			<div class="cards_area">
				<c:forEach var="product" items="${listviewBucket}">
					<div class="card_area">
						<a href="">
						<div class="img_area">
							<img src="${product.stored_thumbnail}" style="width: 340px; height: 450px;">
						</div>
						<div class="card_body">
							<h5 class="card_title">${product.product_name}</h5>
							<p class="card_price"><fmt:formatNumber type="number" maxFractionDigits="3" value="${product.product_price}" />원</p>
							<span class="color">${product.product_color}</span>				
						</div>
						</a>
					</div>
				</c:forEach>			
			</div>
			<!-- 페이징 -->
			<div class="pageForm">
				<div class="pages">
					<c:if test="${page.startPage > page.pageBlock}">
						<a href="viewBucket?currentPage=${page.startPage - page.pageBlock}">[이전]</a>
					</c:if>
					<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
						<a href="viewBucket?currentPage=${i}">[${i}]</a>
					</c:forEach>
					<c:if test="${page.endPage < page.totalPage}">
						<a href="viewBucket?currentPage=${page.startPage + page.pageBlock}">[다음]</a>
					</c:if>
				</div>
			</div>
		</div>
	</main>
</body>
</html>
<%@ include file="footer.jsp" %>