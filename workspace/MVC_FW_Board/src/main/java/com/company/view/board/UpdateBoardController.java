package com.company.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.MVC_FW_Board.board.BoardDAO;
import com.company.MVC_FW_Board.board.BoardDO;
import com.company.view.controller.Controller;

public class UpdateBoardController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//1. 입력 폼(View page)에서 넘어온 사용자 입력 정보 추출
		String seq = request.getParameter("seq");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		//2. BoardDO 클래스(Bean 클래스) 객체 생성하여 입력값들을 통해 초기화한다
		BoardDO boardDO = new BoardDO();
		boardDO.setSeq(Integer.parseInt(seq));
		boardDO.setTitle(title);
		boardDO.setWriter(writer);
		boardDO.setContent(content);
		
		//3.BoardDAO 클래스(Model 클래스) 객체 생성후 게시글 등록 메소드 호출
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.updateBoard(boardDO);
		return "getBoardList.do";
	}

}
