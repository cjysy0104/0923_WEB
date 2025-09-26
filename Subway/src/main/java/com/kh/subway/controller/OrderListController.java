package com.kh.subway.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.subway.model.service.SubwayService;
import com.kh.subway.model.vo.Subway;

@WebServlet("/orderList.sandwich")
public class OrderListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	// GET? POST?
		// a태그 요청 == 무조건 GET방식
		
	// 1) 요청 시 전달 값?
		// X
		
	// 2) 데이터 가공
		// 전달값X므로 skip
		
	// 3) 요청 처리 -> Service단 호출
		List<Subway> orderList = new SubwayService().findAll();
		
		//System.out.println(orderList);
		request.setAttribute("orders", orderList);
		
		// 4) 응답 결과 출력
		request.getRequestDispatcher("/WEB-INF/views/list.jsp")
			   .forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
