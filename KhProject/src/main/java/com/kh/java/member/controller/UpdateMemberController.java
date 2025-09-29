package com.kh.java.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.java.member.model.service.MemberService;
import com.kh.java.member.model.vo.Member;

@WebServlet("/update.me")
public class UpdateMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateMemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// post
		request.setCharacterEncoding("UTF-8");
		
		// parameter
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		
		// data processing
		/*
		Map<String, String> map = Map.of(
										"userName", userName,
										"email", email);
										
		//System.out.println(map);
		*/
		
		HttpSession session =  request.getSession();
		Member member = (Member)session.getAttribute("userInfo");
		Long userNo = ((Member)session.getAttribute("userInfo")).getUserNo();
		
		Map<String, String> map = Map.of(
									"userName", userName,
									"email", email,
									"userNo", String.valueOf(userNo));
		// service call
		int result = new MemberService().updateMember(map);
			
		if(result > 0) {
			// 문제점
			// DB상 Update 성공
			// but, session의 userInfo 키값은
			// 로그인 당시 갱신했던 필드값이 그대로 존재
			// View단에 넘어오면 그 전 값들이 조회가 됨
			
			// => 목표: 1. DB가서 갱신된 회원정보 갖고와서 2. userInfo 덮어씌우기
			
			/*
			Member loginIfo = new MemberService().login(member);
			session.setAttribute("userInfo", loginIfo);
			// VIEW단을 위한 session 최신정보 갱신
			request.getRequestDispatcher("/WEB-INF/views/member/my_page.jsp")
			.forward(request, response);
			*/
			// sendRedirect
			// /kh/myPage
			session.setAttribute("alertMsg", "회원정보 수정 성공!");
			response.sendRedirect(request.getContextPath()+"/myPage");
		} else {
			request.setAttribute("msg", "정보수정 실패");
			request.getRequestDispatcher("/WEB-INF/views/common/result_page.jsp")
					.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
