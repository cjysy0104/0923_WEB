package com.kh.java.board.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
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

@WebServlet("/insert.board")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardInsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// encoding
		request.setCharacterEncoding("UTF-8");
		
		// get Parameter
		/*
		String title = request.getParameter("title");
		System.out.println(title);
		*/
		// => form태그 요청 시 multipart/form-data형식으로 전송한 경우
		// request.getParameter로는 값을 뽑을 수 없다.
		
		// 0. multi part방식 잘 왔는가?
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// multipart => 파일을 업로드하겠다는 의도
			// 1.1. 전송파일 용량 제한
			/*
			 * * 단위정리
			 * 
			 * bit x 8 => Byte
			 * Byte x 2^10 => KB(kilo Byte)
			 * KByte x 2^10 => MB(mega Byte)
			 * MByte x 2^10 => GB(giga Byte)
			 *  ~~~ 		=> TB(Tera Byte)
			 *  ~~~			=> PB(Peta Byte)
			 */
			int maxSize = 1 * 1024 * 1024 * 100;
			
			// 1.2. 서버 폴더 경로 알아내기: (webapp/resources/board_upfiles에 올릴 예정)
			// pageContext < HttpServeltRequest < HttpSession < ServletContext: 프로젝트(애플리케이션) 전체
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext();
			String savePath = application.getRealPath("/resources/board_upfiles");
			//System.out.println(savePath);
			// 장점
			// 동적으로 실제 경로 확인
			// 서버 환경에 관계 없이 동작
			// 단점
			// WAR파일 배포 시 파일이 사라질 수 있음
			
			// 2. 파일 업로드하기
			// 기존 a.jpg가 있는데 a.jpg가 업로드되면 예외 or 덮어씌어짐 > cos~.jar lib: 같은 파일이 올라오면 이름을 바꿔줌
			
			/*
			 * HttpServletRequest
			 * 
			 * [ 표현법 ]
			 * 
			 * MultipartRequest multiRequest = new MultiRequest(request, 저장경로, 용량제한, 인코딩, 파일명을 수정해주는 객체);
			 * 
			 * Multipart객체를 생성하면 파일이 업로드!
			 * 
			 * 사용자가 올린 파일명은 이름을 바꿔서 업로드하는게 일반적인 관례
			 * 
			 * Q)파일명을 왜 바꾸나?
			 * A)똑같은 파일명 있을 수 있다.
			 *   파일명에 한글 / 특수문자 / 공백문자 포함경우 서버에 따라 문제가 발생
			 */
			
			MultipartRequest multiRequest = new MultipartRequest(request, 
																 savePath,
																 maxSize,
																 "UTF-8",
																 new MyRenamePolicy()
																 );
			// -- 파일업로드--
			// BOARD테이블에 INSERT하기
			
			// 다시 2. 값뽑기
			String title = multiRequest.getParameter("title");
			//System.out.println(title);
			String content = multiRequest.getParameter("content");
			String category = multiRequest.getParameter("category");
			
			Long userNo = ((Member)session.getAttribute("userInfo")).getUserNo();
			
			// 3. 데이터 가공
			Board board = new Board();
			board.setBoardTitle(title);
			board.setBoardContent(content);
			board.setCategory(category);
			board.setBoardWriter(String.valueOf(userNo));
			
			// 3.2 첨부파일의 경우 => 선택적
			Attachment at = null;
			
			// 첨부파일의 유무를 파악: 앞단 속성값
			//System.out.println(multiRequest.getOriginalFileName("upFile"));
			// 첨부파일 있다면 "원본파일명" / 없다면 null 반환
			
			if(multiRequest.getOriginalFileName("name")!=null) {
				// 첨부파일이 있다 => VO로 만들기
				at = new Attachment();
				
				//originName
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));
				
				//changeName
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				
				//filePath
				at.setFilePath("resuources/board_upfiles");
				
				// 4) 요청처리 Service호출
				new BoardService().insert(board, at);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
