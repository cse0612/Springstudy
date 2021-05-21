package com.company.view.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	
	@Override
	/**
	 * init()를 재정의한 이유
	 * 서블릿의 init()매서드는 서블릿 객체가 생성된 후에 맴버변수를 초기화하기 위해 자동으로 실행된다.
	 */
	public void init() throws ServletException {
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");
	}
	
	//생성자
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
		//1.클라이언트 요청 정보 추출
		//String url = "http://localhost:8080/MVC_FW_Board/login.do  요청하면
		String uri = request.getRequestURI();   				//  ->	/MVC_FW_Board/login.do 를 가져옴
		String path = uri.substring(uri.lastIndexOf("/"));  	//  substring(마지막'/' 포함된인덱스부터 ~ 마지막 인덱스)  -> /login.do
		System.out.println("DispatcherServlet.java > process() >  getRequestURI() : >>>> " + uri + " --> path:" + path);
		
		//2. HandlerMapping을 통해서 Key값을 통해 path에 해당하는 Controller를 검색
		Controller ctrl = handlerMapping.getController(path);
		
		//3. 검색된 Controller를 실행후 리턴값(String)을 받아온다
		String viewName = ctrl.handleRequest(request, response);
		
		//4. ViewResolver를 통해서 viewName에 해당하는 페이지로 포워딩
		String view = null;
		if(viewName.contains(".do")) {
			view = viewName;
		} else {
			view = viewResolver.getView(viewName);
		}
		
		System.out.println("view page >>>>>> " + view);
		
		//5. 검색된 페이지로 이동
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
