<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="/css/join.css">
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
</script>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!------------------------------------------------------------->
<main>
	<div class="title">
		<h2>추가 정보 입력</h2>
	</div>
	<form action="" method="post">
	  	<div class="join">
	  		<fieldset>
			  	<p class="info">기본정보</p>
			  	<p class="default"><img src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="변경불가">추후변경불가</p>
					<table>
						<tr class="table_top">
							<th scope="row">아이디<img src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="변경불가"></th>
		                	<td><input type="text" class="id" id="id" name="id" maxlength="15" required="required" value="${user.id}" disabled><span class="msg">( 영문소문자/숫자, 4~15자 )</span></td>
		            	</tr>
						<tr>
							<th scope="row">이름</th>
						    <td><input type="text" class="username" id="username" required="required" value="${user.username}" disabled></td>
						</tr>
						<tr>
							<th scope="row">생년월일<img src="//img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif" alt="변경불가"></th>
							<td><input type="text" class="age" id="age" name="age" pattern="[0-9]{6}" maxlength="6" required="required" ></td>
						</tr>
						<tr>
					  		<th scope="row">주소</th>
					      	<td><input type="text" id="address1" name="address1">
							<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호" required="required"><br>
							<input type="text" id="address2" name="address2" required="required" ><span class="msg" >기본주소</span><br>
							<input type="text" id="address3" name="address3" ><span class="msg">상세주소</span><br>
				   		</tr>
				   		<tr>
				   			<th scope="row">전화번호</th>
				   			<td><input type="text" class="phone" id="phone" name="phone" placeholder="010-XXXX-XXXX" maxlength="13" required="required"></td>
				   		<tr>
						     <th scope="row">이메일</th>
						     <td><input type="email" class="email" id="email"  value="${user.email}" disabled pattern="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{3,3}$" required="required"></td>
						 </tr>
					</table>
				<div class="TOS_area">
			   	<p>약관동의</p>
			     <div class="TOS_box">
			       <div class="accordion" id="accordionExample">
					  <div class="card">
					    <div class="card-header" id="agree">
					        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
					          이용약관 보기
					        </button>
					    </div>
					    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
					      <div class="card-body">
					      	제 1 장 총 칙<p><br>
					      	
							제 1 조 (목적)<br>
							이 약관은 {COMPANY_NAME}(이하 "사이트"라 합니다)에서 제공하는 인터넷서비스(이하 "서비스"라 합니다)의 이용 조건 및 절차에 관한 기본적인 사항을 규정함을 목적으로 합니다.<p><br>
							
							제 2 조 (약관의 효력 및 변경)<br>
							① 이 약관은 서비스 화면이나 기타의 방법으로 이용고객에게 공지함으로써 효력을 발생합니다.<br>
							② 사이트는 이 약관의 내용을 변경할 수 있으며, 변경된 약관은 제1항과 같은 방법으로 공지 또는 통지함으로써 효력을 발생합니다.<p><br>
							
							제 3 조 (용어의 정의)<br>
							이 약관에서 사용하는 용어의 정의는 다음과 같습니다.<br>
							① 회원 : 사이트와 서비스 이용계약을 체결하거나 이용자 아이디(ID)를 부여받은 개인 또는 단체를 말합니다.<br>
							② 신청자 : 회원가입을 신청하는 개인 또는 단체를 말합니다.<br>
							③ 아이디(ID) : 회원의 식별과 서비스 이용을 위하여 회원이 정하고 사이트가 승인하는 문자와 숫자의 조합을 말합니다.<br>
							④ 비밀번호 : 회원이 부여 받은 아이디(ID)와 일치된 회원임을 확인하고, 회원 자신의 비밀을 보호하기 위하여 회원이 정한 문자와 숫자의 조합을 말합니다.<br>
							⑤ 해지 : 사이트 또는 회원이 서비스 이용계약을 취소하는 것을 말합니다.<p><br>
							
							제 2 장 서비스 이용계약<p><br>
							
							제 4 조 (이용계약의 성립)<br>
							① 이용약관 하단의 동의 버튼을 누르면 이 약관에 동의하는 것으로 간주됩니다.<br>
							② 이용계약은 서비스 이용희망자의 이용약관 동의 후 이용 신청에 대하여 사이트가 승낙함으로써 성립합니다.<br>
					      </div>
					    </div>
					  </div>
			        </div>
			      </div>
			    </div>   
		        <div class="form-check">
		          	<input class="form-check-input is-invalid" type="checkbox" value="" id="invalidCheck3" required>
	    			<label class="form-check-label" for="invalidCheck3">이용약관 동의</label>
	        		<div class="invalid-feedback">
	     		 	   	*제출하기 전에 동의해야 합니다.
	   				</div>
	    		</div>
	    		<input type="hidden" name="id" value="${user.id}">
	    		<input type="hidden" name="user_delete" value="0">
			    <div class="submit">
			      <button type="submit" id="submit">회원가입</button>
			    </div>
			</fieldset>
		 </div>
	</form>
</main>
<!------------------------------------------------------------->
</body>
</html>
<%@ include file="footer.jsp" %>