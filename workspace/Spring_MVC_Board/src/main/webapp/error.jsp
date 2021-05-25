<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>isErrorPage="true" => 오류만 처리하는 페이지</title>
</head>
<!-- 추가 -->
<jsp:useBean id="now" class="java.util.Date"/>

<body>
	<h2>에러 페이지</h2>
	개발자에게 문의해주세요<br>
	빠른시일내로 복구하겠습니다.<br>
	
	${now}<br>
	요청실패 URI : ${pageContext.errorData.requestURI}<br>
	상태코드 : ${pageContext.errorData.statusCode} <br>
	예외유형 : ${pageContext.errorData.throwable}
	
</body>
</html>