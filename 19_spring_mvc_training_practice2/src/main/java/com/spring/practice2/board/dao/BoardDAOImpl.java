package com.spring.practice2.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.practice2.board.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertBoard(BoardDTO boardDTO) throws Exception {
		sqlSession.insert("boardMapper.insertBoard", boardDTO);
	}

	@Override
	public List<BoardDTO> selectListBoard() throws Exception {
		
		return sqlSession.selectList("boardMapper.selectListBoard");
	}

	@Override
	public BoardDTO selectOneBoard(long boardId) throws Exception {

		return sqlSession.selectOne("boardMapper.selectOneBoard", boardId);
	}

	@Override
	public void updateReadCnt(long boardId) throws Exception {
		sqlSession.update("boardMapper.updateReadCnt", boardId);
	}
}
