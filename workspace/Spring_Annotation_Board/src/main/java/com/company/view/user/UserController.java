package com.company.view.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.Spring_Annotation_Board.user.UserDAO;
import com.company.Spring_Annotation_Board.user.UserDO;

@Controller
public class UserController {
	
	@RequestMapping("/insertUser.do")
	public String insertUser(UserDO userDO, UserDAO userDAO) {
		userDAO.insertUser(userDO);
		return "login.jsp";
	}
	
}
