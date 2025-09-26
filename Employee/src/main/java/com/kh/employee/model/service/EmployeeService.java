package com.kh.employee.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.employee.model.dao.EmployeeDao;
import com.kh.employee.model.vo.Employee;
import com.kh.employee.common.Template;

public class EmployeeService {

	private EmployeeDao ed = new EmployeeDao();
	
	public int insertEmp(Employee emp) {

		SqlSession session = Template.getSqlSession();
		
		int result = ed.insertEmp(session, emp);
		
		if(result > 0) {
			session.commit();
		} 
		
		session.close();
		
		return result;
	}

	public List<Employee> selectAll() {

		SqlSession session = Template.getSqlSession();
		
		List<Employee> empList = ed.selectAll(session);
		
		session.close();
		
		return empList;
	}


}
