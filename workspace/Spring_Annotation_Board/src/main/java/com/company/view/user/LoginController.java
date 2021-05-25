package com.company.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.Spring_Annotation_Board.user.UserDAO;
import com.company.Spring_Annotation_Board.user.UserDO;

@Controller
public class LoginController  {

	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserDO userDO, UserDAO userDAO, HttpSession session) {
		
		
		System.out.println("로그인처리");
		UserDO user = userDAO.getUser(userDO);
		if(user != null) {
			session.setAttribute("userName", user.getName());
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}
	}

	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login(UserDO userDO) {
		System.out.println("로그인화면으로 이동");
		userDO.setId("test");
		userDO.setPassword("test1234");
		return "login.jsp";
	}
	
}
