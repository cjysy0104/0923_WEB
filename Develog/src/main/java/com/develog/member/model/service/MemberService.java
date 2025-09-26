package com.develog.member.model.service;


import org.apache.ibatis.session.SqlSession;

import com.develog.common.Template;
import com.develog.member.model.dao.MemberDao;
import com.develog.member.model.vo.Member;

public class MemberService {
	
	private MemberDao md = new MemberDao();

	public Member login(Member member) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		Member loginUser = md.login(sqlSession, member);
		
		return loginUser;
	}

}
