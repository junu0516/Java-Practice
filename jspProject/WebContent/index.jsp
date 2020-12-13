<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSP</h1>
	<p>
		JSP란, HTML 형식내에서 자바 코드를 사용할 수 있는 "자바 언어"로<br>
		서블릿(java코드) 내에서 응답화면(html)을 구현했던 복잡함을 보다 간단하게 작성 및 해결할 수 있음<br>
		즉, JSP의 가장 큰 장점은 Servlet에서는 비즈니스 로직처리에만 집중하고 응답화면을<br>
		만들어내는 것은 JSP에서 작성하게끔 분리할 수 있음
	</p>
	<h1>JSP Elements 표현법</h1>
	<p>
		JSP 페이지에 자바코드를 직접 기술할 수 있게 해주는 기능
	</p>
	<ol>
		<li> 선언문 : &lt;%! 자바코드   %&gt; 
			멤버변수와 메소드를 선언하기 위해 사용 <br><br></li>
			
		<li> 스크립틀릿 : &lt;%  자바코드   %&gt; 
			JSP에서 자바코드를 사용하기 위해 사용<br><br></li>
			
		<li> 표현식(출력식) : &lt;%=  자바코드   %&gt;
			자바에서 작성한 값을 화면에 출력하기 위해 사용 </li>
	</ol>
	<hr>
	
	<h2>2. 지시어 (Directive) &lt;%@ page|include|taglib 속성="속성값" 속성="속성값" %&gt;</h2>
	<p>JSP page 전체에 영향을 미치는 정보를 기술 할 때 쓰임</p>
	
	<ol>
		<li>
			page 지시자 : 현재의 JSP 페이지를 컨테이너에서 처리하는데 필요한 각종 속성을 기술하는 부분으로 JSP 문서의 맨 앞에 위치
			<ul>
				<li>language : 사용할 스크립트 언어 유형을 지정</li>
				<li>contentType : 웹 브라우저가 받아 볼 페이지의 형식, 인코딩 방식 지정</li>
				<li>pageEncoding : JSP 파일에 기록된 자바코드의 인코딩 방식을 지정</li>
				<li>import : 자바의 import와 같은 의미</li>
				<li>errorPage / isErrorPage : 오류페이지 관리</li>
				<li>등등..</li>
			</ul>
		</li>
		<li>
			include 지시자 : jsp 파일에 또다른 jsp를 포함하고자 할 때 사용하는 지시자<br>
		</li>
		<li> taglib : JSP 기능을 좀더 확장할 수 있는 인자로  정의된 사용자 정의 태그를 가져와 사용할 수 있는 방법 제공 </li>	
	</ol>
	<hr>
	<h1>JSP 테스트</h1>
	<h3><a href="views/01_sum.jsp">테스트</a></h3>
	<h3><a href="views/02_date.jsp">테스트</a></h3>
	<h3><a href="views/03_menu.jsp">테스트</a></h3>	
</body>
</html>