<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.el.model.vo.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>기존 방식으로 request객체로부터 값 불러오기</h2>
	<%Member m = (Member)request.getAttribute("member"); %>
	이름 : <%=m.getName() %><br>
	나이 : <%=m.getAge() %><br>
	전번 : <%=m.getPhone() %><br>
	메일 : <%=m.getEmail() %><br><hr>
	<h2>EL을 활용해보기</h2>
	이름 : ${requestScope.member.name}<br>
	나이 : ${requestScope.member.age}<br>
	전번 : ${requestScope.member.phone}<br>
	메일 : ${requestScope.member.email}<br><hr>
	<h2>scope 선언 제외하고 값 불러오기</h2>
	이름 : ${member.name}<br>
	나이 : ${member.age}<br>
	전번 : ${member.phone}<br>
	메일 : ${member.email}<br><hr>
	
</body>
</html>