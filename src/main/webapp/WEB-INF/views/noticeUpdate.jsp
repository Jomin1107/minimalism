<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/boardUpdate.css">
</head>
<body>
<main>
	<div class="bigForm">
		<form action="/board/noticeUpdate" method="post">
			<input type="hidden" name="notice_id" value="${notice.notice_id}">
			
			<div class="flex-container updateForm">
				<table class="noticeUpdate">
					<colgroup>
						<col width="20%">
         				<col width="90%">
					</colgroup>
					<tr>
						<td>작성자</td>
						<td>${notice.notice_name}</td>					
					</tr>
					<tr>
						<td>제목</td>
						<td>
							<input type="text" name="notice_title" required="required" value="${notice.notice_title}">
						</td>
					</tr>
					<tr>
						<td colspan="2" style="border-bottom: 1px solid black;">
							<textarea name="notice_content">
								${notice.notice_content}
							</textarea>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="flex-container btnBox">
		    	<div class="noticeLeftBtnbox">
		        	<input class="noticeSubmit_box" type="button" value="목록" onclick="location.href='/board/notices'">
		      	</div>
		      	<div class="noticeRightBtnbox">
		        	<input class="noticeSubmit_box" type="submit" value="수정">
		      	</div>
      		</div>
		</form>
	</div>
</main>
</body>
</html>
<%@ include file="footer.jsp" %>