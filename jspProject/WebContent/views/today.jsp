<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date, java.text.SimpleDateFormat"%>
<%
	Date now = new Date();
	String today = String.format("%tY년 %tm월 %td일%tA",now,now,now,now,now);
	SimpleDateFormat sdf = new SimpleDateFormat("yy년MM월dd일EE요일");
	today = sdf.format(now);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=today%>
</body>
</html>