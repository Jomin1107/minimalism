<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/noticeWriteForm.css">
</head>
<body>
	<main>
	<div class="bigForm">
		<div class="flex-container p">
			<p>NOTICE WRITE FROM</p><br>
			<c:if test="${msg != null}"></c:if>
		</div>
		<form action="/board/noticeWrite" method="post">
			<div class="flex-container noticeWriteForm">
			<input type="hidden" name="notice_name" value="${loginUser.username}">
			<input type="hidden" name="n_step"      value="0">
			<input type="hidden" name="n_level"     value="0">
			<input type="hidden" name="n_delete"    value="0">
			
			<table class="writeTable">
				<tr>
					<td style="text-align: center;">제목</td>
					<td>
						<input style="width: 670px; height: 40px;" type="text" 
							   name="notice_title" required="required">
					</td>
				</tr>
				<tr>
					<td style="text-align: center;">내용</td>
					<td>
						<textarea rows="10" cols="100" name="notice_content"></textarea>
					</td>
				</tr>			
			</table>
			</div>
			<div class="leftBtnbox">
				<input class="submit_box" type="reset" value="취소" onclick="location.href='/board/notices'">
			</div>
			<div class="rightBtnbox">
				<input class="submit_box" type="submit" value="등록">
			</div>
		</form>
	</div>
	</main>
<%@ include file="footer.jsp" %>
</body>
</html>