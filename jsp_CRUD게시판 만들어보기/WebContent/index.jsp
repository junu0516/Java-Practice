<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.topList {
		width: 1000px;
		height: 400px;
		background: black;
		color: white;
		margin: auto;
	}
	
	.thumb {
		display: inline-block;
		width: 250px;
		height: 250px;
		margin: 10px;
		border: 1px solid white;
	}
	
	.thumb:hover {
		cursor: pointer;
	}
</style>
</head>
<body>
	<%@ include file = "views/common/menubar.jsp" %>
	<div class="content">
	
	<div class="topList" align="center">
		<br>
		<h2>인기게시글</h2>

		<div id="thumbList">
			<!-- 
			<div class="thumb" align="center">
				<input type="hidden" value="1"> 
				<img src="<%=contextPath%>/resources/board_upfiles/2020121513395161442.png" width="250px" height="200px"> <br>
				<p>제목입니다.</p>
			</div>
			 -->
		</div>
	</div>
	<script>
		$(function(){
			selectTopList();
			//setInterval(selectTopList,2000); //2초마다 자동 갱신
			
			$("#thumbList").on("click",".thumb",function(){
				var bId = $(this).children().eq(0).val();
				location.href = "<%=contextPath%>/detail.th?bId="+bId;
			});
		});
		function selectTopList(){
			$.ajax({
				url:"topList.do",
				type:"get",
				success:function(list){
					console.log(list);
					var value = "";
					for(var i in list){
						value += '<div class="thumb" align="center">'+
								 '<input type="hidden" value="'+list[i].boardNo+'">'+
								 '<img src="<%=contextPath%>/resources/board_upfiles/'+list[i].titleImg+'"width="250px" height="200px"> <br>'+
								 '<p>'+list[i].boardTitle+'</p>'+
								 '</div>';
					}
					
					$("#thumbList").html(value);
				},
				error:function(){
					console.log("ajax 통신 실패")
				}
			});
		}
	</script>
	
	</div>
	<%@ include file = "views/common/footer.jsp" %>
</body>
</html>