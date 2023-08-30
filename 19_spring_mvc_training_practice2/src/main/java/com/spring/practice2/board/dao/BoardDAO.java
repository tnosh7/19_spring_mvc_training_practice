package com.spring.practice2.board.dao;

import java.util.List;

import com.spring.practice2.board.dto.BoardDTO;

public interface BoardDAO {
	public void insertBoard(BoardDTO boardDTO) throws Exception ;
	public List<BoardDTO> selectListBoard()throws Exception ;
	public BoardDTO selectOneBoard(long boardId)throws Exception ;
	public void updateReadCnt(long boardId)throws Exception ;
}
