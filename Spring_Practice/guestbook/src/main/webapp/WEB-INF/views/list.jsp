<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 리스트</title>
</head>
<body>
	<h1>방명록</h1><br>
	방명록 전체 수 : ${count}, 방문자 수 : ${cookieCount}
	<c:forEach items="${list}" var="guestbook">
	
		${guestbook.id }<br>
		${guestbook.name }<br>
		${guestbook.content }<br>
		${guestbook.regdate }<br>
	
	</c:forEach>
	<br>
	
	<c:forEach items="${pageStartList}" var="pageIndex" varStatus="status">
		<a href="list?start=${pageIndex}">${status.index +1 }</a>&nbsp; &nbsp;
	</c:forEach>
	<br>
	<br>
	
	<form method="post" action="write">
		name : <input type="text" name="name"><br>
		<textarea name="content" cols="60" rows="6"></textarea>
		<br> <input type="submit" value="등록">
	</form>
	
</body>
</html>