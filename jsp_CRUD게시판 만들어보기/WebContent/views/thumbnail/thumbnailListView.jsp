<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.board.model.vo.*" %>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");

%>
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		width:1000px;
		height:700px;
		background:black;
		color:white;
		margin:auto;
		margin-top:50px;
	}
	.listArea{
		width:760px;
		height:550px;
		margin:auto;
	}
	.thumbnail{
		display:inline-block;
		width:220px;
		border:1px solid white;
		margin:10px;
	}
	.thumbnail:hover{
		opacity:0.7;
		cursor:pointer;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">사진 게시판</h2>
		<br>
		<div class="listArea">
			<%for(Board b : list){ %>
			<div class="thumbnail" align="center">
				<input type="hidden" value="<%=b.getBoardNo()%>">
				<img src="<%=contextPath %>/resources/board_upfiles/<%= b.getTitleImg() %>" width="200px" height="150px"> <br>
				<p>
					No.<%= b.getBoardNo() %>  <%=b.getBoardTitle() %> <br>
					조회수 : <%=b.getCount() %>
				</p>
			</div>
			<%} %>
		
			
			<br><br>
			<div align="center">
			
			<% if(loginUser != null){ %>
				<button onclick="location.href='<%=contextPath %>/insertForm.th'">작성하기</button>
			<% } %>
			</div>
		</div>
		<script>
		
			$(function(){
				$(".thumbnail").click(function(){
					var bId = $(this).children().eq(0).val();
					location.href="<%=contextPath%>/detail.th?bId=" + bId;
				});
			});
		</script>
	</div>
</body>
</html>