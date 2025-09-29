package com.kh.java.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.kh.java.member.model.service.MemberService;
import com.kh.java.member.model.vo.Member;

@WebServlet("/members")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUpController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST => 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// request 객체로부터 요청 시 전달값 
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		
		// 데이터 가공
		Member member = new Member();
		member.setUserId(userId);
		member.setUserPwd(userPwd);
		member.setUserName(userName);
		member.setEmail(email);
		
		HttpSession session = request.getSession();
		
		// 서비스 호출
		int result = new MemberService().signUp(member);
		
		// 성공여부에 따른 응답 뷰 지정
		if(result > 0) {
			//성공
			session.setAttribute("alertMsg", "회원가입 성공!");
			response.sendRedirect(request.getContextPath());
			
		} else {
			//실패
			request.setAttribute("msg", "회원가입에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/result_page.jsp")
			.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
