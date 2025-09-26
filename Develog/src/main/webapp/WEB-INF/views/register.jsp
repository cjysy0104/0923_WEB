<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<body>
<div class="form-container">
        <h2>회원가입</h2>
        
        <form action="register" method="POST">
            ID: <input type="text" name="empId" required><br>
            PW: <input type="text" name="empName" required><br>
            이름: <input type="text" name="empNo" required><br>
            이메일: <input type="text" name="jobCode" required><br>
            <input type="submit" value="입력">
        </form>
    </div>
</body>
</html>