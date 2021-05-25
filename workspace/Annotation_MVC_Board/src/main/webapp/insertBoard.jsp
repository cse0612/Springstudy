<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 입력폼</title>
</head>
<body>
<div align="center">
<h1>게시글 등록</h1>
<a href="logout.do">로그아웃</a>
<hr>
<form name="insertForm" method="POST" action="insertBoard.do">
	<table border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td bgcolor="orange" width="70">제목</td>
		<td align="left"><input type="text" name="title" required></td>
	</tr>
	
	<tr>
		<td bgcolor="orange" width="70">작성자</td>
		<td align="left"><input type="text" name="writer" required></td>
	</tr>
	
	<tr>
		<td bgcolor="orange" width="70">내용</td>
		<td align="left"><textarea rows="10" cols="40" name="content" required></textarea></td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="글 등록">
		</td>
	</tr>
	</table>
</form>
<hr>
<a href="getBoardList.do">전체 게시글 목록 보기</a>
</div>
</body>
</html>