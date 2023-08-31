package com.spring.practice2.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.practice2.board.dto.BoardDTO;
import com.spring.practice2.board.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/addBoard")
	public ModelAndView addBoard() throws Exception{
		return new ModelAndView("board/addBoard"); 
	}
	@PostMapping("/addBoard")
	@ResponseBody
	
	public String addBoard(@ModelAttribute BoardDTO boardDTO) throws Exception{
		
		boardService.addBoard(boardDTO);
		
		String jsScript = "<script>";
			   jsScript +="alert('Post Success');";
			   jsScript +="location.href='boardList';";
			   jsScript +="</script>";
		
	    return jsScript;
	}
	
	@GetMapping("/boardList")
	public ModelAndView boardList()throws Exception{ 
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardList");
		mv.addObject("boardList", boardService.getBoardList());
		return mv;
	}
	
	@GetMapping("/boardDetail")
	public ModelAndView boardDetail(@RequestParam("boardId") long boardId)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardDetail");
		mv.addObject("boardDetail", boardService.getBoardDetail(boardId));
		return mv;
		
	}
	@GetMapping("/authentication")
	@ResponseBody
	
	public String authentication(@ModelAttribute BoardDTO boardDTO, @RequestParam("menu") String menu)throws Exception {
		
		String jsScript = "";
		if (boardService.checkAuthenticationUser(boardDTO)) {
			if(menu.equals("update")) {
				jsScript = "<script>";
				jsScript += "location.href='modifyBoard?boardId="+boardDTO.getBoardId()+"';";
				jsScript += "</script>";
				
			}
			else if(menu.equals("delete")) {
				jsScript = "<script>";
				jsScript += "location.href='modifyBoard?boardId="+boardDTO.getBoardId()+"';";
				jsScript += "</script>";
			}
		}
		else {
			jsScript = "<script>";
			jsScript += "alert('Check your Id or Passwd');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
		}
		return jsScript;
	}
	
	@GetMapping("/modifyBoard")
	public ModelAndView modifyBoard(@ModelAttribute BoardDTO boardDTO, @RequestParam("boardId")long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/modifyBoard");
		mv.addObject("boardDTO", boardService.getBoardDetail(boardId));
		return mv;
	}
	
	@PostMapping("/modifyBoard")
	@ResponseBody
	public String getModifyBoard(@ModelAttribute BoardDTO boardDTO)throws Exception {
		
		boardService.getModifyBoard(boardDTO);
		String jsScript = "<script>";
			   jsScript +="alert('');";
			   jsScript +="location.href='boardList';";
			   jsScript +="</script>";
	    return jsScript;
	}	
	@GetMapping("/removeBoard")
	public ModelAndView removeBoard(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv= new ModelAndView();
		mv.setViewName("board/removeBoard");
		mv.addObject("boardId", boardId);
		return mv;
	}
	@PostMapping("/removeBoard")
	@ResponseBody
	
	public String postRemoveBoard(@RequestParam("boardId") long boardId) throws Exception{
		
		boardService.deleteBoard(boardId);
		String jsScript = "<script>";
			   jsScript +="alert('');";
			   jsScript +="location.href='boardList';";
			   jsScript +="</script>";
		return jsScript;
		
	}
}
