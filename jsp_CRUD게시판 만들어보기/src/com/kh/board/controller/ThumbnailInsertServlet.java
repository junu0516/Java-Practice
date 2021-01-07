package com.kh.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ThumbnailInsertServlet
 */
@WebServlet("/insert.th")
public class ThumbnailInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if(ServletFileUpload.isMultipartContent(request)) {//true
			int maxSize = 10*1024*1024;
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			String savePath = resources + "\\board_upfiles\\";
			System.out.println("savePath : "+savePath);
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			Board b = new Board();
			b.setBoardTitle(multiRequest.getParameter("title"));
			b.setBoardContent(multiRequest.getParameter("content"));
			b.setBoardWriter(multiRequest.getParameter("writer"));
			
			ArrayList<Attachment> fileList = new ArrayList<>();
			for(int i=1;i<=4;i++) {
				String name = "file"+i;
				if(multiRequest.getOriginalFileName(name)!=null) { //파일이 존재할 경우
					String originName = multiRequest.getOriginalFileName(name);
					String changeName = multiRequest.getFilesystemName(name);
					
					Attachment at = new Attachment();
					at.setFilePath(savePath);
					at.setOriginName(originName);
					at.setChangeName(changeName);
					
					fileList.add(at);
				}
			}
			
			int result = new BoardService().insertThumbnail(b,fileList);
			System.out.println("result : "+result+", insertThumbnail 확인");
			if(result>0) {
				response.sendRedirect("list.th");
				System.out.println("사진등록 성공");
			}else {
				for(int i=0;i<fileList.size();i++) {
					File failedFile = new File(savePath+fileList.get(i).getChangeName());
					failedFile.delete();
				}
				
				request.setAttribute("msg", "사진 등록 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
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
