package com.kh.myBatis.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.myBatis.member.model.service.MemberService;
import com.kh.myBatis.member.model.service.MemberServiceImpl;
import com.kh.myBatis.member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.me")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service = new MemberServiceImpl();
	/*
	 * 굳이 인터페이스를 구현하는 이유는, 느슨한 결합을 활용하기 위한 것
	 * 강한 결합으로 생성할 경우, 서비스 내용을 바꾸게 되면 결합된 모든 것을 바꿔야 하지만
	 * 느슨한 결합으로 생성할 경우에는 하나만 바꿔주면 되기 때문
	 * 
	 * */
       
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
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Member member = new Member();
		member.setUserId(userId);
		member.setUserPwd(userPwd);
		
		Member loginUser;
		try {
			loginUser = service.loginMember(member);
			if(loginUser != null) {
				request.getSession().setAttribute("loginUser", loginUser);
				response.sendRedirect(request.getContextPath());
			}else {
				throw new Exception("No such user exists");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "login failed");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
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
