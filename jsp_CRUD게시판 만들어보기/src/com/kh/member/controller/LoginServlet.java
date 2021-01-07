package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.me")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//1. 전달된 값에 한글이 있을 경우 별도로 인코딩 처리를 꼭 하도록 함
	//	request.setCharacterEncoding("UTF-8");
		
		//2. 전달된 값을 꺼내서 변수 혹은 객체에 기록하기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String originalPwd = (String)request.getAttribute("originalPwd"); //암호화 적용 이후에는, 미리 이렇게 originalPwd를 따로 만들어놔야 제대로 비교하게 됨
		
		Member loginUser = new MemberService().loginMember(userId,userPwd);
		System.out.println("loginUser : "+loginUser);
		if(loginUser!=null) {
			//로그인 성공했을 경우 세션에 반영
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("originalPwd", originalPwd);
			response.sendRedirect(request.getContextPath());
			/*
			 * JSP 내장객체
			 * 1) application : jsp,servlet, java 코드 어디서든 다 접근해서 값을 사용할 수 있음
			 * 2) session : jsp,servlet 에서 담겨있는 값을 사용할 수 있음 -> 웹브라우저당 하나씩 존재
			 * 3) request : 이 request 객체를 전달받은 해당 jsp 에서 사용할 수 있음
			 * 4) page : 해당 페이지 내에서만 사용할 수 있음 
			 * 
			 * setAttribute : 값을 객체에 저장
			 * getAttribute : 저장한 값을 사용
			 * removeAttribute(객체명) : 객체 삭제 
			 * */
		}else {
			//로그인 실패했을 경우
			request.setAttribute("msg","로그인에 실패하였습니다.");
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
