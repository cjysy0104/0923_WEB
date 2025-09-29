package com.kh.java.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그아웃을 구현해야함
		// 로그아웃이란?
		// session에 userInfo라는 Attribute속성을 null이거나 지우거나
		
		//request.getSession().removeAttribute("userInfo"); : removeAttribute() - 속성제거 메서드
		
		// session을 만료시키는 방법(==무효화한다)
		request.getSession().invalidate();
		
		// 응답데이터? => 웰컴파일 돌려주기
		// sendRedirect()
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
