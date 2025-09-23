<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 이 구문은 스크립틀릿(scriptlet)이라고 함
	// 자바코드를 그냥 쓸 수 있음
	
	// 현재 이 JSP상에서 필요한 데이터들을 => request의 Attribute 에서 뽑아내기
	int age = (int) request.getAttribute("age"); 
	double height = (double) request.getAttribute("height"); 
	String city = (String) request.getAttribute("city"); 
	String name = (String) request.getAttribute("name"); 
	String gender = (String) request.getAttribute("gender"); 
	String[] foods = (String[])request.getAttribute("foods"); 
	
	// request.getAttribute("키값": Object)

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${ msg }</h1>
	<h3><%= name %>의 정보</h3>
	<h3>나이 : <%= age %></h3>
	<h3>키 : <%= height %></h3>
	<h3>지역 : <%= city %></h3>
	
	성별 :
	<!-- 성별을 선택하지 않았을 경우 -->
	<% if(gender == null) { %>
		선택안함	<br>
	<% } else if("남".equals(gender)) {%>
	<!-- 성별을 선택했을 경우 -->
		남자		<br>
	<% } else {%>
		여자		<br>
	<% } %>
	
	좋아하는 음식 :
	<% if(foods == null) { %>
		안고름
	<% } else {%>
		<ul>
			<% for(int i = 0; i < foods.length; i++) {%>
				<li><%= foods[i] %></li>
			<% } %>
		</ul>
		입니다.
	<% } %>
</body>
</html>