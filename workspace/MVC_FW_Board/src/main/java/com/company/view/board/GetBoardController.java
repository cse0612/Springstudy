package com.company.view.board;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.MVC_FW_Board.board.BoardDAO;
import com.company.MVC_FW_Board.board.BoardDO;
import com.company.view.controller.Controller;

public class GetBoardController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("게시판 상세보기 서블릿처리!");
		
		//1. 게시글 번호 가져오기
		String seq = request.getParameter("seq");
		
		//2. BoardDO클래스 생성 후 초기화
		BoardDO boardDO = new BoardDO();
		boardDO.setSeq(Integer.parseInt(seq));	//정수변환후 저장
		
		//3. BoardDAO 클래스 생성 후 '게시글 상세보기' 메서드 조회
		BoardDAO boardDAO = new BoardDAO();
		BoardDO board = boardDAO.getBoard(boardDO);

		request.setAttribute("board", board);
		
		return "getBoard";
	}
}
