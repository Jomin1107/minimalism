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
		<div class="flex-container">
			<%-- Login :  ${loginID} <br> --%>
			<%-- 작성자     :  ${reviewRq_name} --%>
     	</div>
		<div class="flex-container contentsForm">
			<input type="hidden" name="rq_id" value="${review.rq_id}">
			<table class="reviewDetail">
				<colgroup>
					<col width="20%">
         			<col width="90%">
				</colgroup>

				<c:forEach var="review" items="${reviewDetailList}"  varStatus="status">
					<c:if test="${status.index == 0}">
						<tr>
							<td class="reviewborder">번호</td>
							<td class="reviewborder">${review.rq_id}</td>
						</tr>
						<tr>
							<td class="reviewborder">상품코드</td>
							<td class="reviewborder">${review.product_number}</td>
						</tr>
						<tr>
							<td class="reviewborder">상품명</td>
							<td class="reviewborder">${review.product_name}</td>
						</tr>
						<tr>
							<td class="reviewborder">작성자</td>
							<td class="reviewborder">${review.rq_name}</td>
						</tr>
						<tr>
							<td class="reviewborder">작성일</td>
							<td class="reviewborder">
								<fmt:formatDate value="${review.rq_date}" type="date" pattern="YYYY.MM.dd"/>
							</td>
						</tr>
						<tr>
							<td class="reviewborder">제목</td>
							<td class="reviewborder">${review.rq_title}</td>
						</tr>				
						<tr class="reviewcontentTr">
							<td colspan="2" class="reviewborder">
								${review.rq_content}
							</td>
						</tr>				
					</c:if>						
		 			<c:choose>			
						<c:when test="${review.rq_imgnumber != null && review.rq_filename != null}">
							<tr>			
								<td colspan="10" style="text-align: center;">	
			               			<img src="${pageContext.request.contextPath}/reviewImg/${review.rq_filename}">  <!-- /reviewImg/ 사용할 폴더명 넣을 칼럼 추가하기 -->				
								</td>
							</tr>
						</c:when>				
						<c:when test="${review.rq_imgnumber == null && review.rq_filename == null}"> 
						</c:when>			
					</c:choose>										
				</c:forEach>
			</table>
		</div>
		<div class="flex-container btnBox">
	      <div class="reviewLeftBtnbox">
	         <input class="reviewSubmit_box" type="button" value="목록" onclick="location.href='/board/reviews'">
	      </div>
	      <c:forEach var="review" items="${reviewDetailList}" varStatus="status">
	      <c:if test="${status.index == 0}">
	      <div class="reviewRightBtnbox">         
	      	<c:if test="${loginUser.username == review.rq_name}">
	      		<input class="reviewSubmit_box" type="button" value="수정" onclick="location.href='/board/reviewModify?rq_id=${review.rq_id}'">
	         	<input class="reviewSubmit_box" type="button" value="삭제" onclick="location.href='/board/reviewDelete?rq_id=${review.rq_id}'">
 	      	</c:if>    
	      </div>
	      </c:if>
	      </c:forEach>
      </div>
      <!-- 댓글노출 영역 -->
<%--       <div class="flex-container replySeeBox">
      	<c:forEach var="review" items="ReplyList">
      		<table>
      			<tr>
      				<td>${review.rq_name}</td>
      				<td>
      					<fmt:formatDate value="${review.rq_date}" type="date" pattern="YYYY.MM.dd"/>
      				</td>
      			</tr>
      			<tr>
      				<td>
      					${review.rq_content}
      				</td>
      			</tr>
      			<c:choose>
      				<c:when test="${loginUser.username == review.rq_name}">
      					<tr>
      						<td>
      							<input type="submit" value="삭제" onclick="location.href='/board/reviewReplyDelete?rq_id=${review.rq_name}'">
      						</td>
      					</tr>
      				</c:when>
      			</c:choose>
      		</table>
      	</c:forEach>
      </div>    --%>   
      <!-- 댓글작성 영역 -->
<%--       <div class="flex-container replyBox">
      	<form action="/board/reviewReplyWrite?rq_id=${review.rq_id}" method="post">
      	<form action="/board/reviewReply?rq_id=${review.rq_id}&pageNum=${pageNum}" method="post">
      		<input type="hidden" name="rq_id"    value="${review.rq_id}">
      		<input type="hidden" name="rq_group" value="${review.rq_group}">
      		<input type="hidden" name="rq_step"  value="${review.rq_step}">
      		<input type="hidden" name="rq_level" value="${review.rq_level}">
      		<input type="hidden" name="rq_id"    value="${review.rq_id}">
      		
      		<table class="replyBoxtable">
      			<tr>
      				<td colspan="2" style="padding-left: 20px;">댓글쓰기</td>
      			</tr>
      			<tr>
      				<td colspan="2" style="padding-left: 20px;">${loginUser.username}</td>
      			</tr>
      			<tr>
      				<td>
      					<textarea rows="6" cols="160"></textarea>
      				</td>
      				<td>
      					<input class="replySubmit_box" type="submit" value="확인">
      				</td>
      				
      			</tr>
      		</table> 	
      	</form>
      
      </div> --%>
	</div>
</main>
</body>
</html>
<%@ include file="footer.jsp" %>