<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review</title>
<link rel="stylesheet" type="text/css" href="/css/board.css">
</head>
<!-- jQuery 선언(다운방식)  -->
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	// 검색을 할때와 안할때 페이징 처리
	function pageClick(pageNum){
		var jsReviewSearchForm = document.jsReviewSearch;
		$('#currentPage').val(pageNum);   // form태그 안에 input태그의 id값
		jsReviewSearch.submit();
	}
	
	// 검색옵션 가지고 가기
	$(function(){
 		var options = $('#searchfun').find("option");
 		options.each(function(){
 			var optionVal = $(this).val();
		if(optionVal=='${rnQDto.search}'){
			  $(this).attr('selected','selected');
		}
		});	
	});	
</script>
<body>
<main>
	<div id="bigForm">
		<div class="flex-container p">
			<h2>REVIEW</h2><br>		
			<%-- <h3>목록갯수 : ${totalReview}</h3> --%>
		</div>
			<div class="flex-container rnqListForm">
				<!-- 리스트 -->
				<c:set var="num" value="${page.total - page.start + 1}"></c:set>
				<table class="reviewList">
					<colgroup>
						<col width="50">
						<col width="250">
						<col width="450">
						<col width="80">
						<col width="80">
						<col width="50">
					</colgroup>
					<tr>
						<th>번호</th>
						<th>상품정보</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회</th>
					</tr>
					<c:forEach var="review" items="${reviewList}">
						<tr>
							<td>${review.rq_id}</td>
							<td style="text-align: left; padding-left: 35px;">
								<img style="width: 48px; height: 64px;" src="${review.stored_thumbnail}">
								${review.product_number}
								${review.product_name}
							</td>
							<td>
								<a href="/board/reviewDetail?rq_id=${review.rq_id}">${review.rq_title}</a>
							</td>
							<td>${review.rq_name}</td>
							<td>
								<fmt:formatDate value="${review.rq_date}" type="date" pattern="YYYY.MM.dd"/>
							</td>
							<td>${review.rq_readcount}</td>
						</tr>
						<c:set var="num" value="${num - 1 }"></c:set>
					</c:forEach>	
					<c:if test="${totalReview <= 0}">
						<tr>
							<td colspan="10"
								style="padding: 100px;">게시글이 없습니다.</td>
						</tr>
					</c:if>		
				</table>		
			</div>
			<!-- 글쓰기 버튼 -->
			<div class="flex-container reviewWritebutton">
				<!-- <button class="submit_box" type="submit" onclick="">글쓰기</button> -->
				<a href="/board/WriteReviewForm">
					<input class="reviewWriteSubmit_box" type="submit" value="글쓰기">
				</a>
			</div>
			<!-- 페이징 -->
			<div class="flex-container pageForm">
				<c:if test="${page.startPage > page.pageBlock}">
					<a href="review?currentPage=${page.startPage - page.pageBlock}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
					<button onclick="pageClick(${i})">${i}</button>
				</c:forEach>
				<c:if test="${page.endPage < page.totalPage}">
					<a href="review?currentPage=${page.startPage + page.pageBlock}">[다음]</a>
				</c:if>
			</div>		
		<!-- 검색 -->
		<div class="flex-container searchForm">
			<div class="search">
				<form action="/board/reviewSearch" name="jsReviewSearch">	
					<input type="hidden" id="currentPage" name="currentPage" value="1">		
					<select name="search" id="searchfun" >
						<option value="product_name" >상품명</option>
						<option value="rq_name">작성자</option>
					</select>
					<input class="search_box" type="text" name="keyword" placeholder="검색어를 입력하세요" value="${rnQDto.keyword}">
					<button class="submit_box" type="submit" >찾기</button>					
				</form>
			</div>
		</div>
	</div>
</main>
</body>
</html>
<%@ include file="footer.jsp" %>
