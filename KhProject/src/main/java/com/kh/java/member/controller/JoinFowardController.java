package com.kh.java.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class JoinFowardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinFowardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 할 일?
		// 회원가입 양식 띄워주기 enroll_form.jsp가 응답하게 만들기
		// 서블릿에서 응답데이터 지정하는 방식 두 가지
		// 1. RequestDispatcher 이용해 forwarding
		// 2. response 이용해 sendRedirect
		
		request.getRequestDispatcher("/WEB-INF/views/member/enroll_form.jsp")
			   .forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
