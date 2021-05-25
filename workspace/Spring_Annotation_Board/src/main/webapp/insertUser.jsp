<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
.header {
  background-color: #f1f1f1;
  padding: 20px;
  text-align: center;
}
input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=button], select {
  width: 100%;
  background-color: Orange;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #368AFF;
  
}

input[type=password], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: Orange;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #368AFF;
  
}

input[type=reset] {
  width: 100%;
  background-color: Orange;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=reset]:hover {
  background-color: #368AFF;
  
}

div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
</head>
<body>
<div align="center">
<div class="header">
	<h1>회원가입</h1>
</div>
	<form action="insertUser.do" method="POST" name="registForm">
		<table>
			<tr>
				<td>아이디<input type="text" required="required" name="id"></td> 
			</tr>
			<tr>
				<td>패스워드<input type="password" required="required" name="password"></td>
			</tr>
			<tr>
				<td>이름<input type="text" required="required" name="name"> </td>
			</tr>
			<tr>
				<td>직무<input type="text" required="required" name="role"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="회원가입">
					<input type="reset" value="다시쓰기">
					<a href="index.jsp"><input type="button" value="취소"></a>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>