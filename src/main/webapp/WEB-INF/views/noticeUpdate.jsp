<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/boardUpdate.css">
</head>
<body>
<%@ include file="header.jsp" %>
	<div class="bigForm">
		<form action="/board/noticeUpdate" method="post">
			<input type="hidden" name="notice_id" value="${notice.notice_id}">
			
			<div class="flex-container updateForm">
				<table>
					<colgroup>
						<col width="20%">
         				<col width="90%">
					</colgroup>
					<tr>
						<th>작성자</th>
						<td>${notice.notice_name}</td>					
					</tr>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" name="notice_title" required="required" value="${notice.notice_title}">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea rows="20" cols="200" name="notice_content">${notice.notice_content}</textarea>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="flex-container btnBox">
		    	<div class="leftBtnbox">
		        	<input class="submit_box" type="button" value="목록" onclick="location.href='notice'">
		      	</div>
		      	<div class="rightBtnbox">
		        	<input class="submit_box" type="submit" value="수정">
		      	</div>
      		</div>
		</form>
	</div>
</body>
</html>
<%@ include file="footer.jsp" %>