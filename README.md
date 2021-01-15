```java
##자바 연습한 코드 저장 : 
- 📋 jdbc , jsp : Ctrl+F+_JDBC, JSP 연습한 코드 저장_ 으로 키워드 검색
- 📋 spring : Ctrl+F+_스프링 연습한 코드 저장_ 으로 키워드 검색
- 📋 spring-boot : Ctrl+F+_스프링부트 연습한 코드 저장_ 으로 키워드 검색
```   
* * *
# 💻 JDBC, JSP 연습한 코드 저장
### 1. JDBC 활용하여 자바 파일과 db 연동하기  
*  JDBC의 동작과 사용하는 객체의 용도는 [여기](https://junu0516.tistory.com/45?category=926619)를 보도록 하자   
* __단순 statement 활용__ : [코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/01_Statement/src/com/kh/member/model/dao/MemberDAO.java)   
(쿼리에 인자를 부여하지 않기 때문에 단일로 사용할 경우 더 빠르지만, 반복적인 사용시 매번 컴파일 해야 함)   

* __PreparedStatement 활용__ : [코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/02_PreparedStatement/src/com/kh/member/model/dao/MemberDAO.java)   
(PreparedStatement를 활용하면 쿼리에 인자를 부여하여 반복적인 수행시 한 번만 컴파일 하기 때문에 DB에 적은 부하를 주기 때문에 더 좋다.)   
(또한, SQL Injection 공격을 방지하는 효과도 있음)   

* __싱글톤 패턴을 활용해보기__ : [JDBCTemplate 코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/03_Singleton_Service/src/com/kh/common/JDBCTemplate.java), [dao 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/03_Singleton_Service/src/com/kh/member/model/dao/MemberDAO.java)   
(JDBCTemplate의 정적 변수/메소드를 만들어두면 커넥션 객체를 하나만 만들면 되기 때문에 메모리의 낭비를 줄일 수 있음)   

* __properties 파일 활용하기__ : [properties 보기](https://github.com/junu0516/java-practice/tree/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/04_Properties/resources), [JDBCTemplate 코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/04_Properties/src/com/kh/common/JDBCTemplate.java), [dao 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/04_Properties/src/com/kh/member/model/dao/MemberDAO.java)     
(싱글톤 패턴의 적용에서 더 나아가 properties 파일을 통해 동적으로 db 연결정보를 불러오기 때문에, 정보가 변경될 때마다 일일히 코드를 바꾸지 않아도 됨)   

### 2. 서블릿을 활용하여 html문서와 자바 파일 서로 연동하기   
* __매핑한 URL로 이동하도록 form 생성__ : [html파일 보기](https://github.com/junu0516/java-practice/blob/main/servletExample/WebContent/views/testServlet.html)   
* __서블릿 객체에서 요청 받아서 동적인 웹페이지 작성__ : [코드 보기](https://github.com/junu0516/java-practice/blob/main/servletExample/src/com/serverlets/TestServlet.java)      
* 서블릿 컨테이너(서블릿 객체의 생명주기를 관리)와 웹서버가 요청을 처리하는 과정은 [여기](https://junu0516.tistory.com/65)를 참고하자

### 3. JSP 활용해서 화면 구현과 비즈니스 로직을 분리하기
* __JSP파일을 만들면 html코드 내에 자바 코드를 사용할 수 있기 때문에, 서블릿에서 html을 구현했던 것보다 덜 복잡하다는 이점이 있음__  
* __따라서 서블릿에서는 비즈니스 로직에 집중하고, 응답화면의 구현은 JSP에서 작성하도록 하여 보다 효율적으로 작업이 가능함__   
* [01_sum.jsp](https://github.com/junu0516/java-practice/blob/main/jspProject/WebContent/views/01_sum.jsp)에서 <% %> 내에 1~10까지 합을 구하는 자바 코드를 작성 후, 서블릿에서 결과값을 담은 total 변수를 jsp파일로 넘김   
* [02_date.jsp](https://github.com/junu0516/java-practice/blob/main/jspProject/WebContent/views/02_date.jsp)에서는 Date객체를 원하는 형식으로 변형한 today 문자열을 jsp 파일로 넘김
* [03.menu.jsp](https://github.com/junu0516/java-practice/blob/main/jspProject/WebContent/views/03_menu.jsp)에서는 선택한 옵션의 value를 전송(request)하여, [MenuOrderServlet.java](https://github.com/junu0516/java-practice/blob/main/jspProject/src/com/kh/controller/MenuOrderServlet.java)에서 선택한 옵션 기반으로 결과값을 도출하여 이를 다시 jsp로 위임함   
* 위임받은 결과값은 [04.menuResult.jsp](https://github.com/junu0516/java-practice/blob/main/jspProject/WebContent/views/04_menuResult.jsp)에서 jsp 파일로 넘김   
* 요청(request)를 위임할 때 서블릿 파일에서 사용하는 객체가 __RequestDispatcher__ 임. HttpServletRequest 객체의 .getRequestDispatcher(); 메소드의 매개변수로 위임받을 jsp문서의 경로를 입력한 후, __.forward()__ 메소드의 매개변수로 HttpServletRequest, HttpServletResponse 객체를 담아 보내주면 됨   
* 따라서 이러한 방식으로 비즈니스 로직과 화면 구현을 각기 나눠서 처리할 수 있음   

### 4. 자바 서블릿으로 쿠키, 세션 활용하기   
- 기존 요청의 정보를 지속적으로 유지해야할 필요가 있을때 http의 무상태성과 비연결 지향을 극복하기 위한 것
- 쿠키와 세션의 동작구조에 대한 설명은 [여기](https://junu0516.tistory.com/75)를 보자   

### 5. 간단한 CRUD 게시판 만들어보기    
- [기능별 플로우 확인](https://github.com/junu0516/java-practice/tree/main/jsp_CRUD%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0)    
- JDBC를 활용하여 서버와 DB 연동   
- 세션정보를 활용해서 로그인 상태 유지 및 로그아웃 기능 구현   
- __공지사항 등록, 수정, 삭제 기능 구현__   
    - 등록 후 리스트로 돌아갈 때 forward가 아닌 redirect가 적합   
    - 이유는 [여기](https://junu0516.tistory.com/76)에 정리해두었음   
- 댓글 작성 및 조회시 [Ajax 비동기 통신 방식](https://junu0516.tistory.com/78)을 활용  
 
* * *   

# 💻 스프링 연습한 코드 저장
### 1. DI, IoC
- Java Config를 활용한 설정을 위한 어노테이션
    - [_@Configuration_](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/diexam/src/main/java/com/junu/spring/diexam/ApplicationConfig.java) : 스프링 설정 클래스 선언
    - [_@Bean_](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/diexam/src/main/java/com/junu/spring/diexam/ApplicationConfig.java) : bean을 정의
    - [_@ComponentScan_](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/diexam/src/main/java/com/junu/spring/diexam/ApplicationConfig2.java) : @Controller, @Service, @Repository @Component 어노테이션이 붙은 클래스를 찾아 컨테이너에 등록
    - [_@Component_](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/diexam/src/main/java/com/junu/spring/diexam/Car.java) : 컴포넌트 스캔의 대상이 되는 어노테이션
    - [_@Autowired_](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/diexam/src/main/java/com/junu/spring/diexam/Car.java) : 주입 대상이 되는 bean을 컨테이너에 찾아 주입   

* * *   

# 💻 스프링부트 연습한 코드 저장
### 1. 스프링부트 환경 설정 및 JUnit테스트 코드 작성   
- [build.gradle](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/build.gradle)에서 Gradle 프로젝트를 스프링 부트 프로젝트로 환경 설정 / 롬복 설치
- [Application.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/main/java/com/junu/springboot/Application.java) : 프로젝트의 Main 클래스 기능 수행   
- [HelloResponseDtoTest.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/test/java/com/junu/springboot/web/dto/HelloResponseDtoTest.java)에서 [HelloResponseDto.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/main/java/com/junu/springboot/web/dto/HelloResponseDto.java) 테스트
    - @Getter, @RequiredArgsConstructor 어노테이션을 통해 getter메소드와 생성자가 올바르게 생성되는 지를 테스트할 수 있음    
    - assertThat(검증 대상).isEqualTo(비교 대상);를 통해 원하는 결과값과 일치하는 지를 확인
- [HelloControllerTest.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/test/java/com/junu/springboot/HelloControllerTest.java)에서 [HelloController.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/Practice1/src/main/java/com/junu/practice/springboot/web/HelloController.java)의 테스트 코드 수행
    - HelloController.java에서 클래스 선언 이전에 @RestController 어노테이션을 통해 JSON을 반환하는 컨트롤러로 만들어줌
    - @GetMapping(매핑할 url)을 통해 HTTP의 요청을 받을 수 있는 API를 만들어줄 수 있음
    - HelloControllerTest.java를 보면 @Test를 수행할 때, mvc.perform().andExpect() ....으로 테스트 코드를 작성하는데, 이는 mvc.perform()의 결과를 검증하는 것
    - status().isOk() : HTTP Header의 Status 검증 / content().string(비교대상) : 응답 본문의 내용을 검증.. 여기서는 Controller이 "hello"를 제대로 리턴하는 지를 검증
    - jsonPath("$.",is(비교대상)) : JSON의 응답값을 필드별로 검증하며 $를 기준으로 필드명 암시  

### 2. CRUD 게시판 만들기    
- [PostsRepositoryTest.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/test/java/com/junu/springboot/domain/posts/PostsRepositoryTest.java)에서 JUnit 테스트 코드 오류 원인 찾아야 함(2021.1.13)   
- [PostsRepository.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/main/java/com/junu/springboot/domain/posts/PostsRepository.java) : JpaRepository 인터페이스 생성
- [Posts.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/main/java/com/junu/springboot/domain/posts/Posts.java) : Entity 클래스
#### 등록 API 생성(2021.01.15)
- [PostsApiController.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/main/java/com/junu/springboot/web/PostsApiController.java) : Controller에서 API 요청을 받는 역할 수행
- [PostsService.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/main/java/com/junu/springboot/service/posts/PostsService.java) : 트랜젝션, 도메인 기능 간의 순서 보장
- [PostsSaveRequestDto.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/main/java/com/junu/springboot/web/dto/PostsSaveRequestDto.java) : Dto 클래스로 Request 데이터를 받는 역할 수행
- Entity와 Dto는 서로 유사하지만, Entity는 비즈니스 로직을 처리하기 때문에 Request/Response 데이터를 주고 받으며 자주 변경이 필요하기 때문에 Dto 클래스로 활용하기 적합하지 못함... 그래서 Dto 클래스를 또하나 만드는 것




