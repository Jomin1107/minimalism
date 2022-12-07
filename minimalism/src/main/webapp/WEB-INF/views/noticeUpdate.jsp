<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body {
		margin: 0px;
	}
	div {
		/* border: 1px solid black; */    
	}
	.flex-container {
            display: flex;  /* 컨테이너들 수직정렬 */
            justify-content: center;
    }
    .flex-container .updateForm {
    	/* width: 70%; */
    }
    table {
		   width: 70%;
		   border-top: 1px solid black;
		   border-bottom: 1px solid #d5d5d5;
		   padding-top: 10px;
		   padding-bottom: 10px;
		   margin-top: 200px;
		   margin-bottom: 20px;
	}
	table th {
		border-top: 1px solid #d5d5d5;
		font-weight: normal;
		padding-top: 8px;
		padding-bottom: 8px;
		font-size: 12px;
	}
	
	table td {
		border-top: 1px solid #d5d5d5;
		font-weight: normal;
		padding-top: 8px;
		padding-bottom: 8px;
		font-size: 12px;
	}
	.leftBtnbox {
		width: 115px;
		padding: 5px;
	}
	.rightBtnbox {
		width: 210px;
		padding: 5px 0px;
		margin-left: 745px;
	}
	.submit_box {
    	border-radius: 0px;
    	border: 1px solid #d5d5d5;
    	width: 100px;
    	height: 37px;
    	background-color: white;
    	color: black;   
    	padding: 5px;
    			 
    }
</style>
</head>
<body>
<%@ include file="header.jsp" %>
	<div class="bigForm">
		<form action="noticeUpdate" method="post">
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
<%@ include file="footer.jsp" %>
</body>
</html>