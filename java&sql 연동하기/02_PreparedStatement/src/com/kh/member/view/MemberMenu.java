package com.kh.member.view;

import java.util.*;

import com.kh.member.controller.MemberController;
import com.kh.member.model.vo.Member;

public class MemberMenu {
	private static Scanner sc = new Scanner(System.in);
	private MemberController mController = new MemberController();
	public void mainMenu() {
		// TODO Auto-generated method stub
		int choice;
		do {//최초로 1번은 실행해야 하기 때문
			System.out.println("\n******회원 관리 프로그램******");
			System.out.println("1. 회원 전체 조회"); // SELECT
			System.out.println("2. 회원 아이디 조회"); // SELECT
			System.out.println("3. 회원 이름 조회"); // SELECT
			System.out.println("4. 회원 가입"); // INSERT
			System.out.println("5. 회원 정보 변경"); //UPDATE
			System.out.println("6. 회원 탈퇴"); // DELETE
			System.out.println("9. 프로그램 끝내기"); //종료
			System.out.println("번호 선택 : ");
			
			choice = sc.nextInt();
			switch(choice) {
			case 1 : 
				mController.selectAll();
				break;
			case 2 : 
				mController.selectOne(inputMemberId());
				break;
			case 3 : 
				mController.searchName(inputMemberName());
				break;
			case 4 : 
				mController.insertMember(inputMember());
				break;
			case 5 : 
				mController.updateMember(updateMember());
				break;
			case 6 : 
				mController.deleteMember(inputMemberId());
				break;
			case 9 : 
				System.out.println("정말로 끝내시겠습니까?(y/n)");
				if('y' == sc.next().toLowerCase().charAt(0)) return;
				break;
			default :
				System.out.println("번호를 잘못 입력하였습니다.");
			}
		}
		while(true);
		
	}
	
	private Member updateMember() {
		Member m = new Member();

		m.setUserId(inputMemberId());

		System.out.print("암호 : ");
		m.setPassword(sc.next());
		System.out.print("이메일 : ");
		m.setEmail(sc.next());
		System.out.print("전화번호(-빼고입력): ");
		m.setPhone(sc.next());
		System.out.print("주소 : ");
		sc.nextLine();
		m.setAddress(sc.nextLine());

		return m;
	}
	
	private String inputMemberName() {
		// TODO Auto-generated method stub
		System.out.println("조회할 이름 입력 : ");
		return sc.next();
	}

	private Member inputMember() {
		// TODO Auto-generated method stub
		Member m = new Member();
		System.out.println("새로운 회원정보를 입력하세요");
		
		System.out.println("아이디 :");
		m.setUserId(sc.next());
		System.out.println("암호 :");
		m.setPassword(sc.next());
		System.out.println("이름 :");
		m.setUsername(sc.next());
		System.out.println("나이 :");
		m.setAge(sc.nextInt());
		System.out.println("성별(M/F) :");
		m.setGender(sc.next().toUpperCase());
		System.out.println("이메일 :");
		m.setEmail(sc.next());
		System.out.println("전화번호(-빼고 입력) :");
		m.setPhone(sc.next());
		System.out.println("주소 :");
		sc.nextLine(); //버퍼에 엔터 남아있을 수 있기 때문에 제거해줌
		m.setAddress(sc.nextLine());
		System.out.println("취미(,로 공백없이 입력) :");
		m.setHobby(sc.next());
		
		return m;
	}

	/*검색할 id 입력*/
	private String inputMemberId() {
		// TODO Auto-generated method stub
		System.out.println("조회할 아이디 입력 : ");
		return sc.next();
	}

	/*
	 * 리턴된 리스트 출력용 메소드
	 * @param list
	 * */
	public void displayMemberList(List<Member> list) {
		// TODO Auto-generated method stub
		System.out.println("\n조회된 전체 정보는 다음과 같습니다.");
		System.out.println("\n아이디\t이름\t성별\t나이\t이메일\t전화번호\t주소\t취미\t가입일");
		System.out.println("--------------------------------------------------");
		for(Member m : list) {
			System.out.println(m);
		}
	}
	
	
	/*
	 * 실패에 대한 에러메시지 출력용
	 * @param message
	 * */
	public void displayError(String message) {
		// TODO Auto-generated method stub
		System.out.println("서비스 요청 처리 실패 : "+message);
	}
	
	/*
	 * 서비스 요청 성공에 대한 에러메시지 출력용
	 * @param message
	 * */
	public void displaySuccess(String message) {
		// TODO Auto-generated method stub
		System.out.println("서비스 요청 결과 : "+message);
	}
	
	public void displayMember(Member m) {
		// TODO Auto-generated method stub
		System.out.println("\n조회된 전체 정보는 다음과 같습니다.");
		System.out.println("\n아이디\t이름\t성별\t나이\t이메일\t전화번호\t주소\t취미\t가입일");
		System.out.println("--------------------------------------------------");
		System.out.println(m);
	}	
}
