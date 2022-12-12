<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호찾기</title>
<link rel="stylesheet" type="text/css" href="/css/findPwForm.css">
</head>
<body>
<main>
	<div class="title">
		<h2>비밀번호찾기</h2>
	</div>
	<form action="" method="post">
		<div class="login">
			<fieldset>
				<div class="id_title">아이디</div>
				<label class="id" title="아이디">
					<input type="text" id="id" name="id">
				</label>
				<div class="name_title">이름</div>
				<label class="name" title="이름">
					<input type="text" id="username" name="username">
				</label>
				<div class="login_title">이메일</div>
				<label class="email" title="이메일">
					<input type="email" id="email" name="email" >
				</label>
				<div class="login_box">
				<button type="submit" class="btn_login">확인</button>
				</div>
			</fieldset>
		</div>
	</form>    
</main>
</body>
</html>
<%@ include file="footer.jsp" %>