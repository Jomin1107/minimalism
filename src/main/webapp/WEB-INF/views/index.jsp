<%@page import="com.oracle.minimalism.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<title>minimalism</title>
<!-- 부트스트랩 css-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/bootstrap.min.js">
<link rel="stylesheet" type="text/css" href="/css/index.css">
</head>
<body>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="resources/js/bootstrap.min.js"></script>
	<header>
		<nav class="navbar fixed-top navbar-light bg-white">
			<div class="col-md-3">
				<!-- 햄버거 메뉴 -->
				<input type="checkbox" id="trigger">
				<label for="trigger">
					<!-- 햄버거 세줄 표현 -->
		            <span></span>
		            <span></span>
		            <span></span>
				</label>
				 <!-- 클릭시 나타날 사이드바 -->
        		<div class="sidebar">
        			<div class="sidebarList">
	        			<label class="list">MINIMALISM</label><br>
		        			<a class="a" href="/storeInfo/about">About</a><br>
		        			<a class="a" href="/storeInfo/stockist">Stockist</a><br>
	        			<label class="list">BAG</label><br>
		        			<a class="a" href="/viewAll">View all</a><br>
		        			<a class="a" href="/viewShoulder">Shoulder</a><br>
		        			<a class="a" href="/viewCross">Cross</a><br>
		        			<a class="a" href="/viewBucket">Bucket</a><br>
		        			<a class="a" href="/viewTote">Tote</a><br>
	        			<label class="list">BOARD</label><br>
		        			<a class="a" href="/notice">NOTICE</a><br>
		        			<a class="a" href="/reviews">REVIEW</a><br>
		        			<a class="a" href="/qnas">Q&amp;A</a><br>
		        	</div>
        		</div>
			</div>
			<div class="col-md-6">
				<ul class="nav justify-content-center">
					<li class="nav-item">
						<a href="/"><img class="logo" alt="logo" src="img/minimalism.png" width="300"></a>
					</li>
				</ul>
			</div>
			<div class="col-md-3">
				<ul class="nav justify-content-end">
					<c:if test="${loginUser == null}">
					<li class="nav-item">
						<a href="/loginForm">LOGIN</a>
					</li>
					<li class="nav-item">
						<a href="/joinForm">JOIN</a>
					</li>
					<li class="nav-item">
						<a href="">CART</a>
					</li>
					</c:if>
					<c:if test="${loginUser != null}">
					<li class="nav-item">
						<a href="/mypageForm">${loginUser.username}(${loginUser.id})</a>
					
					<li class="nav-item">
						<a href="/logout">LOGOUT</a>
					</li>
					
					<li class="nav-item">
						<a href="">CART</a>
					</li>			
					</c:if>
				</ul>
			</div>
		</nav>
	</header>
	<!--  -->
	<main>
       <div id="carouselExampleInterval" class="carousel slide" data-ride="carousel">
		  <div class="carousel-inner">
		    <div class="carousel-item active" data-interval="10000">
		      <img src="img/main_img1.jpg" class="d-block w-100" alt="main_img">
		    </div>
		    <div class="carousel-item" data-interval="2000">
		      <img src="img/main_img2.jpg" class="d-block w-100" alt="main_img">
		    </div>
		    <div class="carousel-item">
		      <img src="img/main_img3.jpg" class="d-block w-100" alt="main_img">
		    </div>
		    <div class="carousel-item">
		      <img src="img/main_img4.jpg" class="d-block w-100" alt="main_img">
		    </div>
		  </div>
		  <a class="carousel-control-prev" href="#carouselExampleInterval" role="button" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="carousel-control-next" href="#carouselExampleInterval" role="button" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </a>
		</div>
		<c:if test="${msg != null}">
			 <script>
			 let msg = "${msg}";
			 alert(msg);
			 </script>
			 <c:remove scope="session" var="msg" />
		</c:if>
	</main>
</body>
</html>
<%@ include file="footer.jsp" %>