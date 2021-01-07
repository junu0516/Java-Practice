package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet({ "/MemberInsertServlet", "/insert.me" })
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1. 인코딩 처리
	//	request.setCharacterEncoding("UTF-8");
		
		//2. input 태그의 name에 담긴 값을 뽑아오기
		String userId = request.getParameter("userId"); 
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interests = request.getParameterValues("interest"); //값이 여러개이기 때문에 getParameterValues 사용
		
		String interest = "";
		if(interests!=null) {
			interest = String.join(",",interests); //(구분자, 배열)로 매개변수 받아서 배열내 모든 문자열을 입력받은 구분자로 구분하여 하나로 합침
		}
		
		Member member = new Member(userId,userPwd,userName,phone,email,address,interest); //이거때문에 Member 클래스 만들때 생성자 여러개 만들어둔 것
		
		int result = new MemberService().insertMember(member);
		if(result>0) {
			request.getSession().setAttribute("msg", "회원가입성공");
			response.sendRedirect(request.getContextPath());
		}else {
			request.setAttribute("mg", "회원가입실패");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
