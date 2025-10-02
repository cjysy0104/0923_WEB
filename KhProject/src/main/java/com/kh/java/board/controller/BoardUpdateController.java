package com.kh.java.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kh.java.board.model.service.BoardService;
import com.kh.java.board.model.vo.Attachment;
import com.kh.java.board.model.vo.Board;
import com.kh.java.common.MyRenamePolicy;
import com.kh.java.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/update.board")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardUpdateController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		if (ServletFileUpload.isMultipartContent(request)) {

			int maxSize = 1024 * 1024 * 10;
			String savePath = request.getServletContext().getRealPath("/resources/board_upfiles");

			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
					new MyRenamePolicy());
			
			// 파일 서버에 업로드하기 끝

			// KH_BOARD UPDATE
			// KH_ATTACHMENT

			// case 1. 첨부파일이 없음 => BOARD UPDATE + AT X
			// case 2. 첨부파일 O, 기존 첨부파일 O => BOARD UPDATE + AT UPDATE
			// case 3. 첨부파일 O, 기존 첨부파일 X => BOARD UPDATE + AT "INSERT"

			// 값뽑기
			String category = multiRequest.getParameter("category");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			Long boardNo = Long.parseLong(multiRequest.getParameter("boardNo"));
			
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("userInfo");

			Board board = new Board();
			board.setCategory(category);
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setBoardNo(boardNo);
			board.setBoardWriter(String.valueOf(member.getUserNo()));

			// Attachment객체 선언
			// 실제 첨부파일 존재할 경우에만 => 객체생성
			Attachment at = null;

			if (multiRequest.getOriginalFileName("reUpfile") != null) {

				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("reUpfile"));
				at.setChangeName(multiRequest.getFilesystemName("reUpfile"));
				at.setFilePath("resources/board_upfiles");

				// INSERT / UPDATE
				// INSERT : 이 첨부파일은 어떤 게시글에 달리는가?
				// UPDATE : 원래 있던 파일이 몇 행이냐?

				if (multiRequest.getParameter("fileNo") != null) {
					// 새로운 첨부파일이 있음 + 원본파일도 있었음
					// ATTACHMENT => UPDATE => 원본파일번호 필요
					// 기존 파일이 갖고있던 fileNo at의 fileNo필드에 담아줄것

					at.setFileNo(Long.parseLong(multiRequest.getParameter("fileNo")));
					// 기존 첨부파일 삭제
					new File(savePath + "/" + multiRequest.getParameter("changeName")).delete();

				} else {
					// 새로운 첨부파일이 있음 + 원본파일은 없었음
					// ATTACHMENT => INSERT
					// 어떤 게시글으 ㅣ첨부파일인지 (REF_BNO)
					at.setRefBno(boardNo);
				}
			}

			// 요청처리
			// UPDATE 1
			// UPDATE 2
			// UPDATE 1 + INSERT1
			int result = new BoardService().update(board, at);
			
			if(result > 0) {
				session.setAttribute("alertMsg", "수정 성공");
				
				response.sendRedirect(request.getContextPath() + "/detail.board?boardNo=" + boardNo);
			} else {
				request.setAttribute("msg", "수정  실패");
				request.getRequestDispatcher("/WEB-INF/views/common/result_page.jsp")
						.forward(request, response);
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
