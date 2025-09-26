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
<title>Employee</title>
<style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        input[type="text"], input[type="submit"] {
            padding: 8px;
            margin: 5px 0;
        }
        .form-container {
            max-width: 400px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div class="form-container">
        <h2>사용자 정보 입력</h2>
        <form action="insert.do" method="POST">
            ID: <input type="text" name="empId" required><br>
            이름: <input type="text" name="empName" required><br>
            사번: <input type="text" name="empNo" required><br>
            직무코드: <input type="text" name="jobCode" required><br>
            연봉레벨: <input type="text" name="salLevel" required><br>
            <input type="submit" value="입력">
        </form>
    </div>
	<a href="selectList.emp">LIST</a>
    
</body>
</html>