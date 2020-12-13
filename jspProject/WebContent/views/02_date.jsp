<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Date</title>
</head>
<body>
<%
	Date now = new Date();
	String date = String.format("%tF",now);
	String time = String.format("%tp %tT",now,now);
	String today = String.format("%tY년 %tm월 %td일%tA",now,now,now,now,now);
	

	/*
	format문자                                      설명
	-----------------------------------------------------------------
	%tF                          날짜를 yyyy-mm-dd 형식으로 포맷
	%tT                          시간을 HH:MM:SS 형식으로 포맷
	-----------------------------------------------------------------
	%tY                          4자리 년도만 출력
	%ty                          2자리 년도만 출력
	%tB                          월의 이름으로 출력(January, Feburary, March...)
	%tm                          월을 01, 02, 03...12로 출력
	%td                          일을 1 ~ 31로 출력
	%te                          %td와 같음
	%tA                          요일명 출력
	-----------------------------------------------------------------
	%tp                          오전 오후를 출력
	%tk                          시간을 0 ~ 23으로 출력
	%tl                          시간을 1 ~ 12로 출력
	%tM                          분을 00 ~ 59로 출력
	%tS                          초를 00 ~ 59로 출력
	-----------------------------------------------------------------
	
	*/
%>
	<ul style="list-style:decimal">
		<li>오늘의 날짜 : <%=date %></li>
		<li>현재시간: <%=time %></li>
		<li>오늘은 <span style="color:red;"><%=today %></span>입니다.</li>
	</ul>
</body>
</html>