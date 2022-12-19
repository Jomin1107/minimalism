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
      	 <input type="hidden" name="notice_id" value="${notice.notice_id}">
         <table class="noticeDetail">
         	<colgroup>
         		<col width="20%">
         		<col width="90%">
         	</colgroup>
            <tr>
               <td>제목</td>
               <td>${notice.notice_title}</td>
            </tr>
            <tr>
               <td>작성자</td>
               <td>${notice.notice_name}</td>
            </tr>
            <tr>
            	<td>작성일</td>
            	<td>
            		<fmt:formatDate value="${notice.notice_date}" type="date" pattern="YYYY.MM.dd"/>
            	</td>
            </tr>
            <tr class="noticeContentTr">
               <td colspan="2">${notice.notice_content}</td>
            </tr>
         </table>
      </div>
      <div class="flex-container btnBox">
	      <div class="noticeLeftBtnbox">
	         <input class="noticeSubmit_box" type="button" value="목록" onclick="location.href='/board/notices'">
	      </div>
	      <div class="noticeRightBtnbox">
	      	<c:if test="${loginUser.username == 'minimanager'}">
	         <input class="noticeSubmit_box" type="button" value="수정" onclick="location.href='/board/noticeModify?notice_id=${notice.notice_id}'">
	         <input class="noticeSubmit_box" type="button" value="삭제" onclick="location.href='/board/noticeDelete?notice_id=${notice.notice_id}'">
	      	</c:if>
	      </div>
      </div>
      <!-- 댓글작성 영역 -->
<%--       <div class="flex-container replyBox">
      	<form action="/board/noticeReplyWrite?notice_id=${notice.notice_id}" method="post">
   <form action="/board/noticeReplyWrite?notice_id=${notice.notice_id}&pageNum=${pageNum}" method="post">
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