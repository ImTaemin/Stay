<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Dokdo&family=Gaegu&family=Gugi&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- css -->
<link rel="stylesheet" href="/css/findForm.css">

<title>아이디 찾기</title>
</head>
<body>
<div class="find-form">
 <h1>아이디 찾기</h1>
 
  <form action="findIdprocess" method="post">
  	<label for="name">이름</label>
    <input type="text" id="name" name="name" placeholder="이름 입력" required="required" autofocus="autofocus"><br>
  	<label for="hp">연락처</label>
    <input type="text" id="hp" name="hp" placeholder="연락처 입력" required="required" autofocus="autofocus"><br>
   
    <div class="find-cancel-btn">
  	  <button type="submit" class="find-btn">찾기</button>
  	  <button type="button" class="cancel-btn" onclick="history.back()">취소</button>
  	</div>
  </form>
</div>
</body>
</html>