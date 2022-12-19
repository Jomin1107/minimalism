<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FIND ID</title>
<link rel="stylesheet" type="text/css" href="/css/foundIdForm.css">
</head>
<body>
<main>
	<div class="title">
		<h2>아이디찾기</h2>
	</div>
	<div class="findId">
		<fieldset>
			<div class="foundId"></div>
			<p class="info">고객님 아이디 찾기가 완료 되었습니다.</p>
			<div class="message_box">
			<p class="message">저희 쇼핑몰을 이용해주셔서 감사합니다.<br>다음정보로 가입된 아이디가 총 <span class="txtEm">1</span>개 있습니다.</p>
			</div>
			<div class="information">
                <p class="thumbnail"><img src="//img.echosting.cafe24.com/skin/base/member/img_member_default.gif" alt=""></p>
                <div class="description">
                    <ul class="ec-base-desc gSmall">
						<li>
						<strong class="term">이름</strong><strong class="desc"> : <span>${loginUser.username}</span></strong>
						</li>
                        <li>
						<strong class="term">이메일</strong><span class="desc" style="font-size: 13px;"> : <span>${loginUser.email}</span></span>
						</li>
                        <li><label><input type="radio" name="fid" onclick="set_findpwdid( '' , 'indi' );" checked="checked"> 
                        <span class="id">${loginUser.id}</span> 
                        <span class="gaip">(개인회원)</span></label><br></li>
                        <li>고객님 즐거운 쇼핑 하세요!</li>
                    </ul>
				</div>
           	</div>
			<br>
			<div class="login_box">
			<a href="/loginForm" class="btn_login">로그인</a>
			</div>
			<div class="password_box">
				<a href="/findPwForm" class="btn_password">비밀번호찾기</a>
			</div>
		</fieldset>
	</div>
</main>
</body>
</html>
<%@ include file="footer.jsp" %>