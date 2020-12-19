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
		
		request.setCharacterEncoding("UTF-8");
		
		//요청받은 request 객체에서 userId, userPwd값 불러오기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		//받아온 값으로 Member 객체 만들고 확인
		Member loginUser = new MemberService().loginMember(userId,userPwd);
		System.out.println("loginUser : "+loginUser);
		
		//로그인 성공했을 경우, 즉 loginUser이 null이 아닐 경우에는 이를 세션에 반영하도록 함
		if(loginUser !=null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser); //loginUser을 객체에 저장
			response.sendRedirect(request.getContextPath());
		}else {
			//로그인 실패시 에러페이지로 넘어가도록 함
			request.setAttribute("message", "로그인에 실패하였습니다.");
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
