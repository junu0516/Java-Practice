package com.kh.member.model;

import java.sql.*;
import java.util.*;
import static com.kh.common.JDBCTemplate.*;
import com.kh.member.model.dao.MemberDAO;
import com.kh.member.model.vo.Member;

public class MemberService {

	public List<Member> selectAll() {
		Connection conn = getConnection();
		List<Member> list  = new MemberDAO().selectAll(conn);
		return list;
	}

	public Member selectOne(String memberId) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Member m = new MemberDAO().selectOne(conn, memberId);
		return m;
	}

	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result = new MemberDAO().insertMember(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}

	public Member searchName(String memberName) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Member m = new MemberDAO().searchName(conn,memberName);
		return m;
	}

	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result = new MemberDAO().updateMember(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}

	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result = new MemberDAO().deleteMember(conn,userId);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}

	public void exitProgram() {
		// TODO Auto-generated method stub
		close(getConnection());
	}

}
