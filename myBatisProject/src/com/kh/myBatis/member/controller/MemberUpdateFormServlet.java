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
 * Servlet implementation class MemberUpdateFormServlet
 */
@WebServlet("/updateForm.me")
public class MemberUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DB로부터 최신 유저 정보 불러옴(로그인 상태에서의 객체가 최신 업데이트된 DB 정보와 맞지 않을 수 있기 때문)
		Member loginUser = (Member) request.getSession().getAttribute("loginUser");
		
		try {
			loginUser = memberService.loginMember(loginUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//먼저 업데이트된 유저 정보로 세션 업데이트
		request.getSession().setAttribute("loginUser",loginUser);
	
		request.getRequestDispatcher("WEB-INF/views/member/memberUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
