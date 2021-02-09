<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>parameter 값 받아서 출력하기</h2>
	<p>
		해당 페이지 요청시에 전달값은 내무적으로 param 영역에 저장되어 있음<br>
		param : d해당 페이지 요청시 전달된 파라미터 값을 받아올 때 사용<br>
		paramValues : 해당 페이지 요청시 전달된 파라미터 값들을 배열로 받아올 때 사용
	</p>
	<%
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		String[] no = request.getParameterValues("no"); //배열을 받을 때는 getParameterValues 메소드를 호출
		String option = request.getParameter("option");
	%>
	상품명 : <%=name %><br>
	가격 : <%=price %><br>
	제품번호 : <%=no[0] %>와 <%=no[1]%>의 두가지 존재<br>
	옵션 : <%=name %><br><hr>
	
	상품명 : ${param.name}<br> 
	가격 : ${param.price}<br> 
	제품번호 : ${paramValues.no['0']} 와  ${paramValues.no['1']}  <br> 
	옵션 : ${param.name}<br> 
	
</body>
</html>