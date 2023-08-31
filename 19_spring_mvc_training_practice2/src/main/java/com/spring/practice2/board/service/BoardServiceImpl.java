package com.spring.practice2.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.practice2.board.dao.BoardDAO;
import com.spring.practice2.board.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void addBoard(BoardDTO boardDTO) throws Exception {
		
		boardDTO.setPasswd(bCryptPasswordEncoder.encode(boardDTO.getPasswd()));
		boardDAO.insertBoard(boardDTO);
		
	}

	@Override
	public List<BoardDTO> getBoardList() throws Exception {

		
		return boardDAO.selectListBoard();
	}

	@Override
	public BoardDTO getBoardDetail(long boardId) throws Exception {
		boardDAO.updateReadCnt(boardId);
		return boardDAO.selectOneBoard(boardId);
	}

	@Override
	public boolean checkAuthenticationUser(BoardDTO boardDTO) throws Exception {
		String rawPassword = boardDTO.getPasswd();
		String encodedPassword = boardDAO.selectOnePasswd(boardDTO.getBoardId());
		
		boolean isAuthentication = false;
		if (bCryptPasswordEncoder.matches(rawPassword, encodedPassword)) {
			isAuthentication = true;
		}
		
		return isAuthentication;
	}

	@Override
	public void getModifyBoard(BoardDTO boardDTO) throws Exception {
		boardDAO.updateBoard(boardDTO);
	}

	@Override
	public void deleteBoard(long boardId) throws Exception {
		boardDAO.delBoard(boardId);
	}
}
