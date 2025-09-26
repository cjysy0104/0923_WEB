<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.employee.model.vo.Employee, java.util.List" %>
<%
	// request.getAttribute("키값") : Object
	List<Employee> emps = (List<Employee>)request.getAttribute("emps");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>사용자 목록</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>이름</th>
            <th>사번</th>
            <th>직무코드</th>
            <th>연봉레벨</th>
        </tr>
 
        <tr>
            <td>111</td>
            <td>홍길동</td>
            <td>991231-9999999</td>
            <td>J5</td>
            <td>S4</td>
        </tr>
     
        <!-- 여기에는 실제 데이터가 들어갈 곳 -->
        <% if(emps.isEmpty()) { %>
				<tr>
					<th colspan="10">조회결과가 존재하지 않습니다.</th>
				</tr>
		<%} else { %>
		
			<% for(Employee e : emps) { %>
				
					<tr>
						<td><%=e.getEmpId() %></td>
						<td><%=e.getEmpName() %></td>
						<td><%=e.getEmpNo() %></td>
						<td><%=e.getJobCode() %></td>
						<td><%=e.getSalLevel() %></td>
					</tr>
				<%} %>
					
			<%}%> 

    </table>
</body>
</html>