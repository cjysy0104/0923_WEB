package com.develog.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.develog.member.model.service.MemberService;
import com.develog.member.model.vo.Member;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//POST
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Member member = new Member();
		member.setUserId(userId);
		member.setUserPwd(userPwd);
		
		Member loginUser = new MemberService().login(member);
		
		if(loginUser != null) {
			// 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", loginUser);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
			System.out.println(loginUser);
			System.out.println(session);
		} else {
			// 로그인 실패
			request.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
