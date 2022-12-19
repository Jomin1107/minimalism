<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice</title>
<link rel="stylesheet" type="text/css" href="/css/board.css">
</head>
<!-- jQuery 선언(다운방식)  -->
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function pageClick(pageNum){
	    var jsNoticeSearchForm = document.jsNoticeSearch;
	//	alert("pageNum->" + pageNum);		// 페이지 버튼 누를때 pageNum에 대한 넘버 확인하기
		
		$('#currentPage').val(pageNum);									// 1번 - 예쁘게 작성하는 방법
	//	$('#currentPage').val() = pageNum;                              // 2번 - 1번을 풀어쓴 방법
	//	document.getElementById("currentPage").value = pageNum; 		// button값 변경 : 3번 - 1번 2번을 포함해 더 풀어쓴 방법
		
		jsNoticeSearch.submit();		//  javaScript에서 NoticeSearchForm을 submit 해줌 
		
    /* ----------------------------------- 두번째 방법 ----------------------------------- */		
	//  
	// 	var sendData = $('form').serialize();                // form태그의 데이터를 읽어온다.
	//	var sendData = sendData + "&currentPage="+pageNum;   // 데이터와 currentPage에 pageNum을 더한다.
	//	alert("sendData->" + sendData);                      // 재선언한 sendData의 정보를 확인해본다.
	
	//  location.href = "noticeSearch?"+sendData;			 // 경로에 설정해준다.
	/* ------------------------------------------------------------------------------- */
	
	} 
	
 	$(function(){
 		var options = $('#searchfun').find("option");
 		options.each(function(){
 			var optionVal = $(this).val();
		if(optionVal=='${noticeDto.search}'){
			  $(this).attr('selected','selected');
		}
		});	
	});

</script>
<body>
<main>
	<div id="bigForm">
		<div class="flex-container p">
			<h2>NOTICE</h2><br>
			<%-- <h3>목록갯수 : ${totalNotice}</h3>  --%>
		</div>
	
			<div class="flex-container noticeListForm">
				<!-- 리스트 -->
				
				<table class="noticeList">
					<colgroup>
						<col width="70">
						<col width="750">
						<col width="70">
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
								<a href="/board/noticeDetail?notice_id=${notice.notice_id}">${notice.notice_title}</a>	
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
			<!-- 글쓰기 버튼 -->
			<div class="flex-container noticeWritebutton">
				<a href="/board/WriteNoticeForm">
					<c:if test="${loginUser.username == 'minimanager'}">
						<input class="noticeWriteSubmit_box" type="submit" value="글쓰기">
					</c:if>
				</a>
				
				
			</div>
			<!-- 페이징 -->
			<div class="flex-container pageForm">
				<c:if test="${page.startPage > page.pageBlock}">
					<a href="notice?currentPage=${page.startPage - page.pageBlock}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
					<button onclick="pageClick(${i})">${i}</button>
				</c:forEach>
				<c:if test="${page.endPage < page.totalPage}">
					<a href="notice?currentPage=${page.startPage + page.pageBlock}">[다음]</a>
				</c:if>
			</div>
			
		<!-- 검색 -->
		<div class="flex-container searchForm">
			<div class="search">
				<form action="/board/noticeSearch" name="jsNoticeSearch" >	
				    <input type="hidden"  id="currentPage"  name="currentPage" value="1" >		
					<select name="search" id="searchfun">
						<option value="notice_title">제목</option>
						<option value="notice_name">작성자</option>						
					</select>
					<input class="search_box" type="text" name="keyword" placeholder="검색어를 입력하세요" value="${noticeDto.keyword}">
					<button class="submit_box" type="submit">찾기</button>
				</form>
			</div>
		</div>
	</div>
</main>
</body>
</html>
<%@ include file="footer.jsp" %>