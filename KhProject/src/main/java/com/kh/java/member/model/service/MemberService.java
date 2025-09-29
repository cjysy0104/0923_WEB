package com.kh.java.member.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.java.common.Template;
import com.kh.java.member.model.dao.MemberDao;
import com.kh.java.member.model.vo.Member;

public class MemberService {

	private MemberDao md = new MemberDao();

	public Member login(Member member) {
		// 로그인 처리 -> DAO에 보내서 있나없나 -> 결과값 반환

		SqlSession sqlSession = Template.getSqlSession();
		
		
		Member loginMember = md.login(sqlSession, member);
		
		sqlSession.close();

		return loginMember;
	}
	
	// 로그인 유효성검사 validateMember(member); 비즈니스로직
	public void validatemember(Member member) {
		String pattern = "^[a-zA-Z0-9]{4,20)$";

		if (member.getUserId() == null || member.getUserId().trim().isEmpty()) {
			return;
		}
		if (!member.getUserId().matches(pattern)) {
			return;
		}
		
	}
	// 비밀번호 검증 로직: 하나의 클래스는 하나의 기능만 담당해하므로 빼서 다른파일 사용해야함

	public int signUp(Member member) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = md.signUp(sqlSession, member);
		
		if(result > 0) {
			sqlSession.commit();
		}
		
		sqlSession.close();
		
		return result;
	}

	public int updateMember(Map<String, String> map) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = md.updateMember(sqlSession, map);
		
		if(result > 0) {
			sqlSession.commit();
		}
		
		sqlSession.close();
		
		return result;
	}

	public int deleteMember(Member member) {

		SqlSession sqlSession = Template.getSqlSession();
		
		int result = md.deleteMember(sqlSession, member);
		
		if(result > 0) {
			sqlSession.commit();
		}
		
		sqlSession.close();
		
		return result;
	}

	public int updatePwd(Map<String, String> map) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = md.updatePwd(sqlSession, map);
		
		if(result > 0) {
			sqlSession.commit();
		}
		
		sqlSession.close();
		
		return result;
	}
}
