package com.kh.servlet.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.kh.servlet.common.JDBCTemplate;
import com.kh.servlet.model.dao.MemberDAO;
import com.kh.servlet.model.vo.Member;

public class MemberService {
	private MemberDAO dao=new MemberDAO();
	public Member login(String userId, String password) {
		Connection conn=JDBCTemplate.getConnection();
		Member m=dao.login(conn,userId, password);
		System.out.println("--확인--");
		System.out.println(m.getMemberId());
		System.out.println(m.getMemberPwd());
		System.out.println(m.getMemberName());
		try {
			if(conn!=null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
}
