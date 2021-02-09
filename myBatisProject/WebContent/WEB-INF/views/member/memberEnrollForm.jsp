<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<h1 align="center">회원가입</h1>
		<br>

		<form id="insertForm" action="insert.me" method="post">
			<table align="center">
				<tr>
					<td>* ID :</td>
					<td><input type="text" name="userId" required></td>
				</tr>
				<tr>
					<td>* PWD :</td>
					<td><input type="password" name="userPwd" required></td>
				</tr>
				<tr>
					<td>* NAME :</td>
					<td><input type="text" name="userName" required></td>
				</tr>
				<tr>
					<td>EMAIL :</td>
					<td><input type="email" name="email"></td>
				</tr>
				<tr>
					<td>BIRTHDAY :</td>
					<td><input type="text" name="birthday" placeholder="생년월일(6자리)"></td>
				</tr>
				<tr>
					<td>GENDER :</td>
					<td><input type="radio" name="gender" value="M"> 남 <input
						type="radio" name="gender" value="F"> 여</td>
				</tr>
				<tr>
					<td>PHONE :</td>
					<td><input type="text" name="phone" placeholder="(-포함)"></td>
				</tr>
				<tr>
					<td>ADDRESS :</td>
					<td><input type="text" name="address"></td>
				</tr>
			</table>

			<br>
			<div class="btns" align="center">
				<button type="reset">초기화</button>
				<button type="submit">회원가입</button>
			</div>
		</form>
		<br>
		<br>
	</div>


</body>
</html>