<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>전달된 request 객체에서 저장된 정보 출력하기</h2>
	<%
		String name = (String)request.getAttribute("name");
		int age = (Integer)request.getAttribute("age");
		String phone = (String)request.getAttribute("phone");
	
	%>
	name : <%=name %><br>
	age : <%=age %><br>
	phone : <%=phone %><br><hr>
	<h3>EL을 이용해서 보다 쉽게 request, session 객체에 저장된 값을 출력할 수 있음</h3>
	<p>
		- EL은 get을 통해 값을 빼올 필요없이 키값만 제시해서 바로 접근이 가능함<br>
		- 내부적으로 해당 객체의 getXXX을 자동적으로 활용하여 해당 키에 저장된 값을 읽어오는 구조<br>
		- EL은 request, session 등 jsp 내장 객체를 구분하지 않아도 자동으로 입력된 속성명(key)을 검색하여 해당 속성이 존재할 경우 값(value)를 가져옴
	</p>
	<h4 style="color : red">****아래에서 직접 테스트 해보기</h4>
	name : ${requestScope.name}<br>
	age : ${requestScope.age}<br>
	phone : ${requestScope.phone}<br>
	<h4 style="color : red">****키값을 사용하지 않을 경우</h4>
	name : ${name}<br>
	age : ${age}<br>
	phone : ${phone}<br><hr>
	<h2>items 이름으로 저장된 리스트 정보 출력하기</h2>
	<%
		ArrayList items = (ArrayList)request.getAttribute("items");
	%>
	<%for(int i=0;i<items.size();i++){%>
		- <%= items.get(i) %><br>
	<%} %>
	<h4 style="color : red">****el을 사용할 경우</h4>
	- ${items[0]}<br>
	- ${items[1]}<br>
	- ${items[2]} 
	<hr>
</body>
</html>