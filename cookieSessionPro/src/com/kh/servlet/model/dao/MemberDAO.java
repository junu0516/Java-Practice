package com.kh.servlet.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.servlet.model.vo.Member;

public class MemberDAO {

	public Member login(Connection conn, String userId, String password) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		String sql="SELECT * FROM MEMBER WHERE USERID=? AND PASSWORD=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=new Member();
				m.setMemberId(rs.getNString("USERID"));
				m.setMemberPwd(rs.getString("PASSWORD"));
				m.setMemberName(rs.getNString("USERNAME"));
				
				System.out.println("****DB조회****");
				System.out.println(rs.getNString("USERID"));
				System.out.println(rs.getNString("PASSWORD"));
				System.out.println(rs.getNString("USERNAME"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}return m;
	}
}
