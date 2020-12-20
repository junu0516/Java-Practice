<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	String today = sdf.format(date);
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
	#enrollForm{width:60%; margin:auto;}
	#enrollForm>table{border:1px solid white;}
	#enrollForm>table input{
		width:100%;
		box-sizing:border-box;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		
		<h2 align="center">공지사항 작성하기</h2>
		
		<form id="enrollForm" action="<%= contextPath %>/insert.no" method="post" >
			<table align="center">
				<tr>
					<td>제목</td>
					<td colspan="3"><input type="text" name="title"></td>
				</tr>
				
				<tr>
					<td>내용</td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td colspan="4">
						<textarea name="content" cols="60" rows="15" style="resize:none;"></textarea>
					</td>
				</tr>	
			</table>
			<br>
			
			<div class="btns" align="center">
				<button type="submit">등록하기</button>
				<!-- NoticeInsertServlet 만들어서 로직 처리
					작성자 회원 번호 writer : 세션에 loginUser의 getUserNo()를 가져와서 담을 것
					notice 객체에 담을 때 content.replaceAll("\n","<br>")
					
					insert 후,
					result>0 : msg - 공지사항이 정상적으로 등록되었습니다.
					response.sendRedirect("list.no");	
				-->
				
			</div>
		</form>
	</div>
</body>
</html>