<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.subway.model.vo.Subway"%> <!-- import문의 경우 따로 분리해서 작성 -->
<%
	// 스크립틀릿 : 자바코드를 그대로 작성하는 영역(세미클론을 포함한 형태)
	// JSP는 Servlet으로 변환되어 동작하기 때문에 클래스 작성과 동일한 방식을
	// 생각해야함
	Subway order = (Subway)request.getAttribute("order");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문확인 페이지</title>
<style>
	#wrap{
		width: 1200px;
		margin: auto;
	}
	h1{
		text-align: center;
	}
</style>
</head>
<body>

	<div id="wrap"></div>
	
		<h1>주문내역</h1>
		
		<h3>주문자 정보</h3> 
		
		<%-- 
			JSP 주석
			
			JSP 내용들은 여기에 주석 처리
			
			출력식: <%= (내용) %> (출력식에는 세미클론을 달지 않음)
			
		--%>
		
		이름: <%= order.getName() %><br>
		전화번호: <%= order.getPhone() %><br>
		주소: <%= order.getAddress() %><br>
		요청사항: <%= order.getRequest() %><br>
		
		<h3>메뉴 정보</h3>
		샌드위치: <%= order.getSandwitch() %><br>
		채소: <%= order.getVegetable() %><br>
		소스: <%= order.getSauce() %><br>
		쿠키: <%= order.getCookie() %><br>
		결제방식: <%= order.getPayment() %><br>
		
		위와 같이 주문하시겠습니까? <br><br>
		
		<button>확인</button> <button>취소</button>
		
</body>
</html>