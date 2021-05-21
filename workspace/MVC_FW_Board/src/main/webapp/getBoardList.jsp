<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 추가 -->
<%@ page errorPage="error.jsp" %>
<%@ page import="com.company.MVC_FW_Board.board.BoardDO" %>	
<%@ page import="com.company.MVC_FW_Board.board.BoardDAO" %>
<%@ page import="java.util.List" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");

	//List<BoardDO> boardList = (List)session.getAttribute("boardList");//세션객체
	List<BoardDO> boardList = (List)request.getAttribute("boardList");
	//총게시글 갯수 구하기
	int totalList = boardList.size();
	request.setAttribute("totalList", totalList);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
</head>
<body>
	<h3>${IdKey}님
		환영합니다.&nbsp;&nbsp;&nbsp; <a href="logout.do">로그아웃</a>
	</h3>
	<!-- 게시글 검색폼 -->
	<form name="form2" method="post" action="getBoardList.do">
		<p>총게시글:${totalList}</p>
		<table border="1" cellspacing="0" cellsapcing="0" width="700">
			<tr>
				<td align="center">
					<div class="input-group sm">
						<select name="searchCondition" class="form-select" style="width:50px">
								<option value="">선택</option>
								<option value="TITLE">제목</option>
								<option value="WRITER">작성자</option>
								<option value="CONTENT">내용</option>
						</select>
						<input type="text" name="searchKeyword"> 
						<input type="submit" value="검 색">
					</div>
				</td>
			</tr>
		</table>


		<!-- 게시판 목록 -->
		<table class="table" >
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">등록일</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}">
				<tr>
					<th scope="row">${board.seq}</th>
					<td><a href="getBoard.do?seq=${board.seq}"> ${board.title} </a></td>
					<td>${board.writer}</td>
					<td>${board.regdate}</td>
					<td>${board.cnt}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="insertBoard.jsp">새 개시글 등록</a>
		<a href="getBoardList.do">전체 개시글 보기</a>
	</form>


</body>
</html>