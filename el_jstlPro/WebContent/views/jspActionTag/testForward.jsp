<%-- <%@page import = "com.kh.el.model.vo.Member" %> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">testForward.jsp 입니다.</h1>
	<%-- <h3 align="right"><%=request.getAttribute("name") %>님 환영합니다.</h3> --%>
	<h3 align="right"><%= request.getParameter("name") %>님 환영합니다.</h3> <!-- 태그의 value값 가져오는 것 -->
	<h3 align="right">${param.name}님 환영합니다.</h3> <!-- 좀 더 간단한 표기 -->
	
	<h2>jsp : useBean 활용하기</h2>
	<%-- 
	<%
		Member m = new Member();
		m.setName("유재석");
		m.setAge(20);
		m.setPhone("010-2926-0516");
		m.setEmail("mcYou.gmail.com");
	
	%>
	이름 : <%=m.getName() %><br>
	나이 : <%=m.getAge() %><br>
	전번 : <%=m.getPhone() %><br>
	메일 : <%=m.getEmail() %><br>
	--%>
	<jsp:useBean id="m" class="com.kh.el.model.vo.Member" scope="page"/>
	<jsp:setProperty property="name" name="m" value="유재석"/> <!-- name은 바로 위의 bean의 id로 적어줌 (객체의 변수명고 맞춘다고 생각하자)-->
	<jsp:setProperty property="age" name="m" value="20"/> 
	<jsp:setProperty property="phone" name="m" value="010-1111-2222"/>
	<jsp:setProperty property="email" name="m" value="mcYou@gmail.com"/> 
	
	이름 : <jsp:getProperty property="name" name="m"/><br>
	나이 : <jsp:getProperty property="age" name="m"/><br>
	전번 : <jsp:getProperty property="phone" name="m"/><br>
	메일 : <jsp:getProperty property="email" name="m"/>
	
</body>
</html>