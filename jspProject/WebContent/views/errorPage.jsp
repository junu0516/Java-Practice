<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>에러페이지</h1>
	<h3>에러 종류 : <%=exception.getClass().getName() %></h3>
	<button onclick = "history.back();">이전페이지로</button>
</body>
</html>