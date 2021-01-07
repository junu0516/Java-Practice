package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.vo.Member;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/insert.no")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     *"
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// DB에 저장시 
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = String.valueOf(((Member)request.getSession().getAttribute("loginUser")).getUserNo());// 작성자회원번호
		System.out.println(content);
		
		Notice n = new Notice(title, writer, content.replaceAll("\n", "<br>")); //줄바꿈을 인식하기 위해서 \n을 <br>로 모두 바꿔줘야 jsp가 제대로 읽어들임(페이지 생성시에는 html언어를 활용하기 때문)
		
		int result = new NoticeService().insertNotice(n);
		
		if(result > 0) {
				
			request.getSession().setAttribute("msg", "공지사항이  성공적으로 등록되었습니다.");
			response.sendRedirect("list.no");
		}else {
			request.setAttribute("msg", "공지사항 등록 실패!!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
