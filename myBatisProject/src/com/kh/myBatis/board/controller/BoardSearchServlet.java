package com.kh.myBatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.myBatis.board.model.service.BoardService;
import com.kh.myBatis.board.model.service.BoardServiceImpl;
import com.kh.myBatis.board.model.vo.Board;
import com.kh.myBatis.board.model.vo.PageInfo;
import com.kh.myBatis.board.model.vo.SearchCondition;
import com.kh.myBatis.common.Pagination;

/**
 * Servlet implementation class BoardSearchServlet
 */
@WebServlet("/search.bo")
public class BoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService boardService = new BoardServiceImpl();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//선택한 검색 기준, 입력한 검색어 받아오기
		String condition = request.getParameter("condition");
		String search = request.getParameter("search");
		
		SearchCondition searchCondition = new SearchCondition();
		
		//검색기준에 맞춰서, 검색어를 searchConditoin 객체의 setter 매개변수로 넣어줌
		switch(condition) {
		case "writer" : 
			searchCondition.setWriter(search);
			break;
		case "title" : 
			searchCondition.setTitle(search);
			break;
		case "content" :
			searchCondition.setContent(search);
			break;
		}
		
		int listCount;
				
		try {
			
			listCount = boardService.getListCount(searchCondition);
			
			int currentPage = 1;
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			int pageLimit = 10;
			int boardLimit = 5;
			
			PageInfo pageInfo = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
			ArrayList<Board> boardList = boardService.selectList(pageInfo,searchCondition);
			
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", boardList);
			request.setAttribute("condition", condition);
			request.setAttribute("search", search);
			
			request.getRequestDispatcher("WEB-INF/views/board/boardListView.jsp").forward(request, response);;
			
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
