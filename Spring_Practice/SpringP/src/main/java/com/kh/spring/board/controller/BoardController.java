package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.Pagination;
import com.kh.spring.common.exception.CommonException;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	/*
	 *  1. @RequestParam(value="currentPage") int currentPage --> @RequestParam 지정한 키 값이 존재하지 않다면  400 에러 (값을 주입할 수가 없어서)
	 *  
	 * 	2. @RequestParam(value="currentPage", required=false) int currentPage
	 * 					required : 해당 파라미터가 필수인지의 여부(true : 필수)required 속성 값을 따로 작성안할 경우 기본 값은 true
     *   				-> required=true일 때 NULL 입력 시 400에러 발생
     *   				-> 따라서 required=false로 지정하여 값을 받아줄 필요없다라고 선언(null이 들어올 수 있다.)
     *   
     *   				-> null이라는 데이터가 기본형이 int형에 들어갈 수 없기 때문에 
     *  
     *  3. 그럼 null일경우 기본값이 들어갈 수 있도록 지정
     *    @RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage
     *    				defaultValue : 넘어오는 값이 null일 경우 해당 파라미터의 기본값을 지정할 수 있다 .
     *  
	 */
	
	@RequestMapping("list.bo")
	public String selectList(@RequestParam(value="currentPage", required=false, defaultValue="1")int currentPage, Model model) {
		/*
		 * boardListView.jsp를 보면,
		 * list.bo?currentPage=1...의 형식이 아니라 그냥
		 * list.bo만 하나 달랑 입력되있음
		 * 
		 * 여기서 required=true가 보통 default이기 때문에, 파라미터 없을 경우 위의 메소드의 매개변수로 받는 currentPage의 값이 무엇인지 모르기 때문에 오류가 발생할 수 있음
		 * 따라서, 오류를 방지하고자, @RequestParam의 파라미터로 required 속성을 false로 두고 defaultValue를 설정해줘야 함
		 * 
		 * 위의 예시대로 하면 list.bo로만 요청을 보냈을 때, ?뒤에 붙는 파라미터가 없어도 알아서 currentPage 값을 디폴트인 1로 두고 컨트롤러에서 요청을 처리하게 되는 것!
		 *
		 * */
		
		int listCount = boardService.selectListCount();
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 10, 5);
		
		
		ArrayList<Board> list = boardService.selectList(pi);
		
		//view로 게시글 리스트, 페이지 정보를 넘기기
		model.addAttribute("list",list);
		model.addAttribute("pi",pi);
		
		return "board/boardListView";
	}
	
	@RequestMapping("enrollForm.bo")
	public String enrollForm() {
		
		return "board/boardEnrollForm";
	}
	
	@RequestMapping("insert.bo")
	public String insertBoard(Board board, HttpServletRequest request, Model model,
							  @RequestParam(name="uploadFile", required=false)MultipartFile file) {
		
		System.out.println(board);//전달되는 게시글 확인
		System.out.println("file : "+file); //전달되는 파일명 확인
		
		if(!file.getOriginalFilename().equals("")) {
			String changeName = saveFile(file,request); //업로드된 파일을 계속 활용해야 하기 때문에 따로 저장하고, changeName을 설정
			
			if(changeName!=null) { //업로드가 정상처리 되었다면 changeName에 값이 존재
				board.setOriginName(file.getOriginalFilename());
				board.setChangeName(changeName);
			}
		}
		
		int result = boardService.insertBoard(board);
		if(result>0) {
			return "redirect:list.bo";
		}else {
			throw new CommonException("게시물 작성 실패");
		}
	}

	private String saveFile(MultipartFile file, HttpServletRequest request) {
		
		String resources = request.getSession().getServletContext().getRealPath("resources");
		System.out.println("resources : "+resources);
		String savePath = resources+"\\upload_files\\";
		
		String originalName = file.getOriginalFilename();
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); //java.util로 import
		
		String ext = originalName.substring(originalName.lastIndexOf(".")); //확장자
		
		String changeName = currentTime + ext; //저장할 이름은 시간+확장자 형식으로 사용하게 될 것
		
		try {
			file.transferTo(new File(savePath+changeName)); //바뀐 이름과 경로 정보 반영해서 첨부한 파일을 transfer
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			System.out.println("파일 업로드 에러 "+e.getMessage());
		}
		
		return changeName;
	}
	
	//이번에는 view name이 아닌 ModelAndView 객체를 반환하여, view name과 반환할 Board 객체를 한꺼번에 넘겨보자
	@RequestMapping("detail.bo")
	public ModelAndView selectBoard(int bno, ModelAndView mv){
		
		int result = boardService.updateCount(bno);
		
		if(result>0) {
			Board board = boardService.selectBoard(bno);
			mv.addObject("b",board).setViewName("board/boardDetailView");
		}else {
			throw new CommonException("게시물 상세조회에 실패했습니다.");
		}
		
		return mv;
	}
	
	@RequestMapping("delete.bo")
	public String deleteBoard(int bno, String fileName, HttpServletRequest request, Model model) {
		
		int result = boardService.deleteBoard(bno); 
		
		if(result>0) {
			if(!fileName.equals("")) {
				deleteFile(fileName,request);
				
			}
			
			return "redirect:list.bo";
		}else {
			throw new CommonException("게시물 삭제 실패");
		}
	}

	private void deleteFile(String fileName, HttpServletRequest request) {
		
		String resources = request.getSession().getServletContext().getRealPath("resources");
		String savePath = resources+"\\upload_files\\";
		
		File deleteFile = new File(savePath+fileName);
		deleteFile.delete();
	}
	
	@RequestMapping("updateForm.bo")
	public ModelAndView updateForm(int bno, ModelAndView mv) {
		
		Board b = boardService.selectBoard(bno);
		mv.addObject("b",b).setViewName("board/boardUpdateForm");
		return mv;
	}
	
	/*
	 * 1. 기존의 첨부파일 X, 새로 첨부된 파일 X 	
	 * 	  --> originName : null, changeName : null
	 * 
	 * 2. 기존의 첨부파일 X, 새로 첨부된 파일 O		
	 * 	  --> 서버에 업로드 후 
	 * 	  --> originName : 새로첨부된파일원본명, changeName : 새로첨부된파일수정명
	 * 
	 * 3. 기존의 첨부파일 O, 새로 첨부된 파일 X		
	 * 	  --> originName : 기존첨부파일원본명, changeName : 기존첨부파일수정명
	 * 
	 * 4. 기존의 첨부파일 O, 새로 첨부된 파일 O  
	 * 	  --> 서버에 업로드 후	
	 * 	  --> originName : 새로첨부된파일원본명, changeName : 새로첨부된파일수정명
	 */
	
	
	@RequestMapping("update.bo")
	public ModelAndView updateBoard(Board board, ModelAndView mv, HttpServletRequest request,
									@RequestParam(value="reUploadFile",required=false)MultipartFile file) {
		
		if(!file.getOriginalFilename().equals("")) {//새로 업로드된 파일이 존재하는 경우
			if(board.getChangeName() != null) { //기존 업로드 파일이 존재하는 경우
				deleteFile(board.getChangeName(),request); //대체를 위해 기존 업로드 파일을 삭제
			}
			
			String changeName = saveFile(file,request); //새로 업로드된 파일을 서버에 업로드
			board.setOriginName(file.getOriginalFilename());
			board.setChangeName(changeName);
		}
		
		int result = boardService.updateBoard(board);
		if(result>0) {
			mv.addObject("b",board).setViewName("redirect:list.bo");
			return mv;
		}else {
			throw new CommonException("게시물 수정 실패");
		}
	}
	
	@RequestMapping("topList.bo")
	public void boardTopList(HttpServletResponse response) throws JsonIOException, IOException{
		ArrayList<Board> list = boardService.selectTopList();
		
		response.setContentType("application/json; charset=UTF-8");
		new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(list,response.getWriter());
	}
	
	
	@RequestMapping("rinsert.bo")
	@ResponseBody
	public String insertReply(Reply r) {
		
		int result = boardService.insertReply(r);
		
		return String.valueOf(result);
	}
	
	@RequestMapping(value="rlist.bo", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String selectReplyList(int bno) {
		
		ArrayList<Reply> list = boardService.selectReplyList(bno);
		
		return new GsonBuilder().setDateFormat("yyyy년MM월dd일HH:mm:ss").create().toJson(list);
	}
	
}
