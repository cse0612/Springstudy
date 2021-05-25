package com.company.view.controller;

import java.util.HashMap;
import java.util.Map;

import com.company.view.board.DeleteBoardController;
import com.company.view.board.GetBoardController;
import com.company.view.board.GetBoardListController;
import com.company.view.board.InsertBoardController;
import com.company.view.board.UpdateBoardController;
import com.company.view.user.LoginController;
import com.company.view.user.LogoutController;

//URL Request별 Controller 매핑 자료 구조
public class HandlerMapping {
	//객체 자료구조 
	private Map<String, Controller> mappings;
	
	//생성자
	public HandlerMapping() {
		//HashMAp 객체 생성
		mappings = new HashMap<String, Controller>();
		
		mappings.put("/login.do", new LoginController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
		mappings.put("/logout.do", new LogoutController());
	}

	//사용자 정의 매서드 
	public Controller getController(String path) {
		return mappings.get(path);	// 요청URL에 따라 Controller 객체 반환
	}
	
	
}
