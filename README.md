# ★ java-practice : 자바 연습한 코드 저장
### 1. JDBC 활용하여 자바 파일과 db 연동하기   
* 단순 statement 활용 : [코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/01_Statement/src/com/kh/member/model/dao/MemberDAO.java)
* PreparedStatement 활용 : [코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/02_PreparedStatement/src/com/kh/member/model/dao/MemberDAO.java)   
(PreparedStatement를 활용하면 쿼리의 반복적인 수행시 DB에 적은 부하를 주기 때문에 더 좋다.)   
* 싱글톤 패턴을 활용해보기 : [JDBCTemplate 코드 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/03_Singleton_Service/src/com/kh/common/JDBCTemplate.java), [dao 보기](https://github.com/junu0516/java-practice/blob/main/java%26sql%20%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0/03_Singleton_Service/src/com/kh/member/model/dao/MemberDAO.java)   
(JDBCTemplate의 정적 변수/메소드를 만들어두면 커넥션 객체를 하나만 만들면 되기 때문에 메모리의 낭비를 줄일 수 있음)     
*  properties 파일을 통해 반복되는 드라이버명 입력, 쿼리문 입력 등을 한 번에 처리하기   
### 2. 서블릿을 활용하여 html문서와 자바 파일 서로 연동하기
