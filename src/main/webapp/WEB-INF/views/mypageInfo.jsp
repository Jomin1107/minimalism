<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지수정</title>
<link rel="stylesheet" type="text/css" href="/css/mypageInfo.css">
<!-- 카카오 우편번호 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
/* 우편번호 API */
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
</script>
<script type="text/javascript">
/* 핸드폰 자동 하이픈 */
const autoHyphen2 = (target) => {
	 target.value = target.value
	   .replace(/[^0-9]/g, '')
	  .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
	}
</script>
</head>
<body>
<!-- 유효성검사 -->
<script defer="defer" src="/js/userJoinForm.js"></script>
<!------------------------------------------------------------->
<main>
	<div class="title">
		<h2>회원정보수정</h2>
	</div>
	<form name="userJoinForm" method="post">
	  	<div class="join">
	  		<fieldset>
			  	<p class="info">기본정보</p>
			  	<p class="default"><img src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="변경불가">변경불가</p>					
					<table>
						<tr class="table_top">
							<th scope="row">아이디<img src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="변경불가"></th>
		                	<td><input type="text" class="id" id="id" name="id" value="${loginUser.id}" disabled></td>
		            	</tr>		            	
						<tr>
							<th scope="row">이름</th>
						    <td><input type="text" class="username" id="username" name="username" value="${loginUser.username}"></td>
						</tr>
						<tr>
							<th scope="row">생년월일<img src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="변경불가"></th>
							<td><input type="text" class="age" id="age" name="age" value="${loginUser.age}" disabled></td>
						</tr>
						<tr>
					  		<th scope="row">주소</th>
					      	<td><input type="text" id="address1" name="address1" value="${loginUser.address1}">
							<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호" required="required"><br>
							<input type="text" id="address2" name="address2" value="${loginUser.address2}" required="required" ><span class="msg" >기본주소</span><br>
							<input type="text" id="address3" name="address3" value="${loginUser.address3}" ><span class="msg">상세주소</span><br>
				   		</tr>
				   		<tr>
				   			<th scope="row">전화번호</th>
				   			<td><input type="text" class="phone" id="phone" name="phone" oninput="autoHyphen2(this)" placeholder="010-XXXX-XXXX" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}" maxlength="13" value="${loginUser.phone}" required="required"></td>
				   		<tr>
						     <th scope="row">이메일</th>
						     <td><input type="email" class="email" id="email" name="email" placeholder="example@gmail.com" pattern="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{3,3}$" value="${loginUser.email}" required="required"></td>
						 </tr>
						 <tr>
						  	<th scope="row">비밀번호 확인</th>
						    <td><input type="password" class="password" id="password" name="password" placeholder="현재 비밀번호 입력" required="required"></td>
						</tr>
					</table>
					<input type="hidden" name="id" value="${loginUser.id}">
					<input type="hidden" name="age" value="${loginUser.age}">
	    			<input type="hidden" name="user_delete" value="0">
			    <div class="submit">
			      	<div class="submit_area">
				      <button type="submit" formaction="/mypageUpdate" id="submit" class="submit_btn">정보수정</button>
				      <a href="/changePwForm" class="changePw_btn" style="font-size: 16px; padding:10.4px;" >비밀번호변경</a>				      				
					    <div class="delete_btn_area">
					   	  <button type="submit" formaction="/mypageDelete" class="delete_btn">회원탈퇴</button>
				   		</div>
				   	</div>				   	
			    </div>
			</fieldset>
		 </div>
	</form>
</main>
</body>
</html>
<%@ include file="footer.jsp" %>