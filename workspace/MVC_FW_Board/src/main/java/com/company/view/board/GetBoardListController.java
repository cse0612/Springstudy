package com.company.view.board;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.MVC_FW_Board.board.BoardDAO;
import com.company.MVC_FW_Board.board.BoardDO;
import com.company.view.controller.Controller;

public class GetBoardListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		//검색 대상 및 입력값 저장할 변수
		String searchField = "";
		String searchText = "";
		
		System.out.println("searchCondition>>>>" + request.getParameter("searchCondition"));
		if(request.getParameter("searchCondition") != "" && request.getParameter("searchKeyword") != "") {
			searchField = request.getParameter("searchCondition");
			searchText = request.getParameter("searchKeyword");
		}
		
		//BoardDAO 클래스 객체 생성후 매서드 호출
		BoardDAO boardDAO = new BoardDAO();
		List<BoardDO> boardList = boardDAO.getBoardList(searchField, searchText);
		
		//[중요] 검색 결과를 request객체에 저장하고 포워딩
		//HttpSession session = request.getSession();
		//session.setAttribute("boardList", boardList);
		request.setAttribute("boardList", boardList);
		//RequestDispatcher rd = request.getRequestDispatcher("getBoardList.jsp");
		
		return "getBoardList";
	}

}
