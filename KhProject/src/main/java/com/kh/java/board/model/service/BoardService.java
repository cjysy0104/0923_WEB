package com.kh.java.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.java.board.model.dao.BoardDao;
import com.kh.java.board.model.vo.Attachment;
import com.kh.java.board.model.vo.Board;
import com.kh.java.board.model.vo.Category;
import com.kh.java.common.Template;
import com.kh.java.common.vo.PageInfo;

public class BoardService {

	private BoardDao bd = new BoardDao();
	
	public int selectListCount() {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int listcount = bd.sessionListCount(sqlSession);
		
		sqlSession.close();
		
		return listcount;
	}

	public List<Board> selectBoardList(PageInfo pi) {

		SqlSession sqlSession = Template.getSqlSession();
		
		List<Board> boards = bd.selectBoardList(sqlSession, pi);
		
		sqlSession.close();
		
		return boards;
	}

	public List<Category> selectCategory() {

		SqlSession sqlSession = Template.getSqlSession();
		
		List<Category> categories = bd.selectCategory(sqlSession);
		
		sqlSession.close();
		
		return categories;
	}

	public int insert(Board board, Attachment at) {

		SqlSession sqlSession = Template.getSqlSession();
		
		// INSERT 두번 수행
		// BOARD 테이블에 한 번 => 무조건
		int boardResult = bd.insertBoard(sqlSession, board);
		// ATTACHEMTN 테이블에 한 번 => 파일이 존재할 때만 가야함
		// keyProperty를 통해 필드에 no가 담겨져 있
		int atResult = 1;
		if(at != null) {
			at.setRefBno(board.getBoardNo());
			atResult = bd.insertAttachment(sqlSession, at);
		}
		//두 개의 DML구문을 하나의 트랜잭션으로 묶어서 처리
		if( boardResult * atResult > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		// 트랜잭션까지 끝내고 난 후 성공실패여부를 반환
		return (boardResult * atResult);
	}

	public Map<String, Object> selectBoard(int boardNo) {

		SqlSession sqlSession = Template.getSqlSession();
		
		// SELECT 두 번 하기 + 조회수 증가
		
		// 총 DB에 3번 가야함
		// SELECT BOARD
		// SELECT ATTACHMENT 우선순위 낮음
		// UPDATE BOARD => commit했을때만 select하기: 먼저구현
		
		int result = bd.increaseCount(sqlSession, boardNo);
		
		
		if(result > 0) {
			sqlSession.commit();
			Board board = bd.selectBoard(sqlSession, boardNo);
			Attachment at = bd.selectAttachment(sqlSession, boardNo);
			Map<String, Object> map = new HashMap();
			//System.out.println(board);
			//System.out.println(at);
			 map.put("board", board);
			 map.put("at", at);
			 
						
			sqlSession.close();
			
			return map;
		}
		
		return null;
	}

	public int deleteBaord(Board board) {
		
		SqlSession sqlSession = Template.getSqlSession();
		Attachment at = bd.selectAttachment(sqlSession, board.getBoardNo().intValue());
		int result = bd.deleteBoard(sqlSession, board);
		
		int result2 = 1;
		if(at != null) {
			result2 = bd.deleteAttachment(sqlSession, board.getBoardNo());
		}
		
		if(result * result2 > 0	) {
			
			sqlSession.commit();
			
			return 	(result * result2);
		}		
		
		return 0;
	}
	

}
