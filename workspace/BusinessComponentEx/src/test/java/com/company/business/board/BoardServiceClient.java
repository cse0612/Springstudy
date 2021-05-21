package com.company.business.board;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class BoardServiceClient {
	@Test
	public void BoardServiceClientTest() {
		//1. 스프링 컨테이너 구동
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. 스프링 컨테이너로부터 필요한 객체를 요청(Lookup) 한다.
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		//3. 글 등록 기능 테스트
		BoardDO boardDO = new BoardDO();
		boardDO.setTitle("스프링테스트");
		boardDO.setWriter("홍길동");
		boardDO.setContent("테스트가 잘~되야 할텐데~");
		boardService.insertBoard(boardDO);
		
		//4. 글 목록 검색 기능 테스트
		List<BoardDO> boardList = boardService.getBoardList(boardDO);
		
		for(BoardDO board : boardList) {
			System.out.println("-->" + board.toString());
		}
		
		//5. Spring 컨테이너 종료
		container.close();
	}

}
