package com.company.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.Model2_Board.board.BoardDAO;
import com.company.Model2_Board.board.BoardDO;
import com.company.Model2_Board.user.UserDAO;
import com.company.Model2_Board.user.UserDO;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DispatcherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String uri = "http://localhost:8080/Model2_Board/login.do  요청하면
		String uri = request.getRequestURI();   	// /Model2_Board/login.do 를 가져옴
		String path = uri.substring(uri.lastIndexOf("/"));  //  substring(마지막'/' 포함된인덱스부터 ~ 마지막 인덱스)
		
		if(path.equals("/login.do")) {
			System.out.println("login.do ................");
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			UserDO userDO = new UserDO();
			userDO.setId(id);
			userDO.setPassword(password);
			
			UserDAO userDAO = new UserDAO();
			UserDO user = userDAO.getUser(userDO);
			
			if(user != null) {
					HttpSession session = request.getSession();
					session.setAttribute("IdKey", id);
					System.out.println("로그인성공");
					response.sendRedirect("getBoardList.do");
			} else {
					System.out.println("로그인실패");
					response.sendRedirect("login.jsp");
			}
		}else if(path.equals("/getBoardList.do")) {
			String searchField = "";	//검색대상 객체 레퍼런스변수
			String searchText = "";		// 검색텍스트
			
			//System.out.println("searchCondition>>>>" + request.getParameter("searchCondition"));
			if(request.getParameter("searchCondition") != "" && request.getParameter("searchKeyword") != "") {
				searchField = request.getParameter("searchCondition");
				searchText = request.getParameter("searchKeyword");
			}
			
			//BoardDAO 클래스 객체 생성후 메서드 호출
			BoardDAO boardDAO = new BoardDAO();
			List<BoardDO> boardList = boardDAO.getBoardList(searchField, searchText);
			
			//[중요] 검색 결과를 request객체에 저장하고 포워딩
			//HttpSession session = request.getSession();
			//session.setAttribute("boardList", boardList);
			request.setAttribute("boardList", boardList);
			RequestDispatcher rd = request.getRequestDispatcher("getBoardList.jsp");
			rd.forward(request,response);
			
		}else if(path.equals("/getBoard.do")) {
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
			
			RequestDispatcher rd = request.getRequestDispatcher("getBoard.jsp");
			rd.forward(request,response);
		}else if(path.equals("/insertBoard.do")) {
			//1. 입력 폼에서 넘어온 사용자 입력 정보 추출
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");

			//BoardDO 클래스 객체 생성하여 입력값들을 필드(중간저장소)에 저장(초기화)한다
			BoardDO boardDO = new BoardDO();
			boardDO.setTitle(title);
			boardDO.setWriter(writer);
			boardDO.setContent(content);
			
			//3.BoardDAO 클래스 객체 생성후 게시글 등록 메소드 호출
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(boardDO);
			
			//포워딩
			response.sendRedirect("getBoardList.do");
		}else if(path.equals("/updateBoard.do")) {
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
			response.sendRedirect("getBoardList.do");
		}else if(path.equals("/deleteBoard.do")) {
			//1. 입력 폼(View page)에서 넘어온 사용자 입력 정보 추출
			String seq = request.getParameter("seq");
			//String writer = request.getParameter("writer");

			//2. BoardDO 클래스(Bean 클래스) 객체 생성하여 입력값들을 통해 초기화한다
			BoardDO boardDO = new BoardDO();
			boardDO.setSeq(Integer.parseInt(seq));
			//boardDO.setWriter(writer);
			
			//3.BoardDAO 클래스(Model 클래스) 객체 생성후 게시글 등록 메소드 호출
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(boardDO);
			
			//url 포워딩
			response.sendRedirect("getBoardList.do");
		}else if(path.equals("/logout.do")) {
			HttpSession session = request.getSession();
			session.invalidate();				//세션 삭제
			response.sendRedirect("login.jsp");
		}
	}
	
	
}
