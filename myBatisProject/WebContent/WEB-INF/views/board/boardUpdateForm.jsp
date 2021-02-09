<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	textarea{
		width : 500px;
		height : 300px;
	}

</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp" />

	<div class="outer">
		<br>
		<h1 align="center">게시글 수정하기</h1>
		<form id="updateForm" action="update.bo" method="post">
			<input type="hidden" name="bno" value="${b.boardNo}"/>
			<table align="center" class="tableArea">
				<tr>
					<td width="100">글번호</td>
					<td><b>${ b.boardNo }</b>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" value="${b.boardTitle}" required></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="writer" value="${b.boardWriter}" required/></td>
				</tr>
				<tr>
					<td>작성일</td>
					<td><b>${ b.createDate }</b></td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea row="15" name="content" style="resize:none;">${ b.boardContent }</textarea>
					</td>
				</tr>
			</table>
			<br>
			<div align="center" class="tableArea">
				<button type="submit">수정하기</button>
			</div>
		</form>
		<br>
		<br>
	</div>
</body>
</html>