package com.kh.java.board.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.java.board.model.service.BoardService;

@WebServlet("/detail.board")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 일단 KH_BOARD에서 게시글 내용 조회하고
		// 첨부파일은 KH_ATTACHMENT에서 조회해가야겠다.
		
		//GET방식
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// 가공 x
		
		// 요청처리 -> 서비스단으로 boardNo넘김
		
		
		Map<String, Object> map = new BoardService().selectBoard(boardNo);
		
		// 성공실패 + 중복처리
		
		if(map != null) {
		request.setAttribute("map", map);
		request.getRequestDispatcher("/WEB-INF/views/board/board_detail.jsp")
				.forward(request, response);
		} else {
			request.setAttribute("msg", "해당 게시글은 없거나 삭제되었습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/result_page.jsp")
			.forward(request, response);
		}
		
//		String path = "";
//		if(map != null) {
//			request.setAttribute("map", map);
//			path = "board/board_detail";
//		} else {
//			path = "/common/result_page.jsp";
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
