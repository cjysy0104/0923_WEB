<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navigation Bar</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .navbar .logo {
            font-size: 24px;
            font-weight: bold;
            letter-spacing: 1px;
        }
        .navbar .nav-links {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
        }
        .navbar .nav-links li {
            margin-right: 20px;
        }
        .navbar .nav-links li a {
            text-decoration: none;
            color: black;
            font-size: 16px;
            font-weight: normal;
        }
        .navbar .social-icons a {
            margin-left: 15px;
            text-decoration: none;
            color: black;
            font-size: 18px;
        }
    </style>
    <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- Custom fonts for this template -->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>

<!-- Custom styles for this template -->
<link href="resources/css/agency.min.css" rel="stylesheet">
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>

<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
</head>

<body>
    <div class="navbar">
        <div class="logo">Develog</div>
        <ul class="nav-links">
            <li><a href="#blog">Blog</a></li>
            <li><a href="#works">Works</a></li>
            <li><a href="#contact">Contact</a></li>
        </ul>
        <div class="social-icons">
            <a href="https://instagram.com" target="_blank">📷</a>
            <a href="https://twitter.com" target="_blank">🐦</a>
            <div class="login-bar">
	        	<ul class="navbar-nav text-uppercase ml-auto">
	        		<c:choose>
	        			<c:when test="${ empty sessionScope.userInfo }">
			        		<li class="nav-item"><a class="nav-link js-scroll-trigger"
											data-toggle="modal" data-target="#log-in">로그인</a></li>
	        			</c:when>
	        			<c:otherwise>
	        				<li class="nav-item"><a class="nav-link js-scroll-trigger"
								href="result" onclick="confirm('로그아웃하시겠습니까?')">로그아웃</a>
	        			</c:otherwise>
	        		</c:choose>
	        	
	        	</ul>
        	</div>
        </div>
        
    </div>
    
    
    <!-- 로그인 Modal-->
	<div class="modal fade" id="log-in">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">
						<span style="color: #52b1ff;"></span> 로그인
					</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">

					<form action="login" name="sign-in" method="post" id="signInForm" onsubmit="return sendit()"
						style="margin-bottom: 0;">
						<table
							style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 100%">
							<tr>
								<td style="text-align: left">
									<p>
										<strong>아이디를 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span
											id="idCheck"></span>
									</p>
								</td>
							</tr>
							<tr>
								<td><input type="text" name="userId" id="signInId"
									class="form-control tooltipstered" maxlength="10"
									required="required" aria-required="true"
									style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
									placeholder="최대 15자"></td>
							</tr>
							<tr>
								<td style="text-align: left">
									<p>
										<strong>비밀번호를 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span
											id="pwCheck"></span>
									</p>
								</td>
							</tr>
							<tr>
								<td><input type="password" size="17" maxlength="20"
									id="signInPw" name="userPwd" class="form-control tooltipstered"
									maxlength="20" required="required" aria-required="true"
									style="ime-mode: inactive; margin-bottom: 25px; height: 40px; border: 1px solid #d9d9de"
									placeholder="최소 8자"></td>
							</tr>
							<tr>
								<td style="padding-top: 10px; text-align: center">
									<p>
										<a href="/develog/register"><strong>회원이 아니신가요?</strong></a>
									</p>
								</td>
							</tr>
							<tr>
								<td style="width: 100%; text-align: center; colspan: 2;"><input
									type="submit" value="로그인"
									class="btn form-control tooltipstered" id="signIn-btn"
									style="background-color: #52b1ff; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>