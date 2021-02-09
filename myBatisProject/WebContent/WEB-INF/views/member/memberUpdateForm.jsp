<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#insertForm input[type=text], #insertForm input[type=password],
	#insertForm input[type=email] {
	width: 200px;
	margin: 5px;
}

#insertForm tr>td:nth-child(1) {
	text-align: right;
}
</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />

	<div class="outer">
		<br>
		<h1 align="center">마이페이지(회원정보 수정)</h1>
		<br>

		<form id="updateForm" action="update.me" method="post">
			<table align="center">
				<tr>
					<td>* NAME :</td>
					<td><input type="text" name="userName" value = '${sessionScope.loginUser.userName}' readonly></td>
				</tr>
				<tr>
					<td>EMAIL :</td>
					<td><input type="email" name="email" value = '${sessionScope.loginUser.email}'></td>
				</tr>
				<tr>
					<td>BIRTHDAY :</td>
					<td><input type="text" name="birthday" value='${sessionScope.loginUser.birthday}'></td>
				</tr>
				<tr>
					<td>GENDER :
					</td>
					<c:choose>
						<c:when test="${sessionScope.loginUser.gender eq 'M'}">
							<td><input type="radio" name="gender" value="M" checked> 남 <input
						type="radio" name="gender" value="F"> 여</td>
						</c:when>
						<c:when test="${sessionScope.loginUser.gender eq 'F'}">
							<td><input type="radio" name="gender" value="M"> 남 <input
						type="radio" name="gender" value="F" checked> 여</td>
						</c:when>
					</c:choose>
				</tr>
				<tr>
					<td>PHONE :</td>
					<td><input type="text" name="phone" value='${sessionScope.loginUser.phone}'></td>
				</tr>
				<tr>
					<td>ADDRESS :</td>
					<td><input type="text" name="address" value='${sessionScope.loginUser.address}'></td>
				</tr>
			</table>

			<br>
			<div class="btns" align="center">
				<button type="reset">초기화</button>
				<button type="submit">수정하기</button>
				<a href="delete.me" style="color:white">회원 탈퇴</a>
			</div>
		</form>
		<br>
		<br>
	</div>


</body>
</html>