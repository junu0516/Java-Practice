package com.kh.member.model.service;

import java.sql.Connection;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;
import static com.kh.common.JDBCTemplate.*;

public class MemberService {
	/*
	 * 로그인 서비스
	 * @param userId 사용자가 입력한 아이디
	 * @param userPwd 사용자가 입력한 비밀번호
	 * @return 입력한 아이디와 비밀번호와 일치하는 회원 객체 리턴
	 * 
	 * */
	public Member loginMember(String userId, String userPwd) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Member loginUser = new MemberDao().loginMember(conn,userId,userPwd);
		close(conn);
		return loginUser;
	}

	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result = new MemberDao().insertMember(conn,member);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Member selectMember(String userId) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Member member = new MemberDao().selectMember(conn,userId);
		close(conn);
		return member;
	}

	public Member updateMember(Member member) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Member updateMem = null;
		int result = new MemberDao().updateMember(conn,member);
		if(result>0) {
			commit(conn);
			updateMem = new MemberDao().selectMember(conn, member.getUserId());
		}else {
			rollback(conn);
		}
		close(conn);
		return updateMem;
	}

	public Member updatePwd(String userId, String userPwd, String newPwd) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Member updateMem = null;
		int result = new MemberDao().updateMember(conn, userId,userPwd,newPwd);
		if(result>0) {
			commit(conn);
			updateMem = new MemberDao().selectMember(conn, userId);
		}else {
			rollback(conn);
		}
		close(conn);
		return updateMem;
	}

	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result=0;
		result = new MemberDao().deleteMember(conn,userId);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}

	public int idCheck(String userId) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		
		int result = new MemberDao().idCheck(conn,userId);
		close(conn);
		return result;
	}
}
