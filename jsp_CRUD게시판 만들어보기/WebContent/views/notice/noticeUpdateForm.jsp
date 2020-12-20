<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.kh.notice.model.vo.Notice" %>
<%
	Notice notice = (Notice)request.getAttribute("notice");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		width:800px;
		height:500px;
		background:black;
		color:white;
		margin:auto;
		margin-top:50px;
	}
	#updateForm{width:60%; margin:auto;}
	#updateForm>table{border:1px solid white;}
	#updateForm>table input{
		width:100%;
		box-sizing:border-box;
	}
	
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">공지사항 수정하기</h2>
		
		<form id="updateForm" action="<%= contextPath %>/update.no" method="post" >
			<input type="hidden" name="nno" value="<%= notice.getNoticeNo() %>">
			<table align="center">
				<tr>
					<td>제목</td>
					<td colspan="3"><input type="text" name="title" value="<%= notice.getNoticeTitle() %>"></td>
				</tr>
				
				<tr>
					<td>내용</td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td colspan="4">
						<textarea name="content" cols="60" rows="15" style="resize:none;"><%= notice.getNoticeContent() %></textarea>
					</td>
				</tr>	
			</table>
			<br>
			
			<div class="btns" align="center">
				<button type="submit">수정하기</button>
			</div>
		</form>
	</div>

</body>
</html>