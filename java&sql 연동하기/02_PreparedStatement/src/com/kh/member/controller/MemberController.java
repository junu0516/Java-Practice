package com.kh.member.controller;

import java.util.*;
import com.kh.member.model.dao.MemberDAO;
import com.kh.member.model.vo.Member;
import com.kh.member.view.MemberMenu;


public class MemberController {
	//view와 dao(db와 연결된 곳)를 연결
	//view <-> controller <->dao<->db 순서로 연결된 구조	
	public void selectAll() {
		// TODO Auto-generated method stub
		MemberMenu menu = new MemberMenu();
		ArrayList<Member> list = new MemberDAO().selectAll();
		if(!list.isEmpty()) {
			menu.displayMemberList(list);
		} else {
			menu.displayError("해당되는 데이터가 없습니다.");
		}
	}

	public void selectOne(String memberId) {
		// TODO Auto-generated method stub
		MemberMenu menu = new MemberMenu(); // view를 통해 결과를 보여주기 위해 view 객체를 생성하는 것
		Member m = new MemberDAO().selectOne(memberId); //Dao를 통해 DB에 접근하여 데이터를 리턴
		if(m!=null) {//m 객체에 데이터가 있으면 
			menu.displayMember(m);
		}else { //m 객체에 데이터가 없으면
			menu.displayError(memberId + " : 해당되는 데이터가 없습니다."); //view를 통한 에러 메시지 출력
		}
	}

	public void insertMember(Member m) {
		// TODO Auto-generated method stub
		int result = new MemberDAO().insertMember(m); //insert, update, delete는 쿼리문의 성공 갯수를 리턴할 것
		if(result > 0) {
			new MemberMenu().displaySuccess("회원가입 성공");
		}else {
			new MemberMenu().displayError("회원가입 실패");
		}
	}

	public void searchName(String memberName) {
		// TODO Auto-generated method stub
		MemberMenu menu = new MemberMenu();
		Member m = new MemberDAO().searchName(memberName);
		if(m!=null) {
			menu.displayMember(m);
		}
		else {
			menu.displayError(memberName + " : 해당되는 데이터가 없습니다.");
		}
	}

	public void updateMember(Member m) {
		if(new MemberDAO().updateMember(m) > 0)
			new MemberMenu().displaySuccess("회원 정보가 변경되었습니다.");
		else
			new MemberMenu().displayError("회원 정보 변경 실패!");
	}

	public void deleteMember(String userId) {
		if(new MemberDAO().deleteMember(userId) > 0)
			new MemberMenu().displaySuccess("회원 탈퇴 성공!");
		else
			new MemberMenu().displayError("회원 탈퇴 실패!");
	}
}
