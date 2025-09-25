package com.kh.java.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.java.member.model.service.MemberService;
import com.kh.java.member.model.vo.Member;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * <HttpServletRequest, HttpServletResponse>
		 * 
		 * - request : 서버로 요청 보낼 때의 정보(요청 시 전달값, 요청 전송방식, 사용자정보)
		 * - response : 요청에 대한 응답데이터를 만들 때 사용
		 */
		// 절차 
		// 1) 요청방식 GET? POST? => POST면 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청 시 전달 값 있나? => POST는 무조건 있음 
		// request.getParameter("키값") : String
		// request.getParameter("키값") : String[]
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		//System.out.println(userId);
		//System.out.println(userPwd);
		
		// 3) 데이터 가공
		Member member = new Member();
		member.setUserId(userId);
		member.setUserPwd(userPwd);
		
		// 4) 요청처리 - Service 호출
		Member loginMember = new MemberService().login(member);
		// 성공했을 경우: 조회성공한 컬럼값을 필드에 담은 멤버객체 주소값
		// 실패했을 경우: null
		
		//System.out.println(loginMember);
		
		// 5) 결과 출력
			// 1. 속성추가 => requset setAttribute()
			// 2. 응답뷰 지정 => RequestDispatcher 뷰 지정
			// 3. forward() 호출
		
		// 로그인 성공 / 실패 여부에 따라서 다른 결과 출력
		if(loginMember != null) {
			// 성공한 경우
			
			// 사용자의 정보를 앞단에 넘기기
			// 로그인한 회원정보를 로그아웃하거나
			// 브라우저 종료하기 전까진 계속 유지하고 사용할 것이기 떄문에
			// session에 담기
			
			// 1. session Attribute 사용자 정보 추가
			// session 타입: HttpSession
			// => 현재 요청보내는 Client의 Session : request.getSession();
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", loginMember);
			
			// 2. RequestDispatcher get해오기
			request.getRequestDispatcher("/index.jsp")
					.forward(request, response);
			
			//System.out.println(session.getAttribute("userInfo"));
			
			
			
			
		} else {
			// 실패한 경우 : 로그인을 실패했다 forward하고 실패패이지로 ㄱㄱ
			request.setAttribute("msg", "로그인에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/result_page.jsp")
			.forward(request, response);
		}
		
		
		// 성공한 경우 :
		// 1. 로그인된 사용자의 정보를 어딘가에 담을 것(application, session, request, page)
		/*
		 * 1) application : 웹 애플리케이션 전역에서 사용 가능
		 * 					(일반 자바 클래스에서 값을 사용할 수 있음)
		 * 
		 * 2) session : 모든 JSP와 Servlet에서 꺼내 쓸 수 있음 
		 * 				단, session에 값이 지워지기 전까지
		 * 				세션 종료 시점: 브라우저 종료, 서버 멈춤, 코드로 지움
		 * 
		 * 3) request: 해당 request를 포워딩한 응답 JSP에서만 쓸 수 있음
		 * 				요청부터 응답까지만
		 * 
		 * 4) page: 값을 담은 JSP에서만 쓸 수 있음
		 * 
		 * => session, request 많이 사용함
		 * 이번 로그인 성공은 session 사용
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
