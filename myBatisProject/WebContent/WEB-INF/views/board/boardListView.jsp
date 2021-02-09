<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#listArea {
	border: 1px solid white;
	text-align: center;
}

#pagingArea a {
	color: white;
	text-decoration: none;
}

#listArea>tbody>tr:hover {
	background: darkgray;
	cursor: pointer;
}
</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />

	<div class="outer">
		<br>
		<h1 align="center">게시판</h1>

		<!-- 검색영역 -->
		<div id="searchArea" align="center">
			<form action="search.bo">
				<select name="condition">
					<option value="writer">작성자</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select> <input type="search" name="search" value="${ search }">
				<button type="submit">검색하기</button>
			</form>
		</div>

		<script>
			$(function(){
				switch('${condition}'){
				case "writer" : $("#searchArea option").eq(0).attr("selected", true); break;
				case "title" : $("#searchArea option").eq(1).attr("selected", true); break; 
				case "content" : $("#searchArea option").eq(2).attr("selected", true); break; 
				}
			})
		</script>

		<br> <br>

		<!-- 게시물리스트 -->
		<table id="listArea" align="center">
			<thead>
				<tr>
					<th>글번호</th>
					<th width="300">제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="b" items="${ list }">
					<tr>
						<td>${ b.boardNo }</td>
						<td>${ b.boardTitle }</td>
						<td>${ b.boardWriter }</td>
						<td>${ b.count }</td>
						<td>${ b.createDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
			<!-- 페이징바영역 -->
		
		<div id="pagingArea" align="center">
			<!-- [이전] -->
			<c:if test="${ pi.currentPage != 1 }">
				<c:if test="${ empty search }">
						<a href="list.bo?currentPage=${ pi.currentPage-1 }">[이전]</a>
					</c:if>
					<c:if test="${ !empty search }">
						<c:url var="searchUrl" value="search.bo">
							<c:param name="currentPage" value="${pi.currentPage-1 }"/>
							<c:param name="condition" value="${ condition }"/>
							<c:param name="search" value="${ search }"/>
						</c:url>
						<a href="${ searchUrl }">[이전]</a>
					</c:if>
			</c:if>
			
			<!-- [번호들] -->
			<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
				<c:if test="${ pi.currentPage eq p }">
					<font color="red" size="4">[${ p }]</font>
				</c:if>
				<c:if test="${ pi.currentPage ne p }">
					<%-- <a href="list.bo?currentPage=${ p }">[${ p }]</a> --%>
					
					<c:if test="${ empty search }">
						<a href="list.bo?currentPage=${ p }">[${ p }]</a>
					</c:if>
					<c:if test="${ !empty search }">
						<c:url var="searchUrl" value="search.bo">
							<c:param name="currentPage" value="${ p }"/>
							<c:param name="condition" value="${ condition }"/>
							<c:param name="search" value="${ search }"/>
						</c:url>
						<a href="${ searchUrl }">[${ p }]</a>
					</c:if>
				</c:if>
			</c:forEach>
			
			<!-- [다음] -->
			<c:if test="${ pi.currentPage ne pi.maxPage }">
				<c:if test="${ empty search }">
						<a href="list.bo?currentPage=${ pi.currentPage+1 }">[다음]</a>
					</c:if>
					<c:if test="${ !empty search }">
						<c:url var="searchUrl" value="search.bo">
							<c:param name="currentPage" value="${pi.currentPage+1  }"/>
							<c:param name="condition" value="${ condition }"/>
							<c:param name="search" value="${ search }"/>
						</c:url>
						<a href="${ searchUrl }">[다음]</a>
					</c:if>
			</c:if>
		</div>
		
		<br><br>
	</div>
		<script>
		$(function(){
			$("#listArea>tbody>tr").click(function(){
				location.href="detail.bo?bno=" + $(this).children().eq(0).text();
			});
		});
	</script>
</body>
</html>