<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실패!</title>
<style>
	h1{
		color : red;
		font-size: 64px;
		text-align: center;
		height: 600px;
	}
</style>
</head>
<body>

	<!-- views(상위폴더)갔다가 include폴더 들어가야함 -->
	<!--  ./ : 현재 디렉토리, ../ : 상위 디렉토리 -->
	<jsp:include page="../include/header.jsp" />
	
	<h1>${ msg }</h1>

	<jsp:include page="../include/footer.jsp" />
</body>
</html>