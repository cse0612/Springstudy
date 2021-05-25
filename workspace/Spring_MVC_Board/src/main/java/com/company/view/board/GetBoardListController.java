package com.company.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.company.Spring_MVC_Board.board.BoardDAO;
import com.company.Spring_MVC_Board.board.BoardDO;

public class GetBoardListController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("글 목록 검색 처리");
		
		//1. 사용자입력 정보 추출 (검색 기능은 나중에 구현)

		String searchField = "";
		String searchText = "";
		
		System.out.println("searchCondition>>>>" + request.getParameter("searchCondition"));
		if(request.getParameter("searchCondition") != "" && request.getParameter("searchKeyword") != "") {
			searchField = request.getParameter("searchCondition");
			searchText = request.getParameter("searchKeyword");
		}
		
		// db연동 처리
		BoardDO boardDO = new BoardDO();
		BoardDAO boardDAO = new BoardDAO();
		List<BoardDO> boardList = boardDAO.getBoardList(searchField, searchText);
		
		//Model 정보를  ModelAndView "boardList" 변수에 저장하여 리턴
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList);	// Model 정보 저장
		mav.setViewName("redirect:getBoardList.jsp");	// view 정보 저장
		return mav;
	}

}
