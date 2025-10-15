package com.kh.java.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax1.do")
public class AjaxController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxController1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("doGet메서드 호출!");
		
		// 요청 시 전달 값이 있을 때
		// 값 뽑기
		// request.getParameter()
		String value = request.getParameter("value");
		System.out.println("요청 시 전달값 : " + value);
		
		// 응답
		// 응답할 값이 있다 == scope.setAttribute("key", "val");
		// 응답화면을 만들어낼 JSP로 포워딩
		
		// 응답데이터
		String responseData = "요청처리 성공!";
		
		// 1) 응답데이터 정보 설정 ★★★★★
		response.setContentType("text/html; charset=UTF-8");
		
		// 2) 출력 스트림을 이용해 응답
		response.getWriter().print(responseData);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
