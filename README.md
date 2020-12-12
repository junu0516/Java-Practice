# 자바 연습한 코드 저장
### 1. JDBC 활용하여 자바 파일과 db 연동하기   
* __단순 statement 활용__ : [코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/01_Statement/src/com/kh/member/model/dao/MemberDAO.java)   

* __PreparedStatement 활용__ : [코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/02_PreparedStatement/src/com/kh/member/model/dao/MemberDAO.java)   
(PreparedStatement를 활용하면 쿼리의 반복적인 수행시 DB에 적은 부하를 주기 때문에 더 좋다.)   

* __싱글톤 패턴을 활용해보기__ : [JDBCTemplate 코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/03_Singleton_Service/src/com/kh/common/JDBCTemplate.java), [dao 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/03_Singleton_Service/src/com/kh/member/model/dao/MemberDAO.java)   
(JDBCTemplate의 정적 변수/메소드를 만들어두면 커넥션 객체를 하나만 만들면 되기 때문에 메모리의 낭비를 줄일 수 있음)   

* __properties 파일 활용하기__ : [properties 보기](https://github.com/junu0516/java-practice/tree/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/04_Properties/resources), [JDBCTemplate 코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/04_Properties/src/com/kh/common/JDBCTemplate.java), [dao 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/04_Properties/src/com/kh/member/model/dao/MemberDAO.java)     
(싱글톤 패턴의 적용에서 더 나아가 properties 파일을 통해 동적으로 db 연결정보를 불러오기 때문에, 정보가 변경될 때마다 일일히 코드를 바꾸지 않아도 됨 불러올 수 있음)   

### 2. 서블릿을 활용하여 html문서와 자바 파일 서로 연동하기   
* __매핑한 URL로 이동하도록 form 생성__ : [html파일 보기](https://github.com/junu0516/java-practice/blob/main/servletExample/WebContent/views/testServlet.html)   
* __서블릿 객체에서 요청 받아서 동적인 웹페이지 작성__ : [코드 보기](https://github.com/junu0516/java-practice/blob/main/servletExample/src/com/serverlets/TestServlet.java)      
* 서블릿 컨테이너와 웹서버가 요청을 처리하는 과정은 [여기](https://junu0516.tistory.com/65)에 저장해두었음!
