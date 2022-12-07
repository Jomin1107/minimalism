<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	/* footer */
	.left-footer {
		float: left;
		padding: 50px;
	}
	.left-footer p {
		font-weight: lighter;
		font-size: 12px;
		color: #5D5D5D;
		margin-bottom: 5px;
		letter-spacing : 2.5px;
		line-height:20px;
	}
	.right-footer {
		float: right;
		padding: 50px;
		margin-right: 230px;
	}
	.right-footer p {
		font-weight: lighter;
		font-size: 12px;
		color: #5D5D5D;
		margin-bottom: 5px;
		letter-spacing : 2.5px;
		line-height:20px;
	}
</style>
</head>
<body>
	<footer>
		<div class="left-footer">
			<p>MINIMALISM  |  CEO : LEE HYO JUNG  |  ADRESS : 263, Jangchungdan-ro, Jung-gu, Seoul</p>
			<p>BUSINESS LICENSE : 301-31-929496[사업자정보확인]  |  MAIL-ORDER LICENSE : 2022-SeoulJung-gu-1102</p>
			<p>TEL : 02-1234-5678  |  CHIEF PRIVACY OFFICER : JO MIN JI (abcdefg@naver.com)</p>
		</div>
		<div class="right-footer">
			<p>CS Center</p>
			<p>Tel. 02.1234.5678</p>
			<p>Contact. hijklmn@naver.com</p>
		</div>
	</footer>
	<a href="#" style="display:scroll;position:fixed;bottom:10px;right:10px;"><img class="up" src="${pageContext.request.contextPath}/img/up.png"></a>
</body>
</html>