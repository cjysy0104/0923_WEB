package com.kh.java.member.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.java.member.model.vo.Member;

public class MemberDao {

	public Member login(SqlSession sqlSession, Member member) {

		return sqlSession.selectOne("memberMapper.login", member);
	}

	public int signUp(SqlSession sqlSession, Member member) {

		return sqlSession.insert("memberMapper.signUp", member);
	}

	public int updateMember(SqlSession sqlSession, Map<String, String> map) {
		
		return sqlSession.update("memberMapper.updateMember", map);
	}

	public int deleteMember(SqlSession sqlSession, Member member) {

		return sqlSession.update("memberMapper.deleteMember", member);
	}

	public int updatePwd(SqlSession sqlSession, Map<String, String> map) {

		return sqlSession.update("memberMapper.updatePwd" , map);
	}

}
