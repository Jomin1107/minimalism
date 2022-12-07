<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link rel="stylesheet" href="/css/login.css">
</head>
<body>
<main>
	<div class="title">
		<h2>LOGIN</h2>
	</div>
	<form action="/login" method="post">
		<div class="login">
			<fieldset>
				<div class="login_title">ID</div>
				<label class="id" title="아이디">
					<input type="text" id="id" name="username" autofocus="autofocus" placeholder="아이디">
				</label>
				<div class="login_title">PASSWORD</div>
				<label class="password" title="비밀번호">
					<input type="password" id="password" name="password" placeholder="비밀번호">
				</label>
				<div class="security">
					<ul>
						<li>
							<a href="findIdForm">아이디 찾기</a>
						</li>
						<li class="mid">/</li>
						<li>
							<a href="findPwForm">비밀번호 찾기</a>
						</li>
					</ul>
				</div>
				<center>
					<a href="http://localhost:5555/oauth2/authorization/naver">
						<img src="https://jewooint.godohosting.com/ostkaka/cafe24/main/icon/naver_login.png" alt="네이버 로그인">
					</a>
					<a href="http://localhost:5555/oauth2/authorization/kakao">
						<img src="https://jewooint.godohosting.com/ostkaka/cafe24/main/icon/kakao_login.png" alt="카카오계정 로그인">
					</a>
				</center>
				<br>
				<div class="login_box">
				<button type="submit" class="btn_login">Login</button>
				</div>
				<div class="join_box">
					아직 회원이 아니신가요?
					<br>
					회원가입을 하시면 다양하고 특별한 혜택이 준비되어 있습니다.
					<br>
					<br>
					<a href="joinForm" class="btn_join">Join</a>
				</div>
			</fieldset>
		</div>
	</form>
</main>
</body>
</html>
<%@ include file="footer.jsp" %>