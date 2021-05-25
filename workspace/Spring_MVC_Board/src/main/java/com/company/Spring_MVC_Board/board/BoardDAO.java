package com.company.Spring_MVC_Board.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.company.Spring_MVC_Board.common.JDBCUtil;

public class BoardDAO {
	//DB 관련 변수 선언
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//전체 게시글 목록 조회 메서드 구현
	public List<BoardDO> getBoardList(String searchField, String searchText) {
		
		
		System.out.println("===> getBoardList() 기능 처리" );
		
		//결과값 반환할 listArray 객체 생성
		List<BoardDO> boardList = new ArrayList<BoardDO>();
		
		try {
			conn = JDBCUtil.getConnection();	//JDBCUtil 클래스의 메서드 호출을 통해 DB관련 객체 생성 
			String where ="";
			//검색인 경우에만  where절 추가
			if(searchField != null && searchText != null) {
				where = "where "+searchField+" like '%"+searchText+"%'";
			} 
			//SQL문 완성
			String Condition_SQL = "SELECT * FROM board "+where+" ORDER BY seq desc";  	
			
			pstmt = conn.prepareStatement(Condition_SQL);
			rs = pstmt.executeQuery();
			System.out.println("쿼리문 실행 성공! =>"+Condition_SQL);
			while(rs.next()) {
				BoardDO board = new BoardDO();	//BoardDO 객체 생성후 setter매서드를 통해 값을 저장
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegdate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			
				boardList.add(board);	//리스트에 추가
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		 return boardList;
	} //end getBoardList();
	
	//게시글등록 매서드
	public void insertBoard(BoardDO boardDO) {
		System.out.println("====> insertBoard()기능 처리됨!");
		
		try {
			conn = JDBCUtil.getConnection();
			
			String BOARD_INSERT = "INSERT INTO board(seq, title, writer, content) "
				+ "VALUES((SELECT NVL(MAX(seq),0)+1 FROM board), ?, ?, ?)";
			System.out.println("BOARD_INSERT>>> "+BOARD_INSERT);
			
			pstmt = conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, boardDO.getTitle());
			pstmt.setString(2, boardDO.getWriter());
			pstmt.setString(3, boardDO.getContent());
			pstmt.executeUpdate();	//DML 작업인 경우 executeUpdate() 메서드 호출
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	} // end insertBoard()

	//게시글 상세보기 메서드 구현
	public BoardDO getBoard(BoardDO boardDO) {
		System.out.println("===> getBoard() 기능 처리");
		
		BoardDO board = null;
		try {
			conn = JDBCUtil.getConnection();
			
			//해당 게시글 조회수 수정처리
			String UPDATE_CNT = "UPDATE board SET cnt=cnt+1 WHERE seq=?";
			pstmt = conn.prepareStatement(UPDATE_CNT);
			pstmt.setInt(1, boardDO.getSeq());
			pstmt.executeUpdate();
			
			//게시글 번호 조건에 맞는 해당 게시글 가져오기
			String BOARD_GET = "SELECT * FROM board WHERE seq=?";
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, boardDO.getSeq());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardDO();
				
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegdate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return board;
	} // End getBoard() 

	//게시글 수정 메서드 구현
	public void updateBoard(BoardDO boardDO) {
		
		try {
			conn = JDBCUtil.getConnection();	
			
			//SQL문장
			String BOARD_UPDATE = "UPDATE board SET title=?, writer=?, content=? WHERE seq=?";
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			pstmt.setString(1, boardDO.getTitle());
			pstmt.setString(2, boardDO.getWriter());
			pstmt.setString(3, boardDO.getContent());
			pstmt.setInt(4, boardDO.getSeq());
			pstmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	} // End updateBoard()
	
	//게시판 글 삭제 메서드
	public void deleteBoard(BoardDO boardDO) {
		
		try {
			System.out.println(">>>>>>> deleteBoard()메서드 기능");
			conn = JDBCUtil.getConnection();
			
			String DELETE_BOARD = "DELETE FROM board where seq=?";
			pstmt = conn.prepareStatement(DELETE_BOARD);
			pstmt.setInt(1, boardDO.getSeq());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	} // End deleteBoard() 
	
	
} // End Class
