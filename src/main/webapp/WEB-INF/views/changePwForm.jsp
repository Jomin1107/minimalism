<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<link rel="stylesheet" href="/css/mypageInfo.css">
</head>
<body>
<main>
	<div class="title">
		<h2>CHANGE PW</h2>
	</div>
	<form action="/changePwForm" method="post">
	  	<div class="join">
	  		<fieldset>
			  	<p class="info">비밀번호 입력</p>
					<table>
						<tr>
						  	<th scope="row">현재 비밀번호</th>
						    <td><input type="password" class="password" id="password" name="password" maxlength="15" required="required"></td>
						</tr>				
		            	<tr>
						  	<th scope="row">새로운 비밀번호</th>
						    <td><input type="password" class="password" id="password" name="password1" maxlength="15" required="required"><span class="msg" >( 영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 8~15자 )</span></td>
						</tr>
						<tr>    
						    <th scope="row">새로운 비밀번호확인</th>
						    <td><input type="password" class="passwordchk" id="password1ck" name="password1ck" required="required"></td>
						</tr>
					</table>
			    <div class="submit">
			      	<div class="submit_area">
				      <button type="submit" id="submit" class="submit_btn">수정</button>
				   	</div>
			    </div>
			</fieldset>
		 </div>
	</form>
</main>
</body>
</html>
<%@ include file="footer.jsp" %>