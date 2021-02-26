<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="common/menubar.jsp"/>
	<%--<div style="height: 400px"> --%>
		<div class="content"> <br><br>
			<div class="innerOuter" style="padding: 5% 10%;">
				<h1 align="center">게시글 TOP 5 목록</h1>
				<table id="boardList" class="table table-hover" align="center">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>날짜</th>
							<th>조회수</th>
							<th>첨부파일</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>

				<script>
					function topList(){
						$.ajax({
							url: 'topList.bo',
							success: function(list){
								$tableBody = $('#boardList tbody');
								$tableBody.html('');
								
			    				$.each(list, function(i, obj){
									var $tr = $('<tr>');
									var $bId = $('<td>').text(obj.boardNo);
									var $bTitle = $('<td>').text(obj.boardTitle);
									var $bWriter = $('<td>').text(obj.boardWriter);
									var $bCreateDate = $('<td>').text(obj.createDate);
									var $bCount = $('<td>').text(obj.count);
									var $bFile = $('<td>').text(" ");
									
									if(obj.originName != null){
										$bFile = $('<td>').text("O");
									}
								
									
									$tr.append($bId);
									$tr.append($bTitle);
									$tr.append($bWriter);
									$tr.append($bCreateDate);
									$tr.append($bCount);
									$tr.append($bFile);
									$tableBody.append($tr);
								});
							}
						});
					}
					
					$(function(){
						topList();
						<%--setInterval(function(){
							topList();
						}, 5000);--%>
					})
				</script>

			</div><br><br>
		</div>
	
	<jsp:include page="common/footer.jsp"/>
</body>
</html>