package com.kh.employee.model.vo;

public class Employee {

	private String empId;
	private String empName;
	private String empNo;
	private String jobCode;
	private String salLevel;
	
	public Employee() {
		super();
	}

	public Employee(String empId, String empName, String empNo, String jobCode, String salLevel) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.jobCode = jobCode;
		this.salLevel = salLevel;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getSalLevel() {
		return salLevel;
	}

	public void setSalLevel(String salLevel) {
		this.salLevel = salLevel;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empNo=" + empNo + ", jobCode=" + jobCode
				+ ", salLevel=" + salLevel + "]";
	}
	
	
}
