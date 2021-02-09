<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">JSTL Functions Library Test</h1>
	<h3>문자열처리에 관한 함수들이다.</h3>
	<h3>el 안에서 값 처리용으로 사용되거나 value 속성에 대입시 사용한다.</h3>
	
	<c:set var="str" value="How are you?"/>
	str : ${str}<br>
	you가 포함되어 있나 : ${fn:contains(str,'you')}<br> <!-- checks whether the first parameter contains the value of second parameter -->
	how가 포함되어 있나 : ${fn:contains(str,'how')}<br>
	<br>대소문자 구분 없이 포함여부를 확인하고자 할 경우에는 ${fn:containsIgnoreCase(str,'how')}<br>
	모두 대문자로 : ${fn:toUpperCase(str)}<br>
	모두 소문자로 : ${fn:toLowerCase(str)}<br>
	are의 위치는 : ${fn:indexOf(str,"are")}<br>
	How를 Where로 바꾸기 : ${fn:replace(str,"How","Where")}<br>
	문자열의 길이 : ${fn:length(str)}<br>
	are을 분리 추출 : ${fn:substring(str,4,7)}<br>
	<hr>
	<c:set var="arr" value="${fn:split(str,' ')}"/>
<!-- 
	<c:forEach items="${arr}" varStatus="st">
		<c:out value="${st.count} : ${arr[st.index]}"/><br>
	</c:forEach><br>
 -->	
	<c:out value="${fn:join(arr,' ')}"/>
	
</body>
</html>