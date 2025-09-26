package com.kh.employee.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.employee.model.vo.Employee;

public class EmployeeDao {

	public int insertEmp(SqlSession session, Employee emp) {

		return session.insert("employeeMapper.insertEmp", emp);
	}

	public List<Employee> selectAll(SqlSession session) {

		return session.selectList("employeeMapper.selectAll");
	}

}
