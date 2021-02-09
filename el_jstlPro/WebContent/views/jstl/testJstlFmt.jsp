<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">JSTL Fmt Library</h1>
	<hr>
	<h2>fmt:formatNumber 태그 : 숫자에 포맷을 적용하는 태그</h2>
	<pre>
		숫자의 데이터 포맷 지정<br>
		- 표현하고자 하는 숫자의 형식을 통화기호나 % 등 원하는 쓰임에 맞춰 지정할 수 있음
	</pre>
	
	숫자 그대로 출력 : <fmt:formatNumber value="123456789" groupingUsed="false"/><br>
	1000단위로 ,로 구분하여 출력 : <fmt:formatNumber value="123456789" groupingUsed="true"/><br>
	
	#을 이용하여 지정 : <fmt:formatNumber value="1.23" pattern="#.###"/><br>
	0을 이용하여 지정 : <fmt:formatNumber value="1.23" pattern="0.000"/><br>
	percent : <fmt:formatNumber value="0.65" type="percent"/><br>
	currency: <fmt:formatNumber value="50000" type="currency"/><br>
	currency: <fmt:formatNumber value="50000" type="currency" currencySymbol="$"/><br>
	<hr>
	<h2>fmt:formatDate 태그 : 날짜와 시간에 포맷을 적용하는 태그</h2>
	<pre>
		value 속성에는 java.util.Date 객체를 사용해야 함
	</pre>
	<c:set var="today" value="<%=new java.util.Date() %>"/>
	오늘 날짜 : <fmt:formatDate value="${today}" type="date"/><br>
	현재 시간 : <fmt:formatDate value="${today}" type="time"/><br>
	현재 날짜와 시간 : <fmt:formatDate value="${today}" type="both"/><br>
	<hr>
	<h2>원하는 포맷으로 pattern 적용</h2>
	현재 날짜 : <fmt:formatDate value="${today}" type="date" pattern="yyyy/MM/dd (E)"/>
	현재 시간 : <fmt:formatDate value="${today}" type="time" pattern="(a) hh:mm:ss"/>
	<hr>
	<h2>날짜와 시간에 제공되는 포맷을 적용하는 경우</h2>
	[short] : <fmt:formatDate value="${today}" type="both" dateStyle="short" timeStyle="short"/><br>
	[medium] : <fmt:formatDate value="${today}" type="both" dateStyle="medium" timeStyle="medium"/><br>
	[long] : <fmt:formatDate value="${today}" type="both" dateStyle="long" timeStyle="long"/><br>
	[full] : <fmt:formatDate value="${today}" type="both" dateStyle="full" timeStyle="full"/><br>
	[default] : <fmt:formatDate value="${today}" type="both" dateStyle="default" timeStyle="default"/><br> <!-- default는 medium으로 나옴을 확인할 수 있음 -->
</body>
</html>