package com.kh.java.member.model.vo;

import java.sql.Date;

/*
	USER_NO NUMBER PRIMARY KEY,
	USER_ID VARCHAR2(30) UNIQUE NOT NULL,
	USER_PWD VARCHAR2(30) NOT NULL,
	USER_NAME VARCHAR2(20) NOT NULL,
	EMAIL VARCHAR2(30) NOT NULL,
	ENROLL_DATE DATE DEFAULT SYSDATE,
	MODIFY_DATE DATE DEFAULT SYSDATE,
	STATUS CHAR(1) DEFAULT 'Y' CHECK(STATUS IN ('Y', 'N'))
 */
public class Member {

	// int vs long : int는 범위 한계 존재
	// long vs Long: 호환성 등으로 Long

	private Long userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private Date enrollDate;
	private Date modifyDate;
	private String status;

	public Member() {
		super();
	}

	public Member(Long userNo, String userId, String userPwd, String userName, String email, Date enrollDate,
			Date modifyDate, String status) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", email=" + email + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", status="
				+ status + "]";
	}

}
