<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/gaipForm.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>회원가입폼</title>
</head>
<body>
 <div class="gaip-form">
  <h1>회원가입</h1>
  <form action="gaipform" method="post">
    <label for="id">아이디</label>
    <div class="gaip-input">
      <input type="text" id="id" name="id" minlength="5" maxlength="12" disabled="disabled">
      <button type="button" id="idcheck" class="input-btn">아이디 입력</button>
    </div>
    <label for="pass">비밀번호</label>
      <input type="password" id="pass" name="pass" minlength="8" maxlength="16" placeholder="비밀번호를 입력하세요(8~16자 영문·특수문자 조합)" required="required">
    <label for="pass_check">비밀번호 확인</label>
      <input type="password" id="pass_check" name="pass2" placeholder="비밀번호 확인" required="required">
  	<label for="name">이름</label>
  	  <input type="text" name="name" placeholder="이름을 입력하세요" required="required">
  	<label for="brith">생일</label>
  	  <input type="date" name="birth">
  	<label for="addr">주소</label>
  	<div class="gaip-input">
  	  <input type="text" id="addr" name="addr" disabled="disabled">
  	  <button type="button" name="btnaddr" class="input-btn">주소 찾기</button>
  	</div>
  	  <input type="text" name="addr2" placeholder="상세주소를 입력하세요">
  	<label for="hp">핸드폰 번호</label>
  	  <input type="tel" id="hp" name="hp" placeholder="'-'를 제외한 핸드폰 번호를 입력하세요" required="required">
  	<label for="email">이메일</label>
  	  <input type="email" id="email" name="email" placeholder="이메일을 입력하세요">
  	<div class="btn">
  	  <button type="submit" class="gaip-btn">완료</button>
  	  <button type="button" class="cancel-btn">취소</button>
  	</div>
  </form>
 </div>
 
  <script src="/js/gaipForm.js"></script>
</body>
</html>