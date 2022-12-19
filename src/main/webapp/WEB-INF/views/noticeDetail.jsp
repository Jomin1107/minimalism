<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/noticeDetail.css">
</head>
<body>

	<div class="bigForm">
      <div class="flex-container contentsForm">
      	 <input type="hidden" name="notice_id" value="${notice.notice_id}">
         <table>
         	<colgroup>
         		<col width="20%">
         		<col width="90%">
         	</colgroup>
            <tr >
               <td style="border-bottom: 1px solid #d5d5d5;">제목</td>
               <td style="border-bottom: 1px solid #d5d5d5;">${notice.notice_title}</td>
            </tr>
            <tr>
               <td style="border-bottom: 1px solid #d5d5d5;">작성자</td>
               <td style="border-bottom: 1px solid #d5d5d5;">${notice.notice_name}</td>
            </tr>
            <tr>
            	<td style="border-bottom: 1px solid #d5d5d5;">작성일</td>
            	<td style="border-bottom: 1px solid #d5d5d5;">
            		<fmt:formatDate value="${notice.notice_date}" type="date" pattern="YYYY.MM.dd"/>
            	</td>
            </tr>
            <tr class="contentTr">
               <td colspan="2" style="border-bottom: 1px solid #d5d5d5;">${notice.notice_content}</td>
            </tr>
         </table>
      </div>
      <div class="flex-container btnBox">
	      <div class="leftBtnbox">
	         <input class="btnSubmit_box" type="button" value="목록" onclick="location.href='/board/notices'">
	      </div>
	      <div class="rightBtnbox">
	      	<c:if test="${loginUser.username == 'minimanager'}">
	         <input class="btnSubmit_box" type="button" value="수정" onclick="location.href='/board/noticeModify?notice_id=${notice.notice_id}'">
	         <input class="btnSubmit_box" type="button" value="삭제" onclick="location.href='/board/noticeDelete?notice_id=${notice.notice_id}'">
	      	</c:if>
	      </div>
      </div>

   </div>
</body>
</html>
<%@ include file="footer.jsp" %>