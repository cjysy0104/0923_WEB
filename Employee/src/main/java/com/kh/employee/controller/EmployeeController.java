package com.kh.employee.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.employee.model.service.EmployeeService;
import com.kh.employee.model.vo.Employee;

@WebServlet("/insert.do")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String empNo = request.getParameter("empNo");
		String jobCode = request.getParameter("jobCode");
		String salLevel = request.getParameter("salLevel");
		
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		emp.setEmpNo(empNo);
		emp.setJobCode(jobCode);
		emp.setSalLevel(salLevel);
		
		int result = new EmployeeService().insertEmp(emp);
		
		if(result > 0) {
			request.setAttribute("emp", emp);
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			
			view.forward(request, response);
		} else {
			response.getWriter().append("fail :(");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
