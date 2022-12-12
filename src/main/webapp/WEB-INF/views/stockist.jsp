<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/bootstrap.min.js">
<link rel="stylesheet" type="text/css" href="/css/stockist.css">
</head>
<body>
<%@ include file="header.jsp" %>
	<main>
		<!-- 지도[s] -->    
	    <div id="map"></div>
	    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=96b056d596ae469274dbdf6190d6eaec"></script>
	    <script type="text/javascript">
	    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new kakao.maps.LatLng(37.4996101, 127.030492), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };

		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
		// 마커가 표시될 위치입니다 
		var markerPosition  = new kakao.maps.LatLng(37.4996101, 127.030492); 
	
		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});
	
		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

	    </script>
	    
	    <div class="box">
			<div class="address">
		    	<div class="u">
		    		<u>SHOWROOM</u>
		    	</div>
		    	<div class="strong">
		    		<strong>minimalism 강남</strong><br>
		    	</div>
		    	<div class="p">
			    	<p>
			    	   서울특별시 강남구 테헤란로 7길 7 6F<br>
			    	   MON - SUN 11:30 - 20:30<br>
			    	   02-123-4567
			    	</p>
		    	</div>
	    	</div>
	    </div>
	</main>
<%@ include file="footer.jsp" %>
</body>
</html>
