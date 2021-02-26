package com.kh.spring.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageInfo {
	
	private int currentPage;
	private int listCount;
	private int startPage;
	private int endPage;
	private int maxPage;
	private int pageLimit;
	private int boardLimit;
	
	
}
