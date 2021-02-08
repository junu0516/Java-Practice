<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>덧셈 결과</h1>
	숫자 1 : ${num1}<br>
	숫자 2 : ${num2}<br>
	덧셈 결과 : ${result}<br>
	<input type="button" onclick="goBack();" value="돌아가기"/>
	<input type="button" onclick="goMain();" value="메인으로"/>

	<script>
		function goBack(){
			location.href="/practice/test1";
		}	
		
		function goMain(){
			location.href="/practice";
		}	
	</script>
</body>
</html>