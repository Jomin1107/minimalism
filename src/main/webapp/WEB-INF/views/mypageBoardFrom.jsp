<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/board.css">
</head>
<body>
	<main>
	<div id="bigForm">
		<div class="flex-container p">
			<h2>MY WRITING</h2><br>
			<%-- <h3>목록갯수 : ${totalMypage}</h3> --%>
			<%-- <h3>유저명    : ${loginUser.username}</h3><br> --%>

		</div>
		<!-- 선택옵션 -->
<!-- 		
		<div class="flex-container selectForm">
			<div class="rnqselectForm">
				<select class="rnqSelectBox">
					<option>리뷰</option>
					<option>문의</option>
				</select>
			</div>
		</div>
-->
		
		<div class="flex-container noticeListForm">
		<table class="reviewList">
			<colgroup>
			<col width="50">
			<col width="150">
			<col width="180">
			<col width="50">
			<col width="100">
			<col width="80">
			</colgroup>
			<tr>
				<th>번호</th>
				<th>상품정보</th>
				<th>제목</th>
				<th>분류</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>	
			<c:set var="num" value="${page.total - page.start + 1}"></c:set>
				
				<c:forEach var="rnq" items="${rnqList}">
					<c:if test="${num >= 1}">
					<input type="hidden" name="rq_name" value="${rnq.rq_name}">
					<c:if test="${loginUser.username == rnq.rq_name}">
						<tr>
							<td>${num}</td>
							<td style="text-align: left; padding-left: 35px;">
								<img style="width: 48px; height: 64px;" src="${rnq.stored_thumbnail}">
								${rnq.product_name}
							</td>
							<td>${rnq.rq_title}</td>
							<td>
								<c:choose>
									<c:when test="${rnq.rq_category == 'r'}">
										<c:out value="review"></c:out>
									</c:when>
									<c:otherwise>
										<c:out value="Q&A"></c:out>
									</c:otherwise>
								</c:choose>
							</td>
							<td>${rnq.rq_name}</td>
							<td>
								<fmt:formatDate value="${rnq.rq_date}" type="date" pattern="YYYY.MM.dd"/>
							</td>
						</tr>
				<c:set var="num" value="${num - 1 }"></c:set>		
				</c:if>
				</c:if>
				</c:forEach>
				
				<c:if test="${totalMypage == 0}">
					<tr>
						<td colspan="10"
							style="padding: 100px;">게시글이 없습니다.</td>
					</tr>
				</c:if>
		</table>
		</div>

	</div>
	<!-- 페이징 -->
	<div class="flex-container pageForm">
		<input type="hidden" id="currentPage" name="currentPage" value="1">	
		<c:if test="${page.startPage > page.pageBlock}">
			<a href="mypageBoardService?currentPage=${page.startPage - page.pageBlock}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
			<a href="mypageBoardService?currentPage=${i}">${i}</a>
		</c:forEach>
		<c:if test="${page.endPage < page.totalPage}">
			<a href="mypageBoardService?currentPage=${page.startPage + page.pageBlock}">[다음]</a>
		</c:if>
	</div>
	</main>
</body>
</html>
<%@ include file="footer.jsp" %>