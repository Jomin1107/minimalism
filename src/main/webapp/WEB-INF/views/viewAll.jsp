<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>viewAll</title>
<link rel="stylesheet" type="text/css" href="/css/viewAll.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>
<script type="text/javascript">
 function optionChange(){
	 var value = $('#viewSelect').val();
	 console.log(value);
	 location.href = "/viewAll?currentPage="+ ${page.currentPage} + "&value=" + value + "&category=" + ${category};
 }
 var i = "${page.currentPage}";
 console.log(i);
</script>
<body>
	<main>
		<div class="area">
			<div class="select_area">
			<select class="select_box" id="viewSelect" onchange="optionChange();">
				<option disabled="disabled">-정렬방식-</option>	
			<c:if test="${value == 1}">
				<option value="1" selected="selected">신상품</option>
				<option value="2">낮은가격</option>
				<option value="3">높은가격</option>
			</c:if>
			<c:if test="${value == 2}">
				<option value="1">신상품</option>
				<option value="2" selected="selected">낮은가격</option>
				<option value="3">높은가격</option>
			</c:if>
			<c:if test="${value == 3}">
				<option value="1">신상품</option>
				<option value="2">낮은가격</option>
				<option value="3" selected="selected">높은가격</option>
			</c:if>
			</select>
			</div>
			<div class="cards_area">
				<c:forEach var="product" items="${listviewAll}">
					<div class="card_area">
						<a href="/productDetail/${product.product_number}">
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
						<a href="viewAll?currentPage=${page.startPage - page.pageBlock}&value=${value}&category=${category}">[이전]</a>
					</c:if>
					<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
						<a href="viewAll?currentPage=${i}&value=${value}&category=${category}">[${i}]</a>
					</c:forEach>
					<c:if test="${page.endPage < page.totalPage}">
						<a href="viewAll?currentPage=${page.startPage + page.pageBlock}&value=${value}&category=${category}">[다음]</a>
					</c:if>
				</div>
			</div>
		</div>
	</main>
</body>
</html>
<%@ include file="footer.jsp" %>