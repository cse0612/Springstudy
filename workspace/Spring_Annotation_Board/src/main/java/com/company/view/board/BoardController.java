package com.company.view.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Spring_Annotation_Board.board.BoardDAO;
import com.company.Spring_Annotation_Board.board.BoardDO;

@Controller // -> 컴포넌트 상위클래스 밑의 Controller클래스로 지정
public class BoardController {
	//@Autowired
	//private BoardService boardService;	//BoardService (인터페이스)타입의 BoardServiceImpl 구현한 객체 의존성주입

	//글등록
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardDO boardDO, BoardDAO boardDAO) {
	//public String insertBoard(BoardDO boardDO) {
		boardDAO.insertBoard(boardDO);	//boardDAO 제거하고 
		//boardService.insertBoard(boardDO);	//대신 boardSerice 변수 이용 비즈니스 컴포넌트 사용
		return "getBoardList.do";
	}
	
	//글수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardDO boardDO, BoardDAO boardDAO) {
		boardDAO.updateBoard(boardDO);
		//boardService.updateBoard(boardDO);
		return "getBoardList.do";
	}

	//글삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardDO boardDO, BoardDAO boardDAO) {
		boardDAO.deleteBoard(boardDO);
		//boardService.deleteBoard(boardDO);
		return "getBoardList.do";
	}
	
	//글상세조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardDO boardDO, BoardDAO boardDAO, Model model) {
		//mav.addObject("board", boardDAO.getBoard(boardDO));	// ModelAndView 방식
		//mav.setViewName("getBoard.jsp");
		model.addAttribute("board", boardDAO.getBoard(boardDO)); //command 객체 매개변수 사용
		//model.addAttribute("board", boardService.getBoard(boardDO));
		return "getBoard.jsp";
	}
	
	//글목록검색
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardDO boardDO, BoardDAO boardDAO, Model model, String searchFiled, String searchText) {
	//	model.addAttribute("boardList", boardService.getBoardList(searchFiled, searchText));
		model.addAttribute("boardList", boardDAO.getBoardList(searchFiled, searchText));
		return "getBoardList.jsp";		
	}
	
	//글목록 JSON 데이타 변환
	@RequestMapping("/dataTransform.do")
	@ResponseBody
	public List<BoardDO> dataTransform(BoardDO boardDO, BoardDAO boardDAO, Model model,String searchFiled, String searchText) {
		//searchFiled = "";
		//searchText = "";
		List<BoardDO> boardList = boardDAO.getBoardList(searchFiled, searchText);
		return boardList;
	}
	
//	검색조건 목록 값 설정시 @ModelAttribute 사용하여 view페이지에 적용할수 있다
//	@ModelAttribute("conditionMap")
//	public Map<String, String> searchConditionMap() {
//		Map<String, String> conditionMap = new HashMap<String, String>();
//		conditionMap.put("제목", "TITLE");
//		conditionMap.put("내용", "CONTENT");
//		return conditionMap;
//	}
	

}
