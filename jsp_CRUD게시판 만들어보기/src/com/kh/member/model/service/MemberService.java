package com.kh.member.model.service;

import java.sql.Connection;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;
import static com.kh.common.JDBCTemplate.*;

public class MemberService {

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
		
		//db에 정상적으로 추가되었으면 commit, 그렇지 않으면 rollback 함
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
		Member updatedMember = null;
		int result = new MemberDao().updateMember(conn,member);
		
		if(result>0) {
			//결과값이 존재하면 updatedMember 초기화 후 commit
			updatedMember = new MemberDao().selectMember(conn,member.getUserId());
			commit(conn);
		}else {
			//그렇지 않으면 rollback
			rollback(conn);
		}
		close(conn);
		return updatedMember;
	}

	public Member updatePwd(String userId, String userPwd, String newPwd) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Member updatedMember = null;
		int result = new MemberDao().updateMember(conn,userId,userPwd,newPwd);
		
		if(result>0) {
			//새롭게 변경된 member 객체 담아준 후 반환
			updatedMember = new MemberDao().selectMember(conn, userId); 
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return updatedMember;
	}

	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result = 0;
		result = new MemberDao().deleteMember(conn,userId);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}
}
