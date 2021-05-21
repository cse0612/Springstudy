package com.company.business.board;

public interface BoardService {
	//추상메서드
	// 글등록
	void insertBoard(BoardDO boardDO);
	
	//글 수정
	void updateBoard(BoardDO boardDO);
	
	//글 삭제
	void deleteBoard(BoardDO boardDO);
	
	//전체 게시글 목록조회
	List<BoardDO> getBoardList(BoardDO boardDO);
}
