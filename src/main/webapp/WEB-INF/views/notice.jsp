<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice</title>
<link rel="stylesheet" type="text/css" href="/css/board.css">
</head>
<body>
<%@ include file="header.jsp" %>
	<div id="bigForm">
		<div class="flex-container p">
			<p>NOTICE</p><br>
			<%-- <h3>목록갯수 : ${totalNotice}</h3>  --%>
			<%-- <h3>목록갯수 : ${totalNotice}</h3>  --%>
			<%-- <h3>목록갯수 : ${totalNotice}</h3>  --%>
		</div>
		<form action="">
			<div class="flex-container noticeListForm">
				<!-- 리스트 -->
				
				<table>
					<colgroup>
						<col width="50">
						<col width="780">
						<col width="60">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
						</tr>
					</thead>
					<c:set var="num" value="${page.total - page.start + 1}"></c:set>
					<c:forEach var="notice" items="${noticeList}">
						<tr>
							<td>${notice.notice_id}</td>
							<td>
								<a href="noticeDetail?notice_id=${notice.notice_id}">${notice.notice_title}</a>	
							</td>
							<td>${notice.notice_name}</td>
						</tr>
						<c:set var="num" value="${num - 1 }"></c:set>
					</c:forEach>
					
					 
					<c:if test="${totalNotice <= 0}">
						<tr>
							<td colspan="10"
								style="padding: 100px;">게시글이 없습니다.</td>
						</tr>
					</c:if> 
					
					
								
				</table>		
			</div>
			<!-- 페이징 -->
			<div class="flex-container pageForm">
				<c:if test="${page.startPage > page.pageBlock}">
					<a href="notice?currentPage=${page.startPage - page.pageBlock}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
					<a href="notice?currentPage=${i}">[${i}]</a>
				</c:forEach>
				<c:if test="${page.endPage < page.totalPage}">
					<a href="notice?currentPage=${page.startPage + page.pageBlock}">[다음]</a>
				</c:if>
			</div>
		</form>
		<!-- 검색 -->
		<div class="flex-container searchForm">
			<div class="search">
				<form action="noticeSearch">			
					<select name="search">
						<option value="notice_title" >제목</option>
						<option value="notice_name">작성자</option>
					</select>
					<input class="search_box" type="text" name="keyword" placeholder="검색어를 입력하세요">
					<button class="submit_box" type="submit">찾기</button>
				</form>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp" %>
</body>
</html>
