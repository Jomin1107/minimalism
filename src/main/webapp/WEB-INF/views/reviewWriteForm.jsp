<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/reviewWriteForm.css">
</head>
<body>
<%@ include file="header.jsp" %>
	<div class="bigForm">
		<div class="flex-container p">
			<p>Q&amp;A WRITE</p>		
		</div>
		<form action="">
			<div class="flex-container productChoicebox">
				<!-- 상품선택 영역 -->
         		<table class="productImg">
            		<tr>
               			<th>
               				<img src=""
               					style="width: 120px; height: 152px;">
               			</th>
            		</tr>
         		</table>
         
         		<table class="productChoice">
            		<tr>
               			<td>
               				<span>Ajax로 선택상품노출</span>
               			</td>
            		</tr>
            		<tr>
               			<td colspan="2" style="padding: 0px 10px 0px 10px; height: 1px;">
               				<hr class="line">
               			</td>
            		</tr>
            		<tr>
               			<td colspan="2">
               				<a href="http://localhost:5555/reviewChoiceForm" 
               				   onclick="window.open(this.href, '_blank', 'width=500px, height=500px, toolbars=no, scrollbars=yes'); return false;">
                     			<input class="productSubmit_box" type="submit" value="상품정보선택">            				
               				</a>
               			</td>
            		</tr>
         		</table>
      		</div>
      		<div class="flex-container writeBox">
      		<table class="writeTable">
				<colgroup>
					<col width="200">
					<col width="700">
				</colgroup>
				<tr>
					<td>제목</td>
					<td>
						<input style="width: 850px; height: 30px;" type="text" required="required">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea rows="20" cols="150"></textarea>
					</td>
				</tr>
				<tr>
					<td>첨부파일1</td>
					<td>
						<input type="file" name="imgFile">
					</td>
				</tr>
				<tr>
					<td>첨부파일2</td>
					<td>
						<input type="file" name="imgFile">
					</td>
				</tr>
				<tr>
					<td>첨부파일3</td>
					<td>
						<input type="file" name="imgFile">
					</td>
				</tr>
				<tr>
					<td>첨부파일4</td>
					<td>
						<input type="file" name="imgFile">
					</td>
				</tr>
				<tr>
					<td>첨부파일5</td>
					<td>
						<input type="file" name="imgFile">
					</td>
					</tr>
				<tr>
					<td style="border-bottom: 1px solid black;">비밀글 설정</td>
					<td style="border-bottom: 1px solid black;">
						<label><input type="radio" name="rq_secret" value=""> 공개</label> 
						<label><input type="radio" name="rq_secret" value=""> 비공개</label> 
					</td> 
				</tr>
			</table>
      		</div>
      		
		<div class="leftBtnbox">
			<input class="submit_box" type="reset" value="취소">
		</div>
		<div class="rightBtnbox">
			<input class="submit_box" type="reset" value="등록">
		</div>
		
		</form>
	</div>
</body>
</html>
<%@ include file="footer.jsp" %>