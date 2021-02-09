package com.kh.myBatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.myBatis.board.model.service.BoardService;
import com.kh.myBatis.board.model.service.BoardServiceImpl;
import com.kh.myBatis.board.model.vo.Board;
import com.kh.myBatis.board.model.vo.PageInfo;
import com.kh.myBatis.common.Pagination;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/list.bo")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService service = new BoardServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//페이징 처리
			//1. 총 게시글 개수
			int listCount = service.getListCount();
			
			//2. 현재 페이지의 default는 1로 둠(1페이지가 메인에 뜨기 때문) -> 이후, 요청 들어오면 currentPage 값을 바꿔주는 것
			int currentPage = 1;
			
			//3. 페이지 전환시, 요청받은 페이지 번호로 변수 초기화
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			//4. 페이지 개수, 한 페이지당 보여질 게시글 개수
			//여기서는 최대 한 번에 10페이지까지만 보여주며, 한 페이지당 게시글은 5개씩 보여주겠다는 것을 의미
			int pageLimit = 10;
			int boardLimit = 5;
			
			//페이징 처리를 위한 정보를 담은 객체 생성
			//DB의 게시글 데이터 상태에 따라 페이징이 달라지기 때문에, Pagination 클래스의 getPageInfo() 메소드를 통해 유동적으로 페이징 처리를 위한 정보를 처리
			//유동적으로 변하는 정보 : 총 게시글의 개수, 요청받은 페이지(currentPage), 미리 설정해둔 페이지 개수, 게시글 개수 제한 --> 매개변수로 담아줄 것들
			PageInfo pageInfo = Pagination.getPageInfo(listCount,currentPage,pageLimit,boardLimit);
					
			ArrayList<Board> boardList = service.selectList(pageInfo); //페이징 처리를 위한 정보를 담아서 게시글 리스트를 불러옴(pageInfo는 RowBounds클래스 활용을 위한 매개변수로 쓰일 것)
			request.setAttribute("pi", pageInfo);
			request.setAttribute("list", boardList);
			request.getRequestDispatcher("WEB-INF/views/board/boardListView.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
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
