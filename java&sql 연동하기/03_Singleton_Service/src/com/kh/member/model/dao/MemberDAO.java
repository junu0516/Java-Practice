package com.kh.member.model.dao;

import static com.kh.common.JDBCTemplate.close;
import java.util.ArrayList;
import java.sql.*;
import com.kh.member.model.vo.Member;


	      /* 이전 프로젝트에서 Dao가 맡은 업무
			1) JDBC드라이버 등록
			2) DB 연결 Connection 객체생성
			3) SQL 실행
			4) 처리결과에 따른 트랜잭션처리(commit, rollback)
			5) 자원 반환
		
			이 때, 실제로 dao가 처리해야 업무는 SQL문을 DB로 전달하여 실행하고 반환값을 받아오는
			3번의 역할만을 진행해야 함. 
			 --> 3번 이외에 1,2,4,5 역할을 분리해야 함.
			 
			+ 1,2,4,5의 업무는 어디서든 공통적으로 이루어지는 공통 업무
			 --> 한번에 묶어서 처리해주자
			 --> 공통업무를 처리하기 위한
			 com.kh.common.JDBCTemplate 클래스 생성.
		 */

public class MemberDAO {

	public ArrayList<Member> selectAll(Connection conn) {
		// TODO Auto-generated method stub
		ArrayList<Member> list = null;
		Statement stmt = null; //실행할 쿼리
		ResultSet rset = null; //SELECT 후 결과값 받아줄 객체
		
		String sql = "SELECT * FROM MEMBER"; //자동으로 세미콜론 붙어서 SQL에서 시작되므로 걱정할 필요 없음
		try {
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql); //sql에 적은 명령문을 executeQuery()통해 전송하는 것
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
			
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

	/*아이디를 가지고 회원정보를 조회
	 * @param memberId
	 * @return
	 * */
	public Member selectOne(Connection conn,String memberId) {
		// TODO Auto-generated method stub
		Member m = null;
		PreparedStatement pstmt = null; 
		ResultSet rset = null; //SELECT 후 결과값 받아줄 객체
		
		String sql = "SELECT * FROM MEMBER WHERE USERID =?"; //조건절의 아이디 입력 부분 ?로 대체
		try {
			pstmt = conn.prepareStatement(sql); //sql문을 담아 prepareStatement로 연결
			pstmt.setString(1, memberId); //인덱스와, 그 위치에 넣을 값
			rset = pstmt.executeQuery(); //위에서 매개변수로 sql문 받았기 때문에 여기서 받을 필요 없음
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		return m;
	}

	public int insertMember(Connection conn,Member m) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,sysdate)";
		
		System.out.println(sql); //sql insert 명령문 확인
		try {
			pstmt = conn.prepareStatement(sql);
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
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}	
		System.out.println("드라이버 등록 성공");
		return result;
	}

	public Member searchName(Connection conn,String memberName) {
		// TODO Auto-generated method stub
		Member m = null;
		
		PreparedStatement pstmt = null; 
		ResultSet rset = null; 
		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		return m;
	}

	public int updateMember(Connection conn,Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MEMBER SET "				
					 + " PASSWORD = ?"
					 + ", EMAIL=?"
					 + ", PHONE=?"
					 + ", ADDRESS=?"
					 + " WHERE USERID=?"; //한 번에 일렬로 적어도 상관없음
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, m.getPassword());
			pstmt.setNString(2, m.getEmail());
			pstmt.setNString(3, m.getPhone());
			pstmt.setNString(4, m.getAddress());
			pstmt.setNString(5, m.getUserId());
			result = pstmt.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}	
		return result;
	}

	public int deleteMember(Connection conn,String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
