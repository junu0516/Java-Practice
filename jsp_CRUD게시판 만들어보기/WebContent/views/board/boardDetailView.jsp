<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.kh.board.model.vo.*" %>
<%
	Board b = (Board)request.getAttribute("b");
	Attachment at = (Attachment)request.getAttribute("at");
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
	
	.outer>table, .outer>table tr>*{
		border:1px solid white;
	}
	
	.outer>table{
		width:600px;
		height:300px;
	}
	
	.outer>table p{
		height:230px;
		margin:0;
	}
	
	.replyArea{
		width:800px;
		color:white;
		background:black;
		margin:auto;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		
		<h2 align="center">게시판 상세보기</h2>
		<br>
		
		<table align="center">
			<tr>
				<th width="100">분야</th>
				<td><%= b.getCategory() %></td>
				<th>제목</th>
				<td colspan="2"><%= b.getBoardTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%= b.getBoardWriter() %></td><%-- 
				<th>조회수</th>
				<td><%= b.getCount() %></td> --%>
				<th>작성일</th>
				<td><%= b.getCreateDate() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p><%= b.getBoardContent() %></p>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<% if(at != null){ %>
					<a download="<%= at.getOriginName() %>" href="<%=contextPath%>/resources/board_upfiles/<%=at.getChangeName()%>"><%= at.getOriginName() %></a>
					<% }else{ %>
					첨부파일이 없습니다.
					<% } %>
				</td> 
			</tr>
		</table>
		<br>
		
		<div class="btns" align="center">
			<button type="button" onclick="location.href='<%=contextPath%>/list.bo?currentPage=1';">목록으로</button>
			
			<% if(loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())){ %>
				
				<button type="button" onclick="updateForm();">수정하기</button>
				<button type="button" onclick="deleteBoard();">삭제하기</button>
			<% } %>
		</div>
		
		<form action="" id="postForm" method="post">
			<input type="hidden" name="bno" value="<%= b.getBoardNo() %>">
		</form>
		<script>
			function updateForm(){
				$("#postForm").attr("action", "<%=contextPath%>/updateForm.bo");
				$("#postForm").submit();
			}
			
			function deleteBoard(){
				$("#postForm").attr("action", "<%=contextPath%>/deleteB.bo");
				$("#postForm").submit();
			}
		</script>
	</div>
	<!-- 댓글 관련 영역 -->
	<div class="replyArea">
		<!-- 댓글 작성하는 div -->
		<table border="1" align="center">
			<tr>
				<th>댓글작성</th>
				<% if(loginUser != null){ %>
				<td><textarea rows="3" cols="60" id="replyContent" style="resize:none;"></textarea></td>
				<td><button id="addReply">댓글등록</button></td>
				<% }else{ %>
				<td><textarea readonly rows="3" cols="60" id="replyContent" style="resize:none;">로그인한 사용자만 가능한 서비스입니다. 로그인 후 이용해주세요</textarea></td>
				<td><button disabled>댓글등록</button></td>
				<% } %>
			</tr>
		</table>
		<!-- 댓글 리스트들 보여주는 div -->
		<div id="replyListArea">
			<table id="replyList" border="1" align="center">
				<!-- <tr>
					<td width="100px">admin</td>
					<td width="330px">댓글작성내용</td>
					<td width="150px">2020년 1월 23일</td>
				</tr>
				<tr>
					<td width="100px">user01</td>
					<td width="330px">댓글작성내용</td>
					<td width="150px">2020년 1월 22일</td>
				</tr>
				<tr>
					<td width="100px">test01</td>
					<td width="330px">댓글작성내용</td>
					<td width="150px">2020년 1월 20일</td>
				</tr> -->
			</table>
		</div>
	</div> 
	<script>
		$(function(){
			selectReplyList();
			$("#addReply").click(function(){
				var content = $("#replyContent").val();
				var bId = <%=b.getBoardNo()%>;
				$.ajax({
					url:"rinsert.bo",
					type:"post", //내용이 길기 때문에 get보다는 post가 적합
					data:{
						content:content,
						bId:bId
					},
					success:function(status){
						console.log(status);
						if(status=="success"){
							selectReplyList(); //업데이트된 댓글목록 보여주기 위해 해당 함수 호출
							$("#replyContent").val("");
						}
					},
					error:function(){
						console.log("ajax 통신 실패");
					}
				});
			});
		});
		function selectReplyList(){
			$("#replyList").empty();
			$.ajax({
				url:"rlist.bo",
				data:{bId:<%=b.getBoardNo()%>},
				type:"get",
				success:function(list){
					console.log(list);

					// 1번 방법
					var value = "";
					for(var i in list){
						value += '<tr>' + 
									'<td width="100px">' + list[i].replyWriter + '</td>' +
									'<td width="330px">' + list[i].replyContent + '</td>' + 
									'<td width="150px">' + list[i].createDate  + '</td>' + 
								 '</tr>';
					}
					
					$("#replyList").html(value);
					
					
					/*
					// 2번 방법
					var value = "";
					$.each(list, function(index, obj){
						value += '<tr>' + 
									'<td width="100px">' + obj.replyWriter + '</td>' +
									'<td width="330px">' + obj.replyContent + '</td>' + 
									'<td width="150px">' + obj.createDate  + '</td>' + 
								 '</tr>';
					});
					
					$("#replyList").html(value);
					
					
					// 3번 방법
					$.each(list, function(index, obj){
						
						var writerTd = $("<td>").text(obj.replyWriter).attr("width", "100px");
						var contentTd = $("<td>").text(obj.replyContent).attr("width", "330px");
						var dateTd = $("<td>").text(obj.createDate).attr("width", "150px");
						
						var tr = $("<tr>").append(writerTd, contentTd, dateTd);
						
						$("#replyList").append(tr);
						
					});
					*/
				},
				error:function(){
					console.log("ajax 통신 실패");
				}
			});
		}
	</script>
</body>
</html>