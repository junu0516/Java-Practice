<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//서블릿객체로부터 받아온 request 객체에 저장된 message 사용(getAttribute)
	String message = (String)request.getAttribute("message");   
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center"><%=message %></h1>
	<div align="center">
		<button onclick="location.href='<%=request.getContextPath() %>/views/common/menubar.jsp'" style="width:50%">홈으로 돌아가기</button>
	</div>
</body>
</html>