<%--directive page : 페이지 지시자 태그(페이지에 대한 설정) --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--JSP 주석 --%>
	<!--HTML 주석-->
	<%-- <%! %> 필드를 선언하는 태그 --%>
	<%-- ex <%! private int age=20; %>--%>
				
	<%--스크립틀릿 태그 내에는 자바 문법으로 코드 작성 가능 
		<% %> --> form of scriptlet
	--%>
	<%
		int total=0;
		for(int i=1;i<=10;i++){
			total += i;
		}	
	%>
	
	<h4>1부터 10까지의 합은 <span style="color:red; font-size:16pt;"><%=total%></span>입니다.</h4>
	<h4>1부터 10까지의 합은 <span style="color:red; font-size:16pt;"><%out.print(total); %></span>입니다.</h4>
</body>
</html>