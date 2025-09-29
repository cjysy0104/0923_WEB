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

@WebServlet("/updatePwd.me")
public class UpdatePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePasswordController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//post
		request.setCharacterEncoding("UTF-8");
		//parameter
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("changePwd");
		//for identify
		Member member = (Member)session.getAttribute("userInfo");
		Long userNo = ((Member)session.getAttribute("userInfo")).getUserNo();
		//data process
		Map<String, String> map = Map.of("userPwd", userPwd,
										 "updatePwd", updatePwd,
										 "userNo", String.valueOf(userNo));
		//call service
		int result = new MemberService().updatePwd(map);
		
		if(result > 0) {
			member.setUserPwd(updatePwd);
		}
		
		session.setAttribute("alertMsg", result > 0 ? "변경 성공!" : "변경 실패!");
		
		// 결과 상관없이 마이페이지로 응답
		response.sendRedirect(request.getContextPath() + "/myPage");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
