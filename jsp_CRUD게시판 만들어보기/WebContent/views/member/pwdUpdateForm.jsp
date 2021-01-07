<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Insert title here</title>
<script>
	var msg = "<%= msg %>";
	$(function(){
		if(msg != "null"){ //equals 메소드 제공 안 함
			alert(msg);
		}
		
		if(msg == "성공적으로 비밀번호를 변경하였습니다."){
			window.close();
		}
	});
</script>
</head>
<body>
	<b>비밀번호 변경</b>
	<br>
	
	<form id="updatePwdForm" action="<%= request.getContextPath() %>/updatePwd.me" method="post">
		<table>
			<tr>
				<td><label>현재 비밀번호</label>
				<td><input type="password" name="userPwd" id="userPwd"></td>
			</tr>
			<tr>
				<td><label>변경할 비밀번호</label></td>
				<td><input type="password" name="newPwd"></td>
			</tr>
			<tr>
				<td><label>변경할 비밀번호 확인</label></td>
				<td><input type="password" name="checkPwd"></td>
			</tr>
		</table>
		
		<br>
		<br>
		
		<div class="btns" align="center">
			<div id="updatePwdBtn" onclick="checkPwd();">변경하기</div>
		</div>
	</form>
	
	<script>
		function checkPwd(){
			
			var userPwd = $("#userPwd");
			var newPwd = $("input[name='newPwd']");
			var checkPwd = $("input[name='checkPwd']");
			
			if(userPwd.val().trim() == "" || newPwd.val().trim() == "" || checkPwd.val().trim() == ""){
				alert("비밀번호를 입력해주세요");
				return;
			}
			
			if(newPwd.val() != checkPwd.val()){
				alert("비밀번호가 다릅니다.");
				checkPwd.val('');
				checkPwd.focus();
				return;
			} 
			
			$("#updatePwdForm").submit();
		}
		
	
	</script>
</body>
</html>