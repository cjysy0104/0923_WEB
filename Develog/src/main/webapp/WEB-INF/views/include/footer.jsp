<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Footer Example</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }
        .footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: white;
            border-top: 1px solid #e0e0e0;
        }
        .footer .footer-left {
            font-size: 18px;
            font-weight: bold;
        }
        .footer .social-icons a {
            margin-left: 15px;
            text-decoration: none;
            color: black;
            font-size: 18px;
        }
        .footer .footer-right {
            font-size: 14px;
            color: #888;
        }
    </style>
</head>
<body>
    <div class="footer">
        <div class="footer-left">Develog</div>
        <div class="social-icons">
            <a href="https://instagram.com" target="_blank">📷</a>
            <a href="https://twitter.com" target="_blank">🐦</a>
        </div>
        <div class="footer-right">
            Made with <a href="https://www.squarespace.com" target="_blank" style="color: #000;">CtrlCV</a>
        </div>
    </div>
</body>
</html>