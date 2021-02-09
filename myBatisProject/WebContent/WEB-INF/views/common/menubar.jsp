<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.login-area {
	height: 100px;
}

#login-btn {
	height: 50px;
}

.nav-area {
	background: black;
	height: 50px;
	color: white;
}

.menu {
	display: table-cell;
	width: 250px;
	height: 50px;
	text-align: center;
	vertical-align: middle;
	font-size: 20px;
	font-weight: bold;
}

.menu:hover {
	cursor: pointer;
	background: darkgray;
}

.login-area a {
	text-decoration: none;
	color: black;
	font-size: 0.8em;
}

.outer {
	width: 900px;
	height: auto;
	background: black;
	color: white;
	margin: auto;
	margin-top: 50px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<h1 align="center">Welcome to MyBatis World!!</h1>
	<br>

	<!--  1. 회원 관련 서비스   -->
	<div class="login-area">
		<c:if test="${ empty sessionScope.loginUser }">
			<%--		<%if(loginUser == null){ %> --%>
			<form action="login.me" method="post">
				<table align="center">
					<tr>
						<td>아이디 :</td>
						<td><input type="text" name="userId"></td>
						<td rowspan="2"><button type="submit" id="login-btn">로그인</button></td>
					</tr>
					<tr>
						<td>비밀번호 :</td>
						<td><input type="password" name="userPwd"></td>
					</tr>
					<tr>
						<td colspan="3" align="center"><a href="enrollForm.me">회원가입</a>
							<a href="">아이디/비번 찾기</a></td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${ !empty sessionScope.loginUser }">
			<table align="right">
				<tr>
					<td colspan="2">
						<h3>${ sessionScope.loginUser.userName }님 환영합니다.</h3>
					</td>
				</tr>
				<tr>
					<td><button onclick="location.href='updateForm.me';">마이페이지</button></td>
					<td><button onclick="location.href='logout.me';">로그아웃</button></td>
				</tr>
			</table>
		</c:if>
	</div>

	<!-- ------------------------- 2. 게시물 관련 서비스 ---------------------- -->
	<div class="nav-area" align="center">
		<div class="menu"
			onclick="location.href='${pageContext.servletContext.contextPath }';">HOME</div>
		<div class="menu">공지사항</div>
		<div class="menu" onclick="location.href='list.bo';">게시판</div>
		<div class="menu">etc</div>
	</div>
</body>
</html>