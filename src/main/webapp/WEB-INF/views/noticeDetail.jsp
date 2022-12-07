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
		 /* border: 1px solid black;    */
	}
	.flex-container {
            display: flex;  /* 컨테이너들 수직정렬 */
            justify-content: center;
    }
    .flex-container .contentsForm{
    	width: 70%;
    	
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
	table td {
		/* border-top: 1px solid #d5d5d5; */
		font-weight: normal;
		padding-top: 8px;
		padding-bottom: 8px;
		font-size: 12px;
	}
	.contentTr{
		height: 300px;
		text-align: center;
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
      <div class="flex-container contentsForm">
         <table>
         	<colgroup>
         		<col width="20%">
         		<col width="90%">
         	</colgroup>
            <tr>
               <td>제목</td>
               <td>${notice.notice_title}</td>
            </tr>
            <tr>
               <td style="border-bottom: 1px solid #d5d5d5;">작성자</td>
               <td style="border-bottom: 1px solid #d5d5d5;">${notice.notice_name}</td>
            </tr>
            <tr class="contentTr">
               <td colspan="2">${notice.notice_content}</td>
            </tr>
         </table>
      </div>
      <div class="flex-container btnBox">
	      <div class="leftBtnbox">
	         <input class="submit_box" type="button" value="목록" onclick="location.href='notice'">
	      </div>
	      <div class="rightBtnbox">
	         <input class="submit_box" type="button" value="수정" onclick="location.href='noticeModify?notice_id=${notice.notice_id}'">
	         <input class="submit_box" type="button" value="삭제" onclick="location.href='noticeDelete?notice_id=${notice.notice_id}'">
	      </div>
      </div>
   </div>
<%@ include file="footer.jsp" %>
</body>
</html>