<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/cart.css">
<script type="text/javascript">
	$(document).ready(function() {
		
		setTotalInfo();
		/* 체크여부에따른 종합 정보 변화 */
		$(".individual_cart_checkbox").on("change", function(){
			setTotalInfo();
		});
		
		/* 체크박스 전체,체크 선택 */
		$("#cbx_chkAll").click(function() {
			if($("#cbx_chkAll").is(":checked")) {
				$("input[name=chk]").prop("checked", true);
				setTotalInfo();
			}
			else { 
				$("input[name=chk]").prop("checked", false);
				setTotalInfo()
			}
		});
		$("input[name=chk]").click(function() {
			var total = $("input[name=chk]").length;
			var checked = $("input[name=chk]:checked").length;
			
			if(total != checked) $("#cbx_chkAll").prop("checked", false);
			else $("#cbx_chkAll").prop("checked", true); 
		});
	});
	
	function setTotalInfo() {
		/* 종합 정보 섹션 정보 삽입 */
		let totalPrice = 0;
		let totalCount = 0;
		let totalKind  = 0;
		$("input[name=chk]:checked").each(function() {
			// 총 가격
			totalPrice += parseInt($("#totalPrice"+$(this).val()).val());
			// 총 갯수
			totalCount += parseInt($("#totalCount"+$(this).val()).val());
			// 총 종류
			totalKind  += 1;
		});
		/* 값 삽입 */
		// 총 가격
		$(".totalPrice_span").text(totalPrice.toLocaleString('en-US'));
		// 총 갯수
		$(".totalCount_span").text(totalCount);
		// 총 종류
		$(".totalKind_span").text(totalKind);
	};
	
	/* 수량버튼 */
	function decrease(index) {
		$("#numberUpDown"+index).html(Number($("#numberUpDown"+index).html())-1);
		if($("#numberUpDown"+index).html() == 0) {
			alert("수량은 1개 이상 가능합니다.");
			$("#numberUpDown"+index).html(1);
		}
	};
	function increase(index) {
		$("#numberUpDown"+index).html(Number($("#numberUpDown"+index).html())+1);
		if($("#numberUpDown"+index).html() > 10) {
			alert("최대 구매 가능 수량은 10개 입니다.");
			$("#numberUpDown"+index).html(10);
		}
	};

	/* 수량 변경 버튼 */
	function countChange(index) {
		var product_count = $("#numberUpDown"+index).html();
		var cart_number   = $("#cart_number"+index).val();
		//alert('product_count -> '+ product_count);
		//alert('cart_number -> '+ cart_number);
		//  cart_number 카트번호 -> product_count 제품 수량 수정 
		 $.ajax({
	            url      : "/cart/update",
	            type     : 'POST',  // GET, PUT
	            data     : {cart_number : cart_number , product_count : product_count},
	            dataType : 'text',
	            success  : function(data){
	            	//alert(".ajax update data -> " + data); 
	                alert('수량이 변경되었습니다.');
	                document.location.reload();
                }
	     });
	};
	
	/* 장바구니 삭제 버튼 */
	function deleteCart(index) {
		var cart_number = $("#cart_number"+index).val();
		// alert('cart_number -> '+ cart_number);
		//  cart_number 카트번호 -> 해당 카트 삭제
		 $.ajax({
	            url      : "/cart/delete",
	            type     : 'POST',  // GET, PUT
	            data     : {cart_number : cart_number},
	            dataType : 'text',
	            success  : function(data){
	            	//alert(".ajax update data -> " + data); 
	                alert('상품이 삭제되었습니다.');
	                document.location.reload();
                }
	     });
	};
	
	/* 장바구니 비우기 버튼 */
	function deleteAllCart() {
		var id = $("#user_id").val();
		// alert('id -> '+ id);
		//  id 유저아이디 -> 해당 카트 전체삭제
		 $.ajax({
	            url      : "/cart/deleteAll",
	            type     : 'POST',  // GET, PUT
	            data     : {id : id},
	            dataType : 'text',
	            success  : function(data){
	            	// alert(".ajax update data -> " + data); 
	                alert('장바구니를 비웠습니다.');
	                document.location.reload();
                }
	     });
	};
	
	
	function chkChange(selChk) {
		//alert('selChk->'+selChk);
		if ($("#chk"+selChk).prop("checked")) {
			$("#chkSel"+selChk).val('1');
		} else {
			$("#chkSel"+selChk).val('0');
		}
		
	}
</script>
</head>
<body>	
  <main class="box">
	<h4>CART</h4>
		<!-- 장바구니 리스트 -->
		<div class="content_middle_section"></div>
		<!-- 장바구니 가격 합계 -->
		<!-- cartInfo -->
	<form action="/order/page2">
		<div class="content_totalCount_section">
			<table class="subject_table">
				<!-- <caption>표 제목 부분</caption> -->
					<thead>
						<tr>
							<th class="td_width_1">
								<input type="checkbox" id="cbx_chkAll">
							</th>
							<th class="td_width_0"></th>
							<th class="td_width_2">이미지</th>
							<th class="td_width_3">상품정보</th>
							<th class="td_width_1">색상</th>
							<th class="td_width_2">상품가격</th>
							<th class="td_width_1">수량</th>
							<th class="td_width_2">상품구매금액</th>
							<th class="td_width_1">배송비</th>
							<th class="td_width_1">선택</th>
						</tr>
					</thead>
				<!-- <caption>표 내용 부분</caption> -->
					<tbody>
						<c:if test="${empty cartInfo}">
							<tr>	
								<td colspan="10">장바구니가 비어 있습니다.</td>
							</tr>
						</c:if>
						<c:forEach items="${cartInfo}" var="ci" varStatus="status">
							<tr>
								<td class="td_width_1 cart_info_td">
									<input type="checkbox" name="chk" id="chk${status.index}"
									       onclick="chkChange(${status.index})"
									       value="${status.index}" class="individual_cart_checkbox">
								</td>
								<td class="td_width_0">
									<input type="hidden" name="chkSel" id="chkSel${status.index}" value="0">
									<input type="hidden" value="${ci.product_price}">
									<input type="hidden" id="user_id" value="${ci.id}">
									<input type="hidden" id="totalCount${status.index}" value="${ci.product_count}">
									<input type="hidden" id="totalPrice${status.index}" value="${ci.product_price * ci.product_count}">
									<input type="hidden" value="${ci.product_number}">
									<input type="hidden" name="cart_number"   id="cart_number${status.index}" value="${ci.cart_number}">
								</td>
								<td class="td_width_2">
									<a href="/productDetail/${ci.product_number}">
										<img alt="product_img" src="${ci.stored_thumbnail}" class="thumbnail">
									</a>
								</td>
								<td class="td_width_3">
									<a href="/productDetail/${ci.product_number}">${ci.product_name}</a>
								</td>
								<td class="td_width_1">${ci.product_color}</td>  
								<td class="td_width_2">
									<fmt:formatNumber value="${ci.product_price}" pattern="#,###원"/>
								</td>
								<td class="td_width_1 table_text_align_center">
									<div class="table_text_align_center quantity_div">
										<a href="javascript:void(0);" class="+-" id="decreaseQuantity${status.index}" onclick="decrease(${status.index})">-</a>
										<span id="numberUpDown${status.index}">${ci.product_count}</span>
										<a href="javascript:void(0);" class="+-" id="increaseQuantity${status.index}" onclick="increase(${status.index})">+</a>
									</div>
									<a href="javascript:void(0);" class="quantity_modify_btn" id="modifyQuantity${status.index}" onclick="countChange(${status.index})">변경</a>
								</td>
								<td class="td_width_2 table_text_align_center">
									<fmt:formatNumber value="${ci.product_price * ci.product_count}" pattern="#,###원"/>
								</td>
								<td class="td_width_1">무료</td>
								<td class="td_width_1 table_text_align_center">
									<div class="table_text_align_center select_div">
										<button class="delete_btn" id="remove${status.index}" onclick="deleteCart(${status.index})">삭제</button>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
			</table>
			<table class="list_table">
			</table>
		</div>
		<!-- 가격 종합 -->
		<c:if test="${empty cartInfo}">
			<div class="content_total_section" style="background-color: white;">			
				<div class="content_btn_section"> 
					<a href="/viewAll" class="shopping">쇼핑하러가기</a>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty cartInfo}">
			<div class="deleteAll">
				<button class="deleteAll_btn" id="removeAll" onclick="deleteAllCart()">장바구니 비우기</button>
			</div>
			<div class="content_total_section">
				<div class="total_wrap">
					<table>
						<tr>
							<td>총 상품 가격</td>
							<td><span class="totalPrice_span">0</span> 원</td>
						</tr>
						<tr>
							<td>배송비</td>
							<td><span class="delivery_price">0</span> 원(무료)</td>
						</tr>									
						<tr>
							<td>총 주문 상품수</td>
							<td><span class="totalKind_span"></span> 종류  <span class="totalCount_span"></span> 개</td>
						</tr>
					</table>
				<div class="boundary_div">구분선</div>
					<table>
						<tbody>
							<tr>
								<td><strong>총 결제 예상 금액</strong></td>
								<!-- <td><span class="totalPrice_span">0</span> 원</td> -->
								<td><strong class="totalPrice_span">0</strong><strong> 원</strong></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- 구매 버튼 영역 -->
			<div class="content_btn_section">
				<button type="submit" class="select">선택상품주문하기</button><p>
			</div>
	       </c:if>
	  </form>
	</main>
</body>
</html>
<%@ include file="footer.jsp" %>