package com.kh.member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.member.model.vo.Member;
import static com.kh.common.JDBCTemplate.*;

public class MemberDao {
	private Properties prop = new Properties();
	//생성자
	public MemberDao() {
		String fileName = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public Member loginMember(Connection conn, String userId, String userPwd) {
		Member loginUser = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("loginMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);

			rset = pstmt.executeQuery();
			if(rset.next()) {
				loginUser = new Member(rset.getInt("USER_NO"),
						rset.getString("USER_ID"),
						rset.getString("USER_PWD"),
						rset.getString("USER_NAME"),
						rset.getString("PHONE"),
						rset.getString("EMAIL"),
						rset.getString("ADDRESS"),
						rset.getString("INTEREST"),
						rset.getDate("ENROLL_DATE"),
						rset.getDate("MODIFY_DATE"),
						rset.getString("STATUS"));
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return loginUser;	
	}


	public int insertMember(Connection conn, Member member) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember"); //member-query.properties에서 가져온 것
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getInterest());
			
			result = pstmt.executeUpdate(); //총 몇 건이 추가되었는 지 개수를 result에 담아줌
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public Member selectMember(Connection conn, String userId) {
		// TODO Auto-generated method stub
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMember");
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery(); //결과값을 담는 rset변수에 쿼리를 실행한 결과를 담아주는 것
			if(rset.next())/*결과가 존재할 경우*/{
				member = new Member();
				member.setUserNo(Integer.parseInt(rset.getString("USER_NO")));
				member.setUserId(rset.getString("USER_ID"));
				member.setUserPwd(rset.getString("USER_PWD"));
				member.setUserName(rset.getString("USER_NAME"));
				member.setPhone(rset.getString("PHONE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setInterest(rset.getString("INTEREST"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				member.setModifyDate(rset.getDate("MODIFY_DATE"));
				member.setStatus(rset.getString("STATUS"));				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}


	public int updateMember(Connection conn, Member updateMem) {
		// TODO Auto-generated method stub
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateMem.getUserName());
			pstmt.setString(2, updateMem.getPhone());
			pstmt.setString(3, updateMem.getEmail());
			pstmt.setString(4, updateMem.getAddress());
			pstmt.setString(5, updateMem.getInterest());
			pstmt.setString(6, updateMem.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int updateMember(Connection conn, String userId, String userPwd, String newPwd) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int deleteMember(Connection conn, String userId) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int idCheck(Connection conn, String userId) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1); //column 순서로도 받을 수 있기 때문에, 열이 1개밖에 없으므로 순서로 받아옴
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
}
