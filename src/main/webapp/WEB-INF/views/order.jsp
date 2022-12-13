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
<link rel="stylesheet" type="text/css" href="/css/order.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script> // 우편번호 API
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("address3").value = extraAddr;
            
            } else {
                document.getElementById("address3").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("address1").value = data.zonecode;
            document.getElementById("address2").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("address3").focus();
        }
    }).open();    
}

//	$(document).ready(function() {
//		setTotalInfo();
//	}
	
//	function setTotalInfo() {
		/* 종합 정보 섹션 정보 삽입 */
//		let totalPrice = 0;
//		let totalCount = 0;
//		let totalKind  = 0;
//		$("input[name=chk]:checked").each(function() {
			// 총 가격
//			totalPrice += parseInt($("#totalPrice"+$(this).val()).val());
			// 총 갯수
//			totalCount += parseInt($("#totalCount"+$(this).val()).val());
			// 총 종류
//			totalKind += 1;
//		});
		/* 값 삽입 */
		// 총 가격
//		$(".totalPrice_span").text(totalPrice.toLocaleString('en-US'));
		// 총 갯수
//		$(".totalCount_span").text(totalCount);
		// 총 종류
//		$(".totalKind_span").text(totalKind);
//	};
	
</script>
</head>
<body>
	<main>
		<div class="box">
		<h4>ORDER</h4>
			<div class="delivery">
				<div class="black">주문/결제</div>
				<div class="title">배송지</div>
					<table>
						<tr>
							<th scope="row">받는사람</th>
							<td><input type="text" class="username" id="username" name="username" value="${loginUser.username}" required="required"></td>
						<tr/>
						<tr>
					  		<th scope="row">주소</th>
					      	<td><input type="text" id="address1" name="address1" value="${loginUser.address1}">
							<input type="button" id="Abutton" onclick="sample6_execDaumPostcode()" value="우편번호" required="required"><br>
							<input type="text" id="address2" name="address2" value="${loginUser.address2}" required="required" ><span class="msg" >기본주소</span><br>
							<input type="text" id="address3" name="address3" value="${loginUser.address3}" ><span class="msg">상세주소</span><br>
				   		</tr>
				   		<tr>
				   			<th scope="row">전화번호</th>
				   			<td><input type="text" class="phone" id="phone" name="phone" oninput="autoHyphen2(this)" placeholder="010-XXXX-XXXX" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}" maxlength="13" value="${loginUser.phone}" required="required"></td>
					</table>	
			</div>
			<div class="order">
				<div class="title">주문상품</div>
					<table>
						<%-- <c:forEach items="${product}"> --%>
							<tr class="product">
								<td>
									<a href="/productDetail/${product.product_number}" class="thumbnail">
										<img alt="product_img" src="${product.stored_thumbnail}" class="thumbnail">
									</a>
								</td>
								<td>
									<a href="/productDetail/${product.product_number}">${product.product_name}</a><br>
									${product.product_color}
								</td>
								<td>
									<fmt:formatNumber value="${product.product_price}" pattern="#,###원"/>
								</td>
								<td>주문수량 : ${order.product_count} 개</td>
							</tr>
						<%-- </c:forEach> --%>
					</table>
			</div>
			<div class="pay">
				<div class="title">결제정보</div>
					<div>
						<p class="left">배송비</p>
						<p class="right">+0원</p> 
					</div>
					<div class="gray">
						<p class="left">결제금액</p>
						<p class="right">
							<fmt:formatNumber value="${product.product_price * order.product_count}" pattern="#,###원"/>
						</p> 
					</div>
				<div class="black">
					<a class="pay" href="">
						<fmt:formatNumber value="${product.product_price * order.product_count}" pattern="#,###원"/> 결제하기
					</a>
				</div>
			</div>
		</div>
	</main>
</body>
</html>
<%@ include file="footer.jsp" %>