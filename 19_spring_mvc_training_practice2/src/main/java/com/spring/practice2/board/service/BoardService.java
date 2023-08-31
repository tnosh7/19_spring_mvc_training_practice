package com.spring.practice2.board.service;

import java.util.List;

import com.spring.practice2.board.dto.BoardDTO;

public interface BoardService {
	
	public void addBoard(BoardDTO boardDTO) throws Exception ;
	public List<BoardDTO> getBoardList()throws Exception ;
	public BoardDTO getBoardDetail(long boardId)throws Exception ;
	public boolean checkAuthenticationUser(BoardDTO boardDTO)throws Exception;
	public void getModifyBoard(BoardDTO boardDTO)throws Exception;
	public void deleteBoard(long boardId)throws Exception;

}