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
		//Statement stmt = null; //실행할 쿼리
		PreparedStatement pstmt = null; //PreparedStatement로 사용해보기
		ResultSet rset = null; //SELECT 후 결과값 받아줄 객체
		
		String sql = "SELECT * FROM MEMBER WHERE USERID =?"; //조건절의 아이디 입력 부분 ?로 대체
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
			pstmt = conn.prepareStatement(sql); //sql문을 담아 prepareStatement로 연결
			
			//4. 쿼리문 전송, 실행결과(ResultSet으로 받기)
			pstmt.setString(1, memberId); //인덱스와, 그 위치에 넣을 값
			rset = pstmt.executeQuery(); //위에서 매개변수로 sql문 받았기 때문에 여기서 받을 필요 없음
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,sysdate)";
		
		System.out.println(sql); //sql insert 명령문 확인
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");			
			System.out.println("conn = "+conn);
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			
			//db 테이블 순서랑 인덱스 매칭시켜서 넣어주면 됨
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUsername());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			result = pstmt.executeUpdate(); 
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
				pstmt.close();
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
		PreparedStatement pstmt = null; 
		ResultSet rset = null; 
		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");			
			System.out.println("conn = "+conn);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+memberName+"%");
			rset = pstmt.executeQuery(); 
			
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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MEMBER SET "				
					 + " PASSWORD = ?"
					 + ", EMAIL=?"
					 + ", PHONE=?"
					 + ", ADDRESS=?"
					 + " WHERE USERID=?"; //한 번에 일렬로 적어도 상관없음
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			
			pstmt.setNString(1, m.getPassword());
			pstmt.setNString(2, m.getEmail());
			pstmt.setNString(3, m.getPhone());
			pstmt.setNString(4, m.getAddress());
			pstmt.setNString(5, m.getUserId());
			
			result = pstmt.executeUpdate();
			
			//커밋처리
			if(result>0) conn.commit();
			else conn.rollback();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
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
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID=?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			conn.setAutoCommit(false);
			result = pstmt.executeUpdate();
			
			//커밋처리
			if(result>0) conn.commit();
			else conn.rollback();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
