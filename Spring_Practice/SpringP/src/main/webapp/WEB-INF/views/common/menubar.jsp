<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> 
<style>
    div{box-sizing: border-box;}
    #header{
        width:80%;
        height:100px;
        padding-top: 20px;
        margin:auto;
    }
    #header>div{width:100%; margin-bottom:10px}
    #header_1{height:40%;}
    #header_2{height:60%;}

    #header_1>div{
        height:100%;
        float:left;
    }
    #header_1_left{width:30%;position:relative;}
    #header_1_center{width:20%;}
    #header_1_right{width:40%;}

    #header_1_left>img{height:80%;position:absolute;margin:auto;top:0;bottom:0;right: 0;left:0;}
    #header_1_right{text-align: center; line-height:35px; font-size: 12px; text-indent: 35px;}
    #header_1_right>a{margin: 5px;}
    #header_1_right>a:hover{cursor: pointer;}
    
    #header_2>ul{width:100%; height:100%; list-style-type: none; margin: auto; padding:0;}
    #header_2>ul>li{float:left; width:25%; height:100%; line-height: 55px; text-align:center;}
    #header_2>ul>li a{text-decoration: none; color:black; font-size: 18px; font-weight: 900;}

    #header_2{border-top:1px solid lightgray}

    #header a{text-decoration:none; color:black}
    
    /* 세부 페이지마다 공통적으로 유지할 style */
    .content{
        background-color:rgb(247, 245, 245);
        width:80%;
        margin:auto;
    }
    .innerOuter{
        border:1px solid lightgray;
        width:80%;
        margin:auto;
        padding:5% 10%;
        background:white;
    }

</style>
</head>
<body>
	<c:if test="${ !empty msg }">
		<script>
			alert("${msg}");
		</script>
		<c:remove var="msg" scope="session"/>
	</c:if>
	<div id="header">
        <div id="header_1">
            <div id="header_1_left">
                <img src="https://www.iei.or.kr/resources/images/common/top_logo_s.jpg" alt="" onclick="location.href='${ pageContext.servletContext.contextPath }';">
            </div>
            <div id="header_1_center"></div>
            <div id="header_1_right">
            
            	
                <!-- 로그인 전 -->
                <c:if test="${ empty sessionScope.loginUser }">
	                <a href="enrollForm.me">회원가입</a> | 
	                <a data-toggle="modal" data-target="#loginModal">로그인</a> <!-- 모달의 원리 : 이 버튼 클릭시 data-target에 제시되어있는 해당 아이디의 div가 보이는 거임 -->
                </c:if>
                
                <!-- 로그인 후  -->
                <c:if test="${ !empty sessionScope.loginUser }">
	                <label>${sessionScope.loginUser.userName}님 환영합니다</label> &nbsp;&nbsp;
	                <a href="myPage.me">마이페이지</a>
	                <a href="logout.me">로그아웃</a>
                </c:if>  
                
               
            </div>
        </div>
      
        <div id="header_2">
            <ul>
                <li><a href="${ pageContext.servletContext.contextPath }">HOME</a></li>
                <li><a href="">공지사항</a></li>
                <li><a href="list.bo">자유게시판</a></li>
            </ul>
        </div>
    </div>

    <!-- 로그인 클릭 시 뜨는 모달  -->
    <div class="modal fade" id="loginModal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Login</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button> 
            </div>

            <form action="login.me" method="post">
                <!-- Modal Body -->
                <div class="modal-body">
                    <label for="userId" class="mr-sm-2">ID :</label>
                    <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Enter ID" id="userId" name="userId"> <br>
                    <label for="userPwd" class="mr-sm-2">Password:</label>
                    <input type="password" class="form-control mb-2 mr-sm-2" placeholder="Enter password" id="userPwd" name="userPwd">
                </div>
                
                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">로그인</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
                </div>
            </form>
            </div>
        </div>
    </div>

    <br clear="both">
<!-- 
모달 팝업- 창이 종료되기 전에 다른 작업못하는것 (메세지박스), 모달리스 - 제어권을 독점하지 않아 다른 작업을 할수있다.(찾기상자)
웹사이트 본문에서 별도의 윈도우가 필요한 경우에 띄우는 팝업 윈도우 같은 것
data- target의 value : 버튼 클릭 시 모달로 띄우고자 하는 id값을 #을 붙여서 연결
data- toggle의 value : 모달기능을 수행하겠다는 의미
모달창 영역에 버튼태그의 data : dismiss 속성을 적용하면 모달을 닫게 하는 기능을 적용한다는 의미
data- dismiss="modal" : 모달을 닫는 기능을 갖고 있다.

--------------------------------------------------------------------------------

- m : Margin을 의미
- p : Padding을 의미

- t : top을 의미 
- b : bottom을 의미
- l : left을 의미
- r : right을 의미
- x : x축 -> left , right을 의미  (mx : 양쪽옆마진)
- y : y축 -> top , bottom을 의미  (my : 위아래마진)

- xs : extra small
- sm : small
- md : medium
- lg : large
- xl : extra large


.form-control
 – 부트스트랩에서 값을 입력받는 input, textarea, select는 모두 .form-control 클래스 선택자를 적용한다. 
   그러면 모든 input, textarea 등 width:100%, height:34px, padding 상하:6px, 좌우:12px, border-radius:4px이 적용된다.
 

 .form-group
 – 입력받는 부분은 일반적으로 label, input으로 구성되는데 이 부분에 .form-group 클래스 선택자를 적용한다. 
   그러면 하단 15px의 마진값을 설정하여 공간적인 여백이 생기게 된다

 

 -->

</body>
</html>