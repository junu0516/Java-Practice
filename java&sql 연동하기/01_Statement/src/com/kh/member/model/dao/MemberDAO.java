package com.kh.member.model.dao;

import java.util.ArrayList;
import java.sql.*;
import com.kh.member.model.vo.Member;

/*
 * 1. Connection 객체 연결하기
 * 2. Statement 객체 생성하기
 * 3. ResultSet 객체 생성하기
 * 4. SQL 작성하기
 * 5. ResultSet 결과 담기
 * 6. list에 객체 하나씩 담기
 * 
 * */

public class MemberDAO {

	public ArrayList<Member> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Member> list = null;
		
		//list에 데이터를 가져와서 담기
		//아래 클래스들은 모두 sql 클래스로 사용
		Connection conn = null; //DB와 연결할 객체
		Statement stmt = null; //실행할 쿼리
		ResultSet rset = null; //SELECT 후 결과값 받아줄 객체
		
		String sql = "SELECT * FROM MEMBER"; //자동으로 세미콜론 붙어서 SQL에서 시작되므로 걱정할 필요 없음
		try {
			//1. jdbc driver 등록 처리 : 해당 database 벤더사가 제공하는 클래스 등록하는 것, 여기서는 오라클
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");
			
			//2. 등록된 클래스를 이용해서 DB 연결
			//드라이버타입 @ip주소 : 포트번호 : db이름(SID)
			//orcl : 사용자 정의설치, thin : 자동으로 설치
			//ip주소 -> localhost로 변경해도 됨
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");			
			System.out.println("conn = "+conn); //성공하면 connection 값, 실패하면 null을 출력하게 될 것
			
			//3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();
			
			//4. 쿼리문 전송, 실행결과(ResultSet으로 받기)
			rset = stmt.executeQuery(sql); //sql에 적은 명령문을 executeQuery()통해 전송하는 것
			
			//5. 받은 결과값을 객체에 옮겨서 저장하기
			list = new ArrayList<Member>();
			while(rset.next()) {
				Member m = new Member();
				m.setUserId(rset.getString("USERID")); //컬럼명 매개변수로 줘서 정보 추가
				m.setPassword(rset.getString("PASSWORD"));
				m.setUsername(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrolldate(rset.getDate("ENROLLDATE"));
				list.add(m); //리스트에 setting 완료된 Member 객체 추가
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			//혹시 모르니 최상위 Exception도 처리
			e.printStackTrace();
		} finally {
			//자원을 다 썼으니 반납하는 의미에서 .close()로 닫아줌
			//제일 먼저 사용한 것이 제일 마지막에 닫히는 순서를 따를 것(역순)
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	/*아이디를 가지고 회원정보를 조회
	 * @param memberId
	 * @return
	 * */
	public Member selectOne(String memberId) {
		// TODO Auto-generated method stub
		Member m = null;
		
		Connection conn = null; //DB와 연결할 객체
		Statement stmt = null; //실행할 쿼리
		ResultSet rset = null; //SELECT 후 결과값 받아줄 객체
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = '"+memberId+"'"; //자동으로 세미콜론 붙어서 SQL에서 시작되므로 걱정할 필요 없음
		try {
			//1. jdbc driver 등록 처리 : 해당 database 벤더사가 제공하는 클래스 등록하는 것, 여기서는 오라클
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");
			
			//2. 등록된 클래스를 이용해서 DB 연결
			//드라이버타입 @ip주소 : 포트번호 : db이름(SID)
			//orcl : 사용자 정의설치, thin : 자동으로 설치
			//ip주소 -> localhost로 변경해도 됨
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");			
			System.out.println("conn = "+conn); //성공하면 connection 값, 실패하면 null을 출력하게 될 것
			
			//3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();
			
			//4. 쿼리문 전송, 실행결과(ResultSet으로 받기)
			rset = stmt.executeQuery(sql); //sql에 적은 명령문을 executeQuery()통해 전송하는 것
			
			//5. 받은 결과값을 객체에 옮겨서 저장하기
			if(rset.next()) {
				m = new Member();
				m.setUserId(rset.getString("USERID")); //컬럼명 매개변수로 줘서 정보 추가
				m.setPassword(rset.getString("PASSWORD"));
				m.setUsername(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrolldate(rset.getDate("ENROLLDATE"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			//혹시 모르니 최상위 Exception도 처리
			e.printStackTrace();
		} finally {
			//자원을 다 썼으니 반납하는 의미에서 .close()로 닫아줌
			//제일 먼저 사용한 것이 제일 마지막에 닫히는 순서를 따를 것(역순)
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return m;
	}

	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		String sql = "INSERT INTO MEMBER VALUES("
					+"'"+m.getUserId()+"'"
					+",'"+m.getPassword()+"'"
					+",'"+m.getUsername()+"'"
					+",'"+m.getGender()+"'"
					+","+m.getAge() // int는 sql 내에서 '' 필요 없기 때문에
					+",'"+m.getEmail()+"'"
					+",'"+m.getPhone()+"'"
					+",'"+m.getAddress()+"'"
					+",'"+m.getHobby()+"'"
					+",SYSDATE)";
		System.out.println(sql); //sql insert 명령문 확인
		
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");			
			System.out.println("conn = "+conn);
			stmt = conn.createStatement();
			conn.setAutoCommit(false);
			result = stmt.executeUpdate(sql); //update는 executeUpdate 통해 db로 전달
											  //처리한 행의 갯수를 리턴(int)
			if(result>0) conn.commit(); //db 커밋 적용
			else conn.rollback();  //db 롤백 적용
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//자원을 다 썼으니 반납하는 의미에서 .close()로 닫아줌
			//제일 먼저 사용한 것이 제일 마지막에 닫히는 순서를 따를 것(역순)
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		System.out.println("드라이버 등록 성공");
		
		return result;
	}

	public Member searchName(String memberName) {
		// TODO Auto-generated method stub
		Member m = null;
		
		Connection conn = null; 
		Statement stmt = null; 
		ResultSet rset = null; 
		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME = '"+memberName+"'"; //자동으로 세미콜론 붙어서 SQL에서 시작되므로 걱정할 필요 없음
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");			
			System.out.println("conn = "+conn);
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql); 
			
			if(rset.next()) {
				m = new Member();
				m.setUserId(rset.getString("USERID")); 
				m.setPassword(rset.getString("PASSWORD"));
				m.setUsername(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrolldate(rset.getDate("ENROLLDATE"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return m;
	}

	public int updateMember(Member m) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "UPDATE MEMBER SET "
					 + " PASSWORD='"+m.getPassword()+"'"
					 + ", EMAIL='"+m.getEmail()+"'"
					 + ", PHONE='"+m.getPhone()+"'"
					 + ", ADDRESS='"+m.getAddress()+"'"
					 + " WHERE USERID='"+m.getUserId()+"'";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			stmt = conn.createStatement();
			conn.setAutoCommit(false);
			result = stmt.executeUpdate(sql);
			
			//커밋처리
			if(result>0) conn.commit();
			else conn.rollback();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int deleteMember(String memberId) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID='"+memberId+"'";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			stmt = conn.createStatement();
			conn.setAutoCommit(false);
			result = stmt.executeUpdate(sql);
			
			//커밋처리
			if(result>0) conn.commit();
			else conn.rollback();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
