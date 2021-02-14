# 목차   
클릭하여 해당 항목으로 이동
- [JDBC 연습 코드](#jdbc와-jsp-연습한-코드-저장)   
- [MyBatis 연습 코드](#MyBatis-연습한-코드-저장)
- [스프링 연습 코드](#스프링-연습한-코드-저장)   
- [스프링부트 연습 코드](#스프링부트-연습한-코드-저장)
  
* * *
# JDBC와 JSP 연습한 코드 저장   
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
- __JSP파일을 만들면 html코드 내에 자바 코드를 사용할 수 있기 때문에, 서블릿에서 html을 구현했던 것보다 덜 복잡하다는 이점이 있음__  
- __따라서 서블릿에서는 비즈니스 로직에 집중하고, 응답화면의 구현은 JSP에서 작성하도록 하여 보다 효율적으로 작업이 가능함__   
- [01_sum.jsp](https://github.com/junu0516/java-practice/blob/main/jspProject/WebContent/views/01_sum.jsp), [02_date.jsp](https://github.com/junu0516/java-practice/blob/main/jspProject/WebContent/views/02_date.jsp) 
- [03.menu.jsp](https://github.com/junu0516/java-practice/blob/main/jspProject/WebContent/views/03_menu.jsp)에서는 선택한 옵션의 value를 전송(request)하여, [MenuOrderServlet.java](https://github.com/junu0516/java-practice/blob/main/jspProject/src/com/kh/controller/MenuOrderServlet.java)에서 선택한 옵션 기반으로 결과값을 도출하여 이를 다시 jsp로 위임함   
- 위임받은 결과값은 [04.menuResult.jsp](https://github.com/junu0516/java-practice/blob/main/jspProject/WebContent/views/04_menuResult.jsp)에서 jsp 파일로 넘김   
- 요청(request)를 위임할 때 서블릿 파일에서 사용하는 객체가 __`RequestDispatcher`__ 임
    - HttpServletRequest 객체의 .getRequestDispatcher(); 메소드의 매개변수로 위임받을 jsp문서의 경로를 입력한 후,
    -  __`.forward()`__ 메소드의 매개변수로 HttpServletRequest, HttpServletResponse 객체를 담아 보내주면 됨   
    - 따라서 이러한 방식으로 비즈니스 로직과 화면 구현을 각기 나눠서 처리할 수 있음   

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
([맨 위로](#목차))

* * *   

# MyBatis 연습한 코드 저장
([맨 위로](#목차))
```
- XML을 통해 Mapper 설정 파일을 사용함으로써 CRUD를 좀 더 편리하게 할 수 있음    
- mybatis-config.xml에서 DB 연결 설정 및 SQL 구문의 경로를 설정   
- mapper.xml에서 DB와의 연동이 필요한 패키지별로 쿼리문을 설정 및 매핑
```
    
### 1. resources 폴더에 xml 파일 추가
- [mybatis-config.xml](https://github.com/junu0516/Java-Practice/blob/main/myBatisProject/resources/mybatis-config.xml) : mybatis 라이브러리 적용 후 기본 설정 파일로 기능   
- __`configuration`__ 태그 내부에 설정할 내용들을 기술하는 것   
   
- __`typeAliases`__ : 객체 매핑시 사용할 자료형들의 별칭을 선언   
    - typeAlias 태그 내부에 __`type`__ 속성으로 클래스 정보를, __`alias`__ 속성으로 사용할 별칭을 입력   
   
- __`environments`__ : MyBatis와 연동시킬 DB 정보를 등록   
- 작성 예시   
```
<environments default="development">
    <environment id="development">
        <transactionManager type="JDBC" />
        <dataSource type="POOLED">
            <property name="driver" value="oracle.jdbc.driver.OracleDriver" />
            <property name="url" value="jdbc:oracle:thin:@localhost:1521:xe" />
            <property name="username" value="mybatis" />
            <property name="password" value="mybatis" />
        </dataSource>
    </environment>
</environments>
```
- __`dataSource`__ : type 속성을 POOLED로 둘 경우 커넥션 객체를 pool 영역에 저장 후 이를 재사용하는 것을 의미
    - 한 번 만들어두고 재사용하기 때문에 매번 커넥션 객체를 생성할 필요 없음    
- __`property`__ : 외부 파일 내용을 읽어들일 때 사용
    - 여기서는 JDBCTemplate 클래스 작성시 사용했는 드라이버 관련 정보를 입력
- __`mappers`__ : 사용하고자 하는 쿼리문들이 정의될 mapper xml 파일들을 등록   
   
### 2. mapper 파일 구성
- mybatis-config.xml에 등록한 mapper 파일을 동일하게 resources 폴더에 추가   
- [member-mapper.xml](https://github.com/junu0516/Java-Practice/blob/main/myBatisProject/resources/mappers/member-mapper.xml), [board-mapper.xml](https://github.com/junu0516/Java-Practice/blob/main/myBatisProject/resources/mappers/board-mapper.xml) : mapper 파일 예시
- member-mapper.xml을 예시로 살펴보자   

- __`resultMap`__ : type에 typeAlias로 설정한 객체 타입을 명시하며, id에는 쿼리문 실행 결과를 매핑시킬 아이디값을 입력
   - 기본키에 해당하는 태그만 __`id`__ 로 받고, 나머지는 __`result`__ 태그로 받아줌   
   - __`property`__ 속성에는 매핑시킬 객체 내부의 인스턴스 변수명을, __`column`__ 은 매핑시킬 테이블의 컬럼명을 명시
```
<resultMap type="Member" id="memberResultSet">
    <id property="userNo" column="USER_NO"/>
    <result property="userId" column="USER_ID"/>
    <result property="userPwd" column="USER_PWD"/>
    <result property="userName" column="USER_NAME"/>
    <result property="email" column="EMAIL"/>
    <result property="birthday" column="BIRTHDAY"/>
    <result property="gender" column="GENDER"/>
    <result property="phone" column="PHONE"/>
    <result property="address" column="ADDRESS"/>
    <result property="enrollDate" column="ENROLL_DATE"/>
    <result property="modifyDate" column="MODIFY_DATE"/>
    <result property="status" column="STATUS"/>
</resultMap>   
```   

- 쿼리문 입력시 select, insert, delete, update 네가지 태그를 통해 CRUD을 구현
    - __`parameterType`__ : 매핑시킬 객체타입을 속성값으로 명시
    - __`id`__ : 쿼리문의 실행 결과를 연동시킬 DAO 클래스의 메소드명에서 반환한 값을 명시
    - 객체와 매핑시킬 때는 __`resultMap`__ 으로 속성을 명시해야 함 / 그렇지 않을 경우에는 __`resultType`__ 으로 명시 후, 속성값으로 클래스 정보를 명시   
```  
<select id="loginMember"
          parameterType="Member"
          resultMap="memberResultSet">

<!--쿼리문 입력 -->

</select>   
   
<insert id="insertMember"
          parameterType="Member"><!-- resultType을 Integer로 명시하지 않아도 자동으로 int값을 리턴 -->

 <!--쿼리문 입력 -->
	
</insert>
	
<update id="updateMember"
           parameterType="Member">

<!-- 쿼리문 입력 -->

</update>
	
```  

### 3. mapper.xml을 통해 쿼리문 실행 후 매핑해보기
- 기존의 JDBC 사용 패턴 대로 Controller & VO(DTO) & Service & DAO 사용의 패턴을 따르되, DAO에서 처리하는 코드의 수가 획기적으로 줄어드는 것을 확인할 수 있음   
    - 쿼리문 작성 및 DB와의 연동 등을 모두 xml파일에서 대신 처리해주기 때문   
    
- 회원 정보의 연동을 예로 살펴보도록 하자   
    - [LoginServlet.java](https://github.com/junu0516/Java-Practice/blob/main/myBatisProject/src/com/kh/myBatis/member/controller/LoginServlet.java) : 컨트롤러 클래스(서블릿)   
    - [Member.java](https://github.com/junu0516/Java-Practice/blob/main/myBatisProject/src/com/kh/myBatis/member/model/vo/Member.java) : VO(DTO) 클래스 / [MemberServiceImpl.java](https://github.com/junu0516/Java-Practice/blob/main/myBatisProject/src/com/kh/myBatis/member/model/service/MemberServiceImpl.java) : Service 클래스(MemberService 인터페이스 구현한 클래스)
    - [MemberDao.java](https://github.com/junu0516/Java-Practice/blob/main/myBatisProject/src/com/kh/myBatis/member/model/dao/MemberDao.java) : 기존의 DAO와 달리 __`sqlSession`__ 객체로 필요한 정보만 넘겨주면, xml 파일일에서 알아서 연동 및 쿼리를 수행해서 VO 클래스에 정보를 담아주게 될 것
```  
loginUser = sqlSession.selectOne("memberMapper.loginMember",member);		
```
   
- [member-mapper.xml](https://github.com/junu0516/Java-Practice/blob/main/myBatisProject/resources/mappers/member-mapper.xml) : 위의 코드 실행시 memberMapper로 명시된 매퍼 파일 내에서 loginMember 이라는 id 속성으로 명시된 쿼리문 태그가 실행됨   
   
### 4. RowBounds를 활용하여 페이징 처리하기
- __`RowBounds`__ : MyBatis에서 기본적으로 제공하는 페이징 처리를 위한 클래스(구간을 적용하여, 구간 내에 있는 row만 추출)
    - 기존에는 페이징 처리시 이를 위한 정보를 쿼리문에 담아서 원하는 개수만큼의 row를 DB에서 받아왔음   
    - __`RowBounds`__ 를 활용할 경우 개수 정보만 매퍼로 넘기면 되기 때문에 더욱 편리함
    - 단, 내부 구조상 건너뛰어야 할 데이터도 분류하기 위해 일단 모든 데이터를 찾아 판별하므로 데이터의 크기가 방대할 경우에는 오히려 비효율적임   
   
- [BoardDao.java](https://github.com/junu0516/Java-Practice/blob/main/myBatisProject/src/com/kh/myBatis/board/model/dao/BoardDao.java) : Dao 클래스에서 아래와 같이 RowBounds 객체를 파라미터에 담아서 리턴   
    - RowBounds 객체 생성시 들어갈 매개변수는 아래와 같음
    - 건너뛸 데이터의 개수, 한 번에 보여줄 데이터의 최대 개수(limit)
```  
RowBounds rowBounds = new RowBounds(offset,pageInfo.getBoardLimit());	
```
 
([맨 위로](#목차))
* * *

# 스프링 연습한 코드 저장
([맨 위로](#목차))
### 1. DI, IoC
- 스프링 DI와 IoC의 원리에 대해서는 [여기](https://junu0516.tistory.com/87?category=941266)를 참고하자   

- Java Config를 활용한 설정을 위한 어노테이션
    - [_@Configuration_](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/diexam/src/main/java/com/junu/spring/diexam/ApplicationConfig.java) : 스프링 설정 클래스 선언
    - [_@Bean_](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/diexam/src/main/java/com/junu/spring/diexam/ApplicationConfig.java) : bean을 정의
    - [_@ComponentScan_](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/diexam/src/main/java/com/junu/spring/diexam/ApplicationConfig2.java) : @Controller, @Service, @Repository @Component 어노테이션이 붙은 클래스를 찾아 컨테이너에 등록
    - [_@Component_](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/diexam/src/main/java/com/junu/spring/diexam/Car.java) : 컴포넌트 스캔의 대상이 되는 어노테이션
    - [_@Autowired_](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/diexam/src/main/java/com/junu/spring/diexam/Car.java) : 주입 대상이 되는 bean을 컨테이너에 찾아 주입   
   
### 2. Spring JDBC 테스트
- [pom.xml](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/daoexam/pom.xml) : Spring 컨텍스트, Spring JDBC 종속성, mySQL 드라이버 추가   

- @Configuration 명시, 데이터 소스 사용을 위한 사전 설정
    - [DBConfig.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/daoexam/src/main/java/com/junu/spring/daoexam/config/DBConfig.java) : 데이터 소스 객체를 사용하기 위한 Bean 생성(DataSource 클래스)   
    - [ApplicationConfig.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/daoexam/src/main/java/com/junu/spring/daoexam/config/ApplicationConfig.java) : @Import 어노테이션에 DBConfig의 클래스 정보를 주입시킴   

- mySQL 드라이버 연동   
    - [DataSourceTest.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/daoexam/src/main/java/com/junu/spring/daoexam/main/DataSourceTest.java) : __`ApplicationContext`__ 인스턴스 생성시, AnnotationConfigApplicationContext의 매개변수로 ApplicationConfig.class만 명시해줘도 됨(DBConfig.class는 이미 주입시켰기 때문)
    - __`getBean()`__ 을 통해 DataSource 객체를 받은 후, db에 연결 시도(기존 JDBC 템플릿에서의 드라이버 연결과 동일)

### 3. SELECT, UPDATE, DELETE 쿼리문 실행
- [Role.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/daoexam/src/main/java/com/junu/spring/daoexam/dto/Role.java) : DTO 객체   

- [RoleDao.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/daoexam/src/main/java/com/junu/spring/daoexam/dao/RoleDao.java) : @Repository 어노테이션을 선언하여, DB와 데이터를 주고받는 저장소 역할을 수행(Data Access Object)
    - Spring 4.0부터 Bean 객체의 경우 @Autowired 없이도 의존성 주입이 가능   
    - __`RowMapper`__ : 각 테이블 행마다 resultSet 객체를 통해 매핑하는 것을 가능하게 하는 객체 / _BeanPropertyRowMapper_ : Bean Property를 담아주는 RowMapper 자동으로 생성
    - __`NamedParameterJdbcTemplate`__ : 바인딩시 문자열로 매핑시킴(결과값 담아주며 ?를 사용하지 않기 때문에 편리) / queryForObject, update, execute 메소드를 각각의 경우에 맞게 사용 
    - __`SqlParameterSource`__ : 매개변수로 받은 객체의 인스턴스 변수를 해당 클래스에서 보고 알아서 연동할 DB 테이블의 컬럼명과 매핑시킴 / 따라서 변수명 설정에 주의해야함(SQL : 대문자, Java : 소문자)
    - __`BeanPropertySqlParameterSource`__ : SqlParameterSource 클래스의 인스턴스로 생성된 것을 확인할 수 있음 / 빈 객체롤 Map 객체로 변환시키는 역할
    - __`Collections.singletonMap`__(변수명, 매핑시킬 변수) : 특정 값만 조회하는 쿼리문의 경우(ex. delete/update~ where 변수 = '';)에는 __`singletonMap`__ 을 통해 값을 하나만 넣어서 쓰는 것이 경제적   

- [RoleDaoSqls](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/daoexam/src/main/java/com/junu/spring/daoexam/dao/RoleDaoSqls.java) : 쿼리문을 static final 상수로 선언해서 따로 저장해두면 편함
    - 기존 JDBC에서는 ?로 매핑했지만, 이렇게 하면 ?가 많아질수록 복잡해지기 때문에 :roleId와 같이 이름으로 매핑

### 4. Spring MVC 사용하기
- [pom.xml](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/mvcexam/pom.xml) : spring-context, spring-webmvc dependency, Servlet, JSP, JSTL 종속성  추가    
    - 이후, 반드시 프로젝트의 properties에서 메이븐 기반으로 넣어준 dependency를 deploy해야 함   

- [WebMvcContextConfiguration.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/mvcexam/src/main/java/com/junu/webmvc/config/WebMvcContextConfiguration.java) : Dispatcher Servlet 클래스로 WebMvcConfiguration 클래스를 상속받음   
    - 해당 클래스는 기존의 servlet-context.xml 파일을 자바 클래스로 대체한 것
    - @EnableWebMvc로 기본적 설정은 자동으로 할 수 있지만, 이외의 설정이 필요할 경우에는 필요한 메소드를 오버라이딩 해야 함
    - __`addResourceHandlers`__ : 요청이 들어왔을 때, 특정 url 요청에 대해서 알맞은 디렉토리에서 찾도록 설정(그렇지 않을 경우에는 모든 요청을 매핑으로 찾으려 들기 때문에 오류가 발생할 수 있음)    
    - __`configureDefaultServletHandling`__ : DefaultServletHandler를 사용하도록 하는 메소드로 __`configurer.enable()`__ 메소드 사용시, 매핑정보가 없는 url 요청에 대해 이를 DefaultServletHandler가 처리하도록 함
        - (WAS의 DefaultServlet으로 요청을 넘겨, 해당 서블릿의 static 자원을 읽어들이는 것)
    - __`addViewControllers`__ : 특정 url에 대한 처리를 별도의 컨트롤러 클래스 작성 없이 매핑 가능하도록 하는 메소드
        - registry.addViewController("/").setViewName("main"); : /로 들어온 url 요청에 대해 main이라는 view name을 찾아 보여주겠다는 것
    - __`InternalResourceViewResolver`__ : @Bean 객체를 주입한 것으로, 위의 view name만 가지고 당연히 jsp 파일을 찾을 수 없기 때문에, 여기서 구체적으로 어떤 정보&경로를 가진 파일인지 찾는 것
        - __`resolver.setPrefix`__(경로), __`resolver.setSuffix`__(파일 확장자) 를 입력 하여 view name과 결합   

- [web.xml](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/mvcexam/src/main/webapp/WEB-INF/web.xml) : Dispatcher 서블릿이 FrontController로 기능하도록 서블릿 매핑   

- Dispatcher Servlet이 요청 처리를 위임할 컨트롤러 클래스 생성
    - @Controller 어노테이션을 명시하여 해당 클래스가 컨트롤러 클래스임을 나타내야 함
    - [plusForm.jsp](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/mvcexam/src/main/webapp/WEB-INF/views/plusForm.jsp) :  덧셈 연산을 수행할 두 숫자를 입력하여 POST 방식(/plus)으로 요청을 보내는 view
    - [plusResult.jsp](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/mvcexam/src/main/webapp/WEB-INF/views/plusResult.jsp) : 요청 처리 후, 연산 결과를 보여줄 view

    - [PlusController.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/mvcexam/src/main/java/com/junu/webmvc/controller/PlusController.java) : Dispatcher Servlet으로부터 요청 처리를 위임받은 컨트롤러 클래스
        - __`@GetMapping`__ , __`@PostMapping`__ : 각각 GET/POSt방식으로 들어온 요청을 처리할 것임을 의미
        - plusForm() 메소드는 /plusForm url을 통해 들어온 요청을 처리하며, 처리 결과로 plusForm.jsp 파일의 이름을 리턴(view의 이름을 리턴하는 것)
        - plus() 메소드는 /plus url을 통해 들어온 요청을 처리하며, ModelMap 객체를 통해 입력받은 변수와, 연산 처리 결과를 자동으로 DTO 클래스에 매핑시킨 후 위와 동일하게 plusResult라는 view name을 리턴

    - [PlusResult.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/mvcexam/src/main/java/com/junu/webmvc/dto/PlusResult.java) : DTO 클래스

- WEB API 사용을 위한 컨트롤러 설정   
    - @Controller 어노테이션과 @ResponseBody 어노테이션을 같이 사용하거나, 최신 버전일 경우에는 @RestController 어노테이션 하나만 명시
    - [PlusApiController](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/mvcexam/src/main/java/com/junu/webmvc/controller/PlusApiController.java) : API 사용을 위한 컨트롤러 클래스 생성
        -  __`@ResponseBody`__ 어노테이션을 명시할 경우, 컨트롤러 메소드는 뷰 이름에 해당하는 문자열이 아닌 객체를 반환할 수 있음
        -  다시 말해 리턴할 객체를 출력하라고 하는 것을 의미    
    - [PlusResult.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/mvcexam/src/main/java/com/junu/webmvc/dto/PlusResult.java) : dto 클래스
    - [pom.xml](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/mvcexam/pom.xml)에 반드시 객체<->JSON 간 변환이 가능하도록 라이브러리를 추가 : MessageConverter가 존재해야 DispatcherServlet이 제대로 변환할 수 있기 때문


### 5. 실습 : MVC2 모델을 적용한 방명록 사이트 만들어보기
- 라이브러리 추가, 기타 설정 : [pom.xml](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/guestbook/pom.xml), [org.eclipse.wst.common.project.facet.core.xml](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/guestbook/.settings/org.eclipse.wst.common.project.facet.core.xml)
- Config 클래스 생성 : [DBConfig.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/guestbook/src/main/java/com/junu/spring/guestbook/config/DBConfig.java), [ApplicationConfig.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/guestbook/src/main/java/com/junu/spring/guestbook/config/ApplicationConfig.java)   
- Dispatcher Servlet 클래스 생성 : [WebMvcContextConfiguration.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/guestbook/src/main/java/com/junu/spring/guestbook/config/WebMvcContextConfiguration.java)
- 초기 서블릿 매핑을 위한 설정 : [web.xml](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/guestbook/src/main/webapp/WEB-INF/web.xml)

- 개별 서블릿이 될 컨트롤러 클래스 생성 : [GuestbookController.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/guestbook/src/main/java/com/junu/spring/guestbook/controller/GuestbookController.java) / 직접적인 비즈니스 로직을 처리   

- Repository Layer 완성 : DTO, DAO 클래스 생성 및 DB 테이블 생성
    - DTO : [Guestbook.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/guestbook/src/main/java/com/junu/spring/guestbook/dto/Guestbook.java), [Log.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/guestbook/src/main/java/com/junu/spring/guestbook/dto/Log.java)   
    - DAO : [GuestbookDaoSqls.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/guestbook/src/main/java/com/junu/spring/guestbook/dao/GuestbookDaoSqls.java), [GuestbookDao.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/guestbook/src/main/java/com/junu/spring/guestbook/dao/GuestbookDao.java), [LogDao.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/guestbook/src/main/java/com/junu/spring/guestbook/dao/LogDao.java)   
    - 테이블 생성 : [ddl 쿼리문](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/guestbook/sql.txt)

### 6. @RestController을 통해 Web API 작성해보기
- REST API에 대한 설명은 [여기](https://junu0516.tistory.com/94?category=933252)의 포스팅을 참고하도록 하자
- [pom.xml](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/mvcexam/pom.xml) : com.fasterxml.jackson.core 라이브러리를 설치해야 자바 객체<->JSON 간 변환이 가능해짐
-  [GuestbookApiController](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/guestbook/src/main/java/com/junu/spring/guestbook/controller/GuestbookApiController.java) : 클라이언트 요청시 JSON 데이터를 보내기 위한 컨트롤러 클래스
    - __`@RestController`__ 어노테이션을 명시
    - __`@RequestMapping`__ 어노테이션의 path값을 명시했기 때문에, __`@GetMapping`__ 과 __`@PostMapping`__ 메소드 구현시 따로 path를 입력해줄 필요 없음

- __`@GetMapping`__ : @RequestMapping에서 명시해준 /guestbooks로 요청이 들어오면, content-type이 application/json인 경우 명시된 메소드를  호출
    - 내부적으로 MessageConverter을 사용해서, 해당 메소드에서 반환하는 Map 타입의 객체가 JSON으로 변환되어 클라이언트에게 전송되는 것

- __`@PostMapping`__ : 마찬가지로 POST 요청이 들어올 경우에는 명시된 메소드를 호출하며, 여기서는 Guestbook 타입 객체를 JSON으로 변환시켜 클라이언트에게 전송
    - 단, Guestbook 타입 객체 반환시, 클라이언트의 ip 주소를 같이 받아줌   
   
### 7. Swager을 사용하여 WEB-API 문서화해보기
- [pom.xml](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/calculator/pom.xml) : 여기서는 web.xml을 사용하지 않기 위한 설정과, 스웨거와의 연동을 위한 종속성을 추가
    - properties 태그 내에 web.xml을 사용하지 않도록 사전설정 추가   
    - 초기화시 "web.xml is missing~"과 같은 예외가 발생하지 않도록 하기 위한 것   
``` 
<failOnMissingWebXml>false</failOnMissingWebXml>
``` 

- web.xml을 대체할 WebApplicatonInitializer를 구현한 클래스를 생성
- 여기서는 이를 구현하고 있는 __`AbstractAnnotationConfigDispatcherServletInitializer`__ 클래스를 상속받은 [WebAppInitializer.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/calculator/src/main/java/org/edwith/webbe/calculator/config/WebAppInitializer.java) 클래스를 생성   
    - web.xml에서 설정했던 컨텍스트 로딩시 참고할 설정 클래스의 지정, 리스너 클래스 실행, 프론트 컨트롤러로 DispatcherServlet 등록 등의 작업을 자바 클래스로 대체하는 것   
    - __`AbstractAnnotationConfigDispatcherServletInitializer`__ 클래스를 상속받아 필요한 메소드를 오버라이딩하여 구현
    - getRootConfigClasses : 스프링 기본 설정파일 클래스를 지정(사용할 Bean들을 설정하는 것) / [ApplicationConfig.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/calculator/src/main/java/org/edwith/webbe/calculator/config/ApplicationConfig.java)    
    - getServletConfigClasses : 스프링 MVC 설정 파일 클래스를 지정 / [MvcConfig.java](https://github.com/junu0516/Java-Practice/blob/main/Spring_Practice/calculator/src/main/java/org/edwith/webbe/calculator/config/MvcConfig.java)
    - getServletMappings : DispatcherServlet이 동작할 매핑정보를 설정
    - getServletFilters : 필터를 설정

([맨 위로](#목차))
* * *   

# 스프링부트 연습한 코드 저장
([맨 위로](#목차))
### 1. 스프링부트 환경 설정 및 JUnit테스트 코드 작성   
- [build.gradle](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/build.gradle)에서 Gradle 프로젝트를 스프링 부트 프로젝트로 환경 설정 / 롬복 설치
- [Application.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/main/java/com/junu/springboot/Application.java) : 프로젝트의 Main 클래스 기능 수행   
- [HelloResponseDtoTest.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/test/java/com/junu/springboot/web/dto/HelloResponseDtoTest.java)에서 [HelloResponseDto.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/main/java/com/junu/springboot/web/dto/HelloResponseDto.java) 테스트
    - __`@Getter`__, __`@RequiredArgsConstructor`__ 어노테이션을 통해 getter메소드와 생성자가 올바르게 생성되는 지를 테스트할 수 있음    
    - assertThat(검증 대상).isEqualTo(비교 대상);를 통해 원하는 결과값과 일치하는 지를 확인
- [HelloControllerTest.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/test/java/com/junu/springboot/HelloControllerTest.java)에서 [HelloController.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/Practice1/src/main/java/com/junu/practice/springboot/web/HelloController.java)의 테스트 코드 수행
    - HelloController.java에서 클래스 선언 이전에 __`@RestController`__ 어노테이션을 통해 JSON을 반환하는 컨트롤러로 만들어줌
    - __`@GetMapping`__(매핑할 url)을 통해 HTTP의 요청을 받을 수 있는 API를 만들어줄 수 있음
    - __`HelloControllerTest.java`__ 를 보면 @Test를 수행할 때, mvc.perform().andExpect() ....으로 테스트 코드를 작성하는데, 이는 mvc.perform()의 결과를 검증하는 것
    - __`status().isOk()`__ : HTTP Header의 Status 검증 / content().string(비교대상) : 응답 본문의 내용을 검증.. 여기서는 Controller이 "hello"를 제대로 리턴하는 지를 검증
    - __`jsonPath("$.",is(비교대상))`__ : JSON의 응답값을 필드별로 검증하며 $를 기준으로 필드명 암시  

### 2. CRUD 게시판 만들기    
#### JPA 환경 설정   
- JPA 및 ORM의 개념과 이것이 필요한 이유는 [여기](https://junu0516.tistory.com/85)를 참고하자   
- [build.gradle](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/build.gradle)에서 dependency로 Spring Data JPA 추상화 라이브러리(spring-boot-starter-data-jpa)와, Hibernate 관련 의존성을 우선 등록할 것
- [PostsRepositoryTest.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/test/java/com/junu/springboot/domain/posts/PostsRepositoryTest.java)에서 JUnit 테스트 코드 오류 원인 찾아야 함(2021.1.13)   
- [PostsRepository.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/main/java/com/junu/springboot/domain/posts/PostsRepository.java) : JpaRepository 인터페이스 생성
- [Posts.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/main/java/com/junu/springboot/domain/posts/Posts.java) : Entity 클래스
#### 등록 API 생성(2021.01.15)
- [PostsApiController.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/main/java/com/junu/springboot/web/PostsApiController.java) : Controller에서 API 요청을 받는 역할 수행
- [PostsService.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/main/java/com/junu/springboot/service/posts/PostsService.java) : 트랜젝션, 도메인 기능 간의 순서 보장
- [PostsSaveRequestDto.java](https://github.com/junu0516/Java-Practice/blob/main/Spring%20Boot%20Practice/CRUD/src/main/java/com/junu/springboot/web/dto/PostsSaveRequestDto.java) : Dto 클래스로 Request 데이터를 받는 역할 수행
- Entity와 Dto는 서로 유사하지만, Entity는 비즈니스 로직을 처리하기 때문에 Request/Response 데이터를 주고 받으며 자주 변경이 필요하기 때문에 Dto 클래스로 활용하기 적합하지 못함... 그래서 Dto 클래스를 또하나 만드는 것
   
([맨 위로](#목차))


