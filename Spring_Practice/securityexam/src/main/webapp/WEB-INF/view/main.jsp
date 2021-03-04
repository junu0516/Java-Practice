<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Spring Security 실습 메인 페이지</h1>
	<c:if test="${userId != null }">
		현재 ${userId} 님이 로그인한 상태입니다.<br>
		<a href="logout">로그아웃</a>
		<a href="members/memberinfo">회원정보 조회</a>
	</c:if>
	<c:if test="${userId == null }">
		<a href="loginform">로그인</a>
		<a href="members/joinform">회원가입</a>
	</c:if>
</body>
</html>