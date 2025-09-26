package com.develog.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.develog.member.model.vo.Member;

public class MemberDao {

	public Member login(SqlSession sqlSession, Member member) {

		return sqlSession.selectOne("memberMapper.login", member);
	}

}
