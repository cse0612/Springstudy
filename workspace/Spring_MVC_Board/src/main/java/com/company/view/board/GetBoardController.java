package com.company.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.company.Spring_MVC_Board.board.BoardDAO;
import com.company.Spring_MVC_Board.board.BoardDO;

public class GetBoardController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("게시판 상세보기 처리");
		
		//1. 게시글 번호 
		String seq = request.getParameter("seq");
		
		//2. DO클래스 생성 초기화
		BoardDO boardDO = new BoardDO();
		boardDO.setSeq(Integer.parseInt(seq));	
		
		//3. DAO클래스 getBoard() 호출
		BoardDAO boardDAO = new BoardDAO();
		BoardDO board = boardDAO.getBoard(boardDO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("getBoard");
		mav.addObject("board", board);
		return mav;
		
	}

		
}
