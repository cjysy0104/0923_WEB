package com.kh.java.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.java.board.model.service.BoardService;
import com.kh.java.board.model.vo.Board;
import com.kh.java.common.vo.PageInfo;

@WebServlet("/boards")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// - 페이징 처리 -
		// 필요한 변수들
		int listCount; // 현재 일반게시판의 총 게시글 개수
		// => BOARD테이블에서 COUNT(*) (STATUS='Y' AND BOARD_TYPE=1)을 조회
		int currentPage; // 현재 사용자가 요청한 페이지
		// => request.getParameter("page")로 뽑아쓰기
		//String page = request.getParameter("page");
		//System.out.println(page);
		int pageLimit; // 페이지 하단에 보여질 최대 페이징 버튼 개수 => 5개
		int boardLimit;// 한 페이지에 보여질 게시글 개수 => 10개
		
		int maxPage; // 가장 마지막 페이지가 몇 번 페이지인지(총 페이지의 개수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작 수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝수
		
		// * listCount : 총 게시글의 수
		listCount = new BoardService().selectListCount();
		//System.out.println(listCount);
		currentPage = Integer.parseInt(request.getParameter("page"));
		//System.out.println(currentPage);
		
		pageLimit = 10;
		boardLimit = 10;
		
		
		maxPage = listCount/boardLimit;
		// * maxPage: 가장 마지막 페이지가 몇 번 페이지인지
		/*
		 * listCount, boardLimit 영향받음
		 * 
		 * - 공식구하기
		 * 	 단, boardLimit가 10이라고 가정
		 * 
		 * 		총개수  	한페이지  	나눗셈 결과 	마지막페이지
		 * 		100	 /	  10  = 	10			10
		 * 		107	 /	  10  = 	10			11
		 * 		111	 /	  10  = 	11			12
		 * 
		 * => 나눗셈(listCount/boardCount)의 결과를 올림처리 할 경우
		 * 	   maxPage가 나옴
		 * 
		 * staff1
		 * 1. listCount를 doubel 로 바꿔
		 * 2. listCount""
		 * 
		 * startPage = (currentPage-1) / pageLimit*pageLimit
		 * 
		 */
		startPage = (currentPage - 1) / pageLimit * pageLimit+1;
		// * endPage : 페이지 하단에 보여질 
		/*
		 * statPage, pageLimit에 영향받음
		 * 
		 * -ㄱ공식ㅇㄹ 생각해보자 startpage = w0u?
		 * 
		 * endPage = startPage + pageLimit -1;
		 */
		endPage = startPage * pageLimit - 1;
		// startPage가 1위라서ㅇ
		//	*startPage: "페이지 하단에 보여질 페이징 버튼 중 시작값"
		
		System.out.println(listCount);
		System.out.println(startPage);
		System.out.println(endPage);
		
		if(endPage> maxPage) {
			endPage = maxPage;
		}
		
		int offset = (currentPage-1)*boardLimit;
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit,
									startPage, endPage, maxPage, offset);
	
//		System.out.println(pi);
		
		List<Board> boards = new BoardService().selectBoardList(pi);
		
//		System.out.println(boards);
		
		// pi랑 boards 보내줘야함
		request.setAttribute("pi", pi);
		request.setAttribute("boards", boards);
		
		request.getRequestDispatcher("/WEB-INF/views/board/board_list.jsp")
			   .forward(request, response);
		//*star(int)
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
