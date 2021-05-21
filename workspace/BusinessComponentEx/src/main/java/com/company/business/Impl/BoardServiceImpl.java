package com.company.business.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.business.board.BoardDO;
import com.company.business.board.BoardService;
import com.company.business.common.LogAdvice;

/*
 * 비즈니스 로직을 처리하는 클래스에 붙이는 @Service 어노테이션을 붙인다.
 */
@Service("boardService")
public class BoardServiceImpl implements BoardService{
	/**
	 * @Autowired 어노테이션은 주로 멤버변수 위에 설정하여 해당 타입의 객체를 찾아서 자동으로 할당한다
	 * 즉 변수의 타입을 기준으로 객체를 검색하여 의존성 주입(DI)을 처리한다.
	 */
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public void insertBoard(BoardDO boardDO) {
		boardDAO.insertBoard(boardDO);
	}

	@Override
	public void updateBoard(BoardDO boardDO) {
		boardDAO.updateBoard(boardDO);
	}

	@Override
	public void deleteBoard(BoardDO boardDO) {
		boardDAO.deleteBoard(boardDO);
	}

	@Override
	public List<BoardDO> getBoardList(BoardDO boardDO) {
		return boardDAO.getBoardList(boardDO);
	}

	@Override
	public void getBoard(BoardDO boardDO) {
		boardDAO.getBoard(boardDO);
	}
}
