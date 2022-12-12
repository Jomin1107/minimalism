<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>minimalism</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/mypageForm.css">
<script type="text/javascript">


</script>
</head>
<body>
	<main class="box">
	<h4>MYPAGE</h4>
		<div class="orderstate">
			<div class="title">
				<h3>나의 주문처리 현황</h3>
			</div>
			<div class="state">
				<ul class="ul">
					<li class="list" style="border-right: 1px solid #BDBDBD">
						<strong>결제완료</strong>
						<a href="">
							<span class="count" id="">0</span>
						</a>
					</li>
					<li class="list" style="border-right: 1px solid #BDBDBD">
						<strong>배송준비중</strong>
						<a href="">
							<span class="count" id="">0</span>
						</a>
					</li>
					<li class="list" style="border-right: 1px solid #BDBDBD">
						<strong>배송중</strong>
						<a href="">
							<span class="count" id="">0</span>
						</a>
					</li>
					<li class="list" style="border-right: 1px solid #BDBDBD">
						<strong>배송완료</strong>
						<a href="">
							<span class="count" id="">0</span>
						</a>
					</li>
					<li class="list">
						<strong>환불</strong>
						<a href="">
							<span class="count" id="">0</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="contents">
			<ul class="con">
				<li class="content">
					<img src="img/userOrder.PNG" class="img">
					<a href="">
						<strong class="content">Order</strong>
						<span class="item">주문내역 조회</span>
					</a>
					<p>
						고객님께서 주문하신 상품의<br>
						주문내역을 확인하실 수 있습니다.
					</p>
				</li>
				<li class="content">
					<img src="img/userInfo.PNG" class="img">
					<a href="/mypageInfo">
						<strong class="content">Profile</strong>
						<span class="item">회원 정보</span>
					</a>
					<p>
						회원이신 고객님의 개인정보를<br>
						관리하는 공간입니다.
					</p>
				</li>
				<li class="content">
					<img src="img/userWrite.PNG" class="img">
					<a href="">
						<strong class="content">Board</strong>
						<span class="item">게시물 관리</span>
					</a>
					<p>
						고객님께서 작성하신 게시물을<br>
						관리하는 공간입니다.
					</p>
				</li>
			</ul>
		</div>
	</main>
</body>
</html>
<%@ include file="footer.jsp" %>