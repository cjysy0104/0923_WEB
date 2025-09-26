package com.kh.employee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.employee.model.service.EmployeeService;
import com.kh.employee.model.vo.Employee;

@WebServlet("/selectList.emp")
public class EmployeeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> empList = new EmployeeService().selectAll();
		request.setAttribute("emps", empList);
		request.getRequestDispatcher("/WEB-INF/views/list.jsp")
		   .forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
