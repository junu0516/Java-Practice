# 자바 연습한 코드 저장
### 1. JDBC 활용하여 자바 파일과 db 연동하기  
*  JDBC의 동작과 사용하는 객체의 용도는 [여기](https://junu0516.tistory.com/45?category=926619)를 보도록 하자   
* __단순 statement 활용__ : [코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/01_Statement/src/com/kh/member/model/dao/MemberDAO.java)   

* __PreparedStatement 활용__ : [코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/02_PreparedStatement/src/com/kh/member/model/dao/MemberDAO.java)   
(PreparedStatement를 활용하면 쿼리의 반복적인 수행시 DB에 적은 부하를 주기 때문에 더 좋다.)   

* __싱글톤 패턴을 활용해보기__ : [JDBCTemplate 코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/03_Singleton_Service/src/com/kh/common/JDBCTemplate.java), [dao 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/03_Singleton_Service/src/com/kh/member/model/dao/MemberDAO.java)   
(JDBCTemplate의 정적 변수/메소드를 만들어두면 커넥션 객체를 하나만 만들면 되기 때문에 메모리의 낭비를 줄일 수 있음)   

* __properties 파일 활용하기__ : [properties 보기](https://github.com/junu0516/java-practice/tree/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/04_Properties/resources), [JDBCTemplate 코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/04_Properties/src/com/kh/common/JDBCTemplate.java), [dao 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/04_Properties/src/com/kh/member/model/dao/MemberDAO.java)     
(싱글톤 패턴의 적용에서 더 나아가 properties 파일을 통해 동적으로 db 연결정보를 불러오기 때문에, 정보가 변경될 때마다 일일히 코드를 바꾸지 않아도 됨)   

### 2. 서블릿을 활용하여 html문서와 자바 파일 서로 연동하기   
* __매핑한 URL로 이동하도록 form 생성__ : [html파일 보기](https://github.com/junu0516/java-practice/blob/main/servletExample/WebContent/views/testServlet.html)   
* __서블릿 객체에서 요청 받아서 동적인 웹페이지 작성__ : [코드 보기](https://github.com/junu0516/java-practice/blob/main/servletExample/src/com/serverlets/TestServlet.java)      
* 서블릿 컨테이너와 웹서버가 요청을 처리하는 과정은 [여기](https://junu0516.tistory.com/65)에 저장해두었음!   

### 3. JSP 활용해서 화면 구현과 비즈니스 로직을 분리하기
* __JSP파일을 만들면 html코드 내에 자바 코드를 사용할 수 있기 때문에, 서블릿에서 html을 구현했던 것보다 덜 복잡하다는 이점이 있음__  
* __따라서 서블릿에서는 비즈니스 로직에 집중하고, 응답화면의 구현은 JSP에서 작성하도록 하여 보다 효율적으로 작업이 가능함__   
* [01_sum.jsp](https://github.com/junu0516/java-practice/blob/main/jspProject/WebContent/views/01_sum.jsp)에서 <% %> 내에 1~10까지 합을 구하는 자바 코드를 작성 후, 결과값을 담은 total 변수를 html 문서에 출력되도록 함   
* [02_date.jsp](https://github.com/junu0516/java-practice/blob/main/jspProject/WebContent/views/02_date.jsp)에서는 Date객체를 원하는 형식으로 변형한 today 문자열을 그대로 html문서에 나타나도록 함   
* [03.menu.jsp](https://github.com/junu0516/java-practice/blob/main/jspProject/WebContent/views/03_menu.jsp)에서는 선택한 옵션의 value를 전송(request)하여, [MenuOrderServlet.java](https://github.com/junu0516/java-practice/blob/main/jspProject/src/com/kh/controller/MenuOrderServlet.java)에서 선택한 옵션 기반으로 결과값을 도출하여 이를 다시 jsp로 위임함   
* 위임받은 결과값은 [04.menuResult.jsp](https://github.com/junu0516/java-practice/blob/main/jspProject/WebContent/views/04_menuResult.jsp)에서 html 문서에 나타나도록 함   
* 요청(request)를 위임할 때 서블릿 파일에서 사용하는 객체가 __RequestDispatcher__ 임. HttpServletRequest 객체의 .getRequestDispatcher(); 메소드의 매개변수로 위임받을 jsp문서의 경로를 입력한 후, __.forward()__ 메소드의 매개변수로 HttpServletRequest, HttpServletResponse 객체를 담아 보내주면 됨   
* 따라서 이러한 방식으로 비즈니스 로직과 화면 구현을 각기 나눠서 처리할 수 있음   

### 4. 간단한 CRUD 게시판 만들어보기
- __로그인, 로그아웃 기능 구현__
    - [메인화면](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/WebContent/views/common/menubar.jsp)에서 로그인, 회원가입으로 이동   
    - 로그인 버튼을 누르면 서버로 [longin.me](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/member/controller/LoginServlet.java)로 매핑된 서블릿 객체로 요청을 보냄   
    - [LoginServlet.java](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/member/controller/LoginServlet.java)에서는 JDBC 연동을 통해 로그인한 회원 정보를 담을 loginUser 객체를 생성 후, 이를 세션에 반영하여 redirect함   
    - 로그아웃의 경우 메인화면에서 [logOut.me](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/member/controller/LogoutServlet.java)로 매핑된 [LogOutServlet.java](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/member/controller/LogoutServlet.java)에서 현재 세션에 저장된 loginUser 객체 정보를 모두 없앤 후 redirect하는 방식으로 기능 구현   
   
- __회원가입 및 회원정보 수정, 탈퇴 기능 구현__   
    - __크게 jsp 화면에서 양식 입력 --> 서블릿으로 request 전송 --> 서블릿에서 비즈니스 로직 처리 후, 이를 다른 jsp 파일로 forward하거나, 기존 context redirect의 방식임__   
    - __결국 위에서 언급한대로 비즈니스 로직을 서블릿에서 처리하고, 이와 관련해서 만들어서 보여줄 동적 페이지는 jsp 파일로 request, response를 forward 하여 분담하는 것__
    - 메인화면에서 [MemberEnrollFormServlet](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/member/controller/MemberEnrollFormServlet.java)으로 이동 후, [memberEnrollForm.jsp](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/WebContent/views/member/memberEnrollForm.jsp)으로 request와 response를 forward 해줌   
    - [memberEnrollForm.jsp](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/WebContent/views/member/memberEnrollForm.jsp)로 이동 후, 회원정보를 입력하여 [insert.me]로 매핑된 MemberEnrollServlet 객체로 요청을 보냄   
    - request객체로부터 입력받은 회원정보를 받아 새로운 Member 객체를 만들어 DB에 추가한 후 redirect함   
    - 회원정보 수정 및 탈퇴 역시 큰 틀에서는 회원가입과 동일하며, JDBC 연동시 사용하게 될 쿼리문에 따른 차이를 고려하여 기능을 구현함   
    - 회원정보 수정 서블릿 : [회원정보 변경](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/member/controller/MemberUpdateServlet.java), [비밀번호 변경 이동](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/member/controller/MemberEnrollFormServlet.java), [비밀번호 변경 양식 전송](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/member/controller/UpdatePwd.java)   
    - 회원정보 수정 jsp : [회원정보 변경](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/WebContent/views/common/myPage.jsp), [비밀번호 변경](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/WebContent/views/member/pwdUpdateForm.jsp)   
    - 회원탈퇴 서블릿 : 로그아웃과 비슷하게 db에서 loginUser 멤버 객체 비활성화 후 세션에서 데이터 모두 삭제하는 로직([코드보기](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/member/controller/MemberDeleteServlet.java))   
    - 회원탈퇴 jsp : [회원정보 변경](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/WebContent/views/common/myPage.jsp) 페이지 하단의 deleteMember 함수를 통해 처리   

- __공지사항 등록, 수정, 삭제 기능 구현__   
    - __동일하게 jsp 파일로 구현된 화면에서 양식 입력 후 전송 --> 서블릿에서 request 받아서 비즈니스 로직 처리 --> 처리 후 다른 jsp 파일로 위임하여 새롭게 보여줄 화면 구현, 메인 화면으로 redirect__ 방식을 따름
    - 공지사항 조회 : 전체 목록 조회는 [NoticeListServlet](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/notice/controller/NoticeListServlet.java)에서  DB로부터 공지사항 리스트롤 받아와 [noticeListView.jsp](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/WebContent/views/notice/noticeListView.jsp)로 이를 위임하여 화면 구현, 게시글의 상세 조회는 [NoticeDetailServlet](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/notice/controller/NoticeDetailServlet.java) 서블릿에서 로직 처리 후 [noticeDetailView.jsp](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/WebContent/views/notice/noticeDetailView.jsp)로 위임하여 화면 구현   
    - 공지사항 등록 : [NoticeInsertFormServlet](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/notice/controller/NoticeInsertFormServlet.java) 서블릿에서 [noticeInsertForm.jsp](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/WebContent/views/notice/noticeInsertForm.jsp)으로 위임 후 [NoticeInsertServlet](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/notice/controller/NoticeInsertServlet.java)에서 새로운 글 DB에 추가하는 비즈니스 로직 처리 후 메인화면으로 redirect   
    - 공지사항 수정 : [NoticeUpdateFormServlet](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/notice/controller/NoticeUpdateFormServlet.java) 서블릿에서 수정할 Notice 객체를 DB에서 불러온 후 [noticeUpdateForm.jsp](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/WebContent/views/notice/noticeUpdateForm.jsp)로 위임, 게시글 수정 후 이를 [NoticeUpdateServlet](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/notice/controller/NoticeUpdateServlet.java)으로 보내 DB에서 해당 데이터를 수정 후 게시글 detail로 redirect     
    - 공지사항 삭제 : [NoticeDeleteServlet](https://github.com/junu0516/java-practice/blob/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/src/com/kh/notice/controller/NoticeDeleteServlet.java) 서블릿에서 전송받은 게시글 번호로 DB에서 해당 공지사항을 삭제 후 공지사항 메인으로 redirect   
  

