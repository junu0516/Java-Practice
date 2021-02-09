package com.kh.myBatis.common;

import com.kh.myBatis.board.model.vo.PageInfo;

public class Pagination {
	
	public static PageInfo getPageInfo(int listCount, int currentPage, int pageLimit, int boardLimit) {
		
		//maxPage : 총 페이지 수
		int maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		//현재 페이지에 보여지는 페이징 바의 시작 넘버
		int startPage = (currentPage - 1)/pageLimit*pageLimit+1; 
		
		//현재 페이지에 보여지는 페이징 바의 마지막 넘버
		int endPage = startPage + pageLimit -1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		return new PageInfo(listCount,currentPage,startPage,endPage,maxPage,pageLimit,boardLimit);
	}
}
