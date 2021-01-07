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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/update.me")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId"); 
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interests = request.getParameterValues("interest"); //값이 여러개이기 때문에 getParameterValues 사용
		
		String interest = "";
		if(interests!=null) {
			interest = String.join(",",interests); //(구분자, 배열)로 매개변수 받아서 배열내 모든 문자열을 입력받은 구분자로 구분하여 하나로 합침
		}
		
		Member updateMem = new MemberService().updateMember(new Member(userId,userName,phone,email,address,interest));
		if(updateMem != null) {
			request.getSession().setAttribute("msg", "회원정보 수정에 성공하였습니다.");
			request.getSession().setAttribute("loginUser", updateMem);
			response.sendRedirect(request.getContextPath());
		}else {
			request.setAttribute("msg", "회원정보 수정에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request,response);
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
