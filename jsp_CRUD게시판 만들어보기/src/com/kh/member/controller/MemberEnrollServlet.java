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
 * Servlet implementation class MemberEnroll
 */
@WebServlet("/insert.me")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		//memberEnrollForm.jsp로부터 받은 값들을 가지고 새로운 Member 객체를 만들어줌
		
		//1. 제일먼저 memberEnrollForm.jsp의 input 태그의 name에 담긴 값들을 뽑아옴
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interests = request.getParameterValues("interest"); //체크박스에 여러개가 체크되어 있기 때문에 값을 배열로 받아줌
		
		String interest = "";
		if(interests!=null) {
			interest = String.join(",", interests); //배열 내의 모든 문자열을 하나의 문자열로 합쳐줌
		}
		
		Member member = new Member(userId,userPwd,userName,phone,email,address,interest);
		
		//만든 member 객체를 db에 새로 누적시킴
		int result = new MemberService().insertMember(member);
		if(result>0) {
			request.getSession().setAttribute("message", "회원가입성공");
			response.sendRedirect(request.getContextPath());
		}else {
			request.setAttribute("message", "회원가입실패");
			RequestDispatcher view = request.getRequestDispatcher("view/common/errorPage.jsp");
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
