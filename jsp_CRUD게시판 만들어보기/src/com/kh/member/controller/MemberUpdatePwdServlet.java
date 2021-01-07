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
 * Servlet implementation class MemberUpdatePwdServlet
 */
@WebServlet("/updatePwd.me")
public class MemberUpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//현재 세션에 있는 loginUser을 가져와서, 그 아이디를 불러온 것
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getUserId(); //object 타입으로 넘어오기 때문에 형변환 필요
		String userPwd = request.getParameter("userPwd");
		String newPwd = request.getParameter("newPwd");
		
		Member updateMem = new MemberService().updatePwd(userId,userPwd,newPwd);
		System.out.println("변경 후 : "+updateMem);
		//업데이트 후 팝업창 닫아주기
		RequestDispatcher view = request.getRequestDispatcher("views/member/pwdUpdateForm.jsp");
		if(updateMem != null) {
			request.setAttribute("msg", "성공적으로 비밀번호를 변경하였습니다.");
			//문자열이 일치해야만 성공적으로 변경한 것이기 때문에, 나중에 다시 만들 때는 Y/N 태그로 따로 관리하는 것이 더욱 편리함
			request.getSession().setAttribute("loginUser", updateMem);
		}else {
			request.setAttribute("msg", "비밀번호 변경에 실패하였습니다.");
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
