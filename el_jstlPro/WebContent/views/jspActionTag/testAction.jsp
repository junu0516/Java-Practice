<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--  <%@include file="common.jsp" %> --%>
	<jsp:include page="common.jsp"/>
	<h1 align="center">testAction.jsp 내용입니다.</h1>
	
	
	<%--
		아래처럼 스크립틀릿 내에서 서블리셍 사용하던 코드 작성해서 자동으로 forward할 수 있음
	<%
		request.setAttribute("name","유재석");
		request.getRequestDispatcher("testForward.jsp").forward(request,response);
	%>
	
	--%>
	<jsp:forward page="testForward.jsp">
		<jsp:param value="olive" name="name"/>
	</jsp:forward>
</body>
</html>