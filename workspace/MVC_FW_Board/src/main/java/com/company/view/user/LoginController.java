package com.company.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.MVC_FW_Board.user.UserDAO;
import com.company.MVC_FW_Board.user.UserDO;
import com.company.view.controller.Controller;

public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//1. 사용자 입력 정보 추출
		System.out.println("HttpServletRequest >>>"+ request);
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		//2. DO클래스 객체 생성후 중간저장소에 초기화
		UserDO userDO = new UserDO();
		userDO.setId(id);
		userDO.setPassword(password);
		
		//3. DAO클래스 객체 생성 후 getUser() 매서드 호출
		UserDAO userDAO = new UserDAO();
		UserDO user = userDAO.getUser(userDO);

		if(user != null) {
			System.out.println("로그인성공");
			HttpSession session = request.getSession();
			session.setAttribute("IdKey", id);
			return "getBoardList.do";	// return type : String
		} else {
			System.out.println("로그인실패");
			return "login";
		}
	}
}
