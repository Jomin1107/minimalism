<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/boardDetail.css">
</head>
<body>
	<main>
		<div class="bigForm">
			<div class="flex-container contentsForm">
				<input type="hidden" name="rq_id" value="${review.rq_id}">
				<table class="reviewDetail">
					<colgroup>
						<col width="20%">
						<col width="90%">
					</colgroup>
					<c:forEach var="qna" items="${qnaDetailList}" varStatus="status">
						<c:if test="${status.index == 0}">
							<tr>
								<td class="reviewborder">번호</td>
								<td class="reviewborder">${qna.rq_id}</td>
							</tr>
							<tr>
								<td class="reviewborder">상품코드</td>
								<td class="reviewborder">${qna.product_number}</td>
							</tr>
							<tr>
								<td class="reviewborder">상품명</td>
								<td class="reviewborder">${qna.product_name}</td>
							</tr>
							<tr>
								<td class="reviewborder">작성자</td>
								<td class="reviewborder">${qna.rq_name}</td>
							</tr>
							<tr>
								<td class="reviewborder">작성일</td>
								<td class="reviewborder">
									<fmt:formatDate value="${qna.rq_date}" type="date" pattern="YYYY.MM.dd"/>
								</td>						
							</tr>
							<tr>
								<td class="reviewborder">제목</td>
								<td class="reviewborder">${qna.rq_title}</td>
							</tr>
							<tr class="reviewcontentTr">
								<td colspan="2" class="reviewborder">
									${qna.rq_content}
								</td>
							</tr>
						</c:if>
						<c:choose>			
							<c:when test="${qna.rq_imgnumber != null && qna.rq_filename != null}">
								<tr>			
									<td colspan="10" style="text-align: center;">	
			               			<img src="${pageContext.request.contextPath}/qnaImg/${qna.rq_filename}">  <!-- /reviewImg/ 사용할 폴더명 넣을 칼럼 추가하기 -->				
									</td>
								</tr>
							</c:when>				
							<c:when test="${qna.rq_imgnumber == null && qna.rq_filename == null}"> 
							</c:when>			
						</c:choose>	
					</c:forEach>
				</table>
			</div>
			<div class="flex-container btnBox">
				<div class="reviewLeftBtnbox">
					<input class="reviewSubmit_box" type="button" value="목록" onclick="location.href='/board/qnas'">
				</div>
				<c:forEach var="qna" items="${qnaDetailList}" varStatus="status">
				 <c:if test="${status.index == 0}">
				<div class="reviewRightBtnbox">
					<c:if test="${loginUser.username == qna.rq_name}">
					<input class="reviewSubmit_box" type="button" value="수정" onclick="location.href='/board/qnaModify?rq_id=${qna.rq_id}'">
	         		<input class="reviewSubmit_box" type="button" value="삭제" onclick="location.href='/board/qnaDelete?rq_id=${qna.rq_id}'">				
					</c:if>    
				</div>
				</c:if>
				</c:forEach>
			</div>
		</div>
	</main>
</body>
</html>
<%@ include file="footer.jsp" %>