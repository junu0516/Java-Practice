<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>requestScope와 sessionScope 테스트</h2><hr>
	<h3>requestScope 값 출력하기</h3>
	이름 : ${requestScope.member.name}<br>
	나이 : ${requestScope.member.age}<br>
	전번 : ${requestScope.member.phone}<br>
	메일 : ${requestScope.member.email}<br>
	<br>
	<h3>sessionScope 값 출력하기</h3>
	이름 : ${sessionScope.member.name}<br>
	나이 : ${sessionScope.member.age}<br>
	전번 : ${sessionScope.member.phone}<br>
	메일 : ${sessionScope.member.email}<br>
	<br>
	<h3>scope를 명시하지 않았을 경우에는?</h3>
	이름 : ${member.name}<br>
	나이 : ${member.age}<br>
	전번 : ${member.phone}<br>
	메일 : ${member.email}<br>
	<h4 style="color : red">우선순위가 requestScope에 있음을 확인할 수 있음</h4>
</body>
</html>