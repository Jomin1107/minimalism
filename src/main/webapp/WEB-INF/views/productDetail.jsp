<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Detail</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/productDetail.css">
<script type="text/javascript">
	function changeAmount(){
	    var selectAmount = document.getElementById("selectAmount");
	    // select element에서 선택된 option의 value가 저장된다.
	    var selectValue = selectAmount.options[selectAmount.selectedIndex].value;
	    //alert('selectValue->'+selectValue);
	    var selectPrice = $("#productPrice").val();
	    //alert('selectPrice->'+selectPrice);
	    var showPrice = Number(selectValue) * selectPrice;
	    
	    //alert('showPrice->'+showPrice);
	    // input태그
	    //  $('#total_amount').val(showPrice);
	    // div태그
	    $('#totalPrice').html(showPrice.toLocaleString('en-US'));
	    $('#totalCount').html(selectValue);
	}
	
	// 서버로 전송할 데이터
	const form = {
		id : '${loginUser.id}',
		product_number : '${product.product_number}',
		product_count : ''
	}
	
	// 장바구니 추가 버튼
	$(function() {
		$('#btn_cart').click(function() {
		    // select element에서 선택된 option의 value가 저장된다.
		    var selectAmount = document.getElementById("selectAmount");
			var selectValue = selectAmount.options[selectAmount.selectedIndex].value;
			
			if(selectValue == '선택') {
				alert('수량을 선택해주세요.');
				return false;
			} else {
				form.product_count = selectValue;
				//alert("cartAlert form.product_count -> " + form.product_count);
				$.ajax({
					url: '/cart/add',
					type: 'POST',
					data: form ,
					success: function(result){
						//alert("cartAlert success result -> " + result);
						cartAlert(result);
					}
				});	
			}
		});
	});
	
	function cartAlert(result){
		if(result == '0'){
			alert("장바구니 추가에 실패하였습니다.");
		} else if(result == '1'){
			alert("장바구니에 추가되었습니다.");
		} else if(result == '2'){
			alert("장바구니에 이미 추가되어 있는 상품입니다.");
			location.href = '/cart/${loginUser.id}';
		} else if(result == '5'){
			alert("로그인이 필요한 서비스입니다.");	
			location.href = '/loginForm';
		}
	}
	
</script>
</head>
<body>
	<div id="bigForm">
		<div class="flex-container p">
			<p>Product Detail</p>		
		</div>
		<form action="/order/page" name="frm">
			<div class="flex-container detailForm">
				<div class="flex-container imgtablebox">
					<table class="imgtable">
						<tr>
							<td style="width: 500">
								<img src="${product.stored_thumbnail}">
							</td>
						</tr>				
					</table>
				</div>
				<input type="hidden" id="selectProduct_number" name="product_number" value="${product.product_number}">
				<div class="flex-container detailtablebox">
					<table class="detailtable">
						<tr>
							<th>상품번호</th>
							<td>${product.product_number}</td>
						</tr>
						<tr>
							<th>상품명</th>
							<td>${product.product_name}</td>
						</tr>
						<tr>
							<th>가격</th>
							<td>
								<fmt:formatNumber value="${product.product_price}" pattern="#,###원"></fmt:formatNumber>
							</td>
						</tr>
						<tr>
							<td colspan="2"><hr class="line"></td>
						</tr>
						<tr>
							<th>색상</th>
							<td>${product.product_color}</td>						
						</tr>
						<tr>
							<th>수량</th>
							<td>
								<select id="selectAmount" class="quantity" name="product_count" required="required" onchange="changeAmount()">
									<option selected="selected">선택</option>
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
									<option>6</option>
									<option>7</option>
									<option>8</option>
									<option>9</option>
									<option>10</option>
								</select> 
							</td>
						</tr>
						<tr>
							<td colspan="2"><hr class="line"></td>
						</tr>
						<!-- 수량선택시 구매금액 출현 -->
						<tr>
							<th>총구매금액(수량)</th>		
							<td>
								<input type="hidden" id="productPrice" value="${product.product_price}">
								<strong id="totalPrice"></strong>원 (<span id="totalCount"></span>개)
							</td>			
						</tr>
						<tr>
							<td>
								<input class="submit_box" type="submit" value="BUY">
							</td>
							<td>
								<input class="submit_box" id="btn_cart" type="button" value="CART">
							</td>
						</tr>
					</table>
				</div>				
			</div>
		</form>
		<div class="flex-container productdescription">
			<table class="descriptable">
				<colgroup>
					<col width="200">
					<col width="200">
					<col width="200">
					<col width="200">
				</colgroup>
				<tr>
					<td>DESCRIPTION</td>
					<td>SHOPPING &amp; RETURN</td>
					<td>REVIEW</td>
					<td>Q&amp;A</td>
				</tr>
				<tr>
					<td>상품설명</td>
					<td>교환반품</td>
					<td>리뷰</td>
					<td>문의</td>
				</tr>
			</table>		
		</div>
		<div class="flex-container listreturn">
			<input class="return_box" type="button" value="목록" onclick="location.href='/viewAll?currentPage=1&value=1&category=0'">
		</div>
	</div>
</body>
</html>
<%@ include file="footer.jsp" %>