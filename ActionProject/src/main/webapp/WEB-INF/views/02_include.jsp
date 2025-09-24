<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Include</title>
</head>
<body>
	
	<h1>include</h1>
	
	<p>
		다른 페이지를 포함할 때 사용
	</p>
	
	

	<%-- 파일 전체코드를 가져오기때문에 중복이면 오류발생
	 <%@ include file="footer.jsp" %>
	 <%@ include file="footer.jsp" %> 
	--%>
	
	<h4>JSP표준 액션 태그를 이용한 방식 (동적 include)</h4>
	<!-- 주석입니다 -->
	<!-- !!!주의!!! Content영역에 주석이 있으면 에러 발생, 주석은 밖에 있을것. -->	
	<jsp:include page="footer.jsp"></jsp:include>
	
	<!-- 
		XML기반기술이기 때문에 반드시 시작태그와 종료태그가 쌍으로 존재해야함!
		닫는 태그를 작성하지 않는다면 500 ERORR 발생! 
	-->
	<jsp:include page="footer.jsp"/>
	
	<hr>
	
	<jsp:include page="footer.jsp">
		<jsp:param value="Hi" name="test"/>
	</jsp:include>
	
	<jsp:include page="footer.jsp">
		<jsp:param value="Bye" name="test"/>
	</jsp:include>
	
</body>
</html>