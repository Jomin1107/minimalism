<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/boardWriteForm.css">
</head>
<body>
<main>
	<div class="bigForm">
		<div class="flex-container p">
			<h2>Q&amp;A WRITE</h2><br>
			<c:if test="${msg != null}"></c:if>
		</div>
		<form action="/board/qnaWrite" method="post"  enctype="multipart/form-data">    		
      		<div class="flex-container writeBox">
      		<input type="hidden" name="id"           value="${loginUser.id}">
      		<input type="hidden" name="rq_category"  value="q">
      		<input type="hidden" name="rq_name"      value="${loginUser.username}">
      		<!-- <input type="hidden" name="rq_group"     value="0"> -->
			<input type="hidden" name="rq_step"      value="0">
			<input type="hidden" name="rq_level"     value="0">
			<input type="hidden" name="rq_readcount" value="0">
			<input type="hidden" name="rq_delete"    value="0">
			
      		<table class="reviewWrite">
				<colgroup>
					<col width="200">
					<col width="700">
				</colgroup>
				<!-- 상품선택 영역 -->
				<tr>
					<td>상품명</td>
					<td>
						<select name="product_number">
							<c:forEach var="product" items="${productList}">
								<option value="${product.product_number}">${product.product_name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<!-- 내용작성 영역 -->
				<tr>
					<td>제목</td>
					<td>
						<input style="width: 850px; height: 30px;" 
								type="text" name="rq_title" required="required">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea rows="10" cols="150" name="rq_content" required="required"></textarea>
					</td>
				</tr>
				<!-- 이미지첨부 영역 -->
				<tr>
					<td>첨부파일1</td>
					<td>
						<input type="file" name="imgFile1">
					</td>
				</tr>
				<tr>
					<td>첨부파일2</td>
					<td>
						<input type="file" name="imgFile2">
					</td>
				</tr>
				<tr>
					<td>첨부파일3</td>
					<td>
						<input type="file" name="imgFile3">
					</td>
				</tr>
				<tr>
					<td>첨부파일4</td>
					<td>
						<input type="file" name="imgFile4">
					</td>
				</tr>
				<tr>
					<td>첨부파일5</td>
					<td>
						<input type="file" name="imgFile5">
					</td>
				</tr>
				<!-- 비밀글 설정 미구현 -->
<!-- 				
				<tr>
					<td style="border-bottom: 1px solid black;">비밀글 설정</td>
					<td style="border-bottom: 1px solid black;">
						<label><input type="radio" name="rq_secret" value="0" checked="checked"> 공개</label> 
						<label><input type="radio" name="rq_secret" value="1"> 비공개</label> 
					</td> 
				</tr> 
-->
			</table>
      		</div>
      		<div class="flex-container btnbox">
				<div class="reviewLeftBtnbox">
					<input class="reviewSubmit_box" type="reset" value="취소" onclick="location.href='/board/qnas'">
				</div>
				<div class="reviewRightBtnbox">
					<input class="reviewSubmit_box" type="submit" value="등록">
				</div>
			</div>		
		</form>
	</div>
</main>
</body>
</html>
<%@ include file="footer.jsp" %>