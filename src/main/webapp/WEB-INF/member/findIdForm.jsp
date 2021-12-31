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
 
<form>
  	<label for="inputName">이름</label>
    <input type="text" id="inputName" name="inputName" placeholder="이름을 입력하세요" required="required" autofocus="autofocus"><br>
  	<label for="inputHp">연락처</label>
    <input type="tel" class="phoneNumber" id="inputHp" name="inputHp" placeholder="핸드폰 번호를 입력하세요" required="required" autofocus="autofocus"><br>
   
    <div class="find-cancel-btn">
  	  <!-- <button type="button" id="searchBtn" class="find-btn" onclick="idSearch_click()">찾기</button> -->
  	  <button type="button" id="searchBtn" class="find-btn">찾기</button>
  	  <div class="modal">
  	    <div id="background_modal" class="background_modal">
		  <div class="modal_contents">
		  <div class="close">&times;</div>
			<h2>
				<b>회원님의 아이디는</b>
			</h2><br>
				<h1><b id="id_value"></b></h1>
			<br>
			<button type="button" id="pwSearch_btn" class="findPw-btn">비밀번호 찾기</button>
		  </div>
	    </div>
	  </div>
  	  
  	  <button type="button" class="cancel-btn" onclick="history.back()">취소</button>
  	</div>
</form>
</div>

<!-- js -->
<script src="/js/findForm.js"></script>

<%-- <%@ include file="/member/userIdModal.jsp" %> --%>
</body>
</html>