<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- css -->
<link rel="stylesheet" href="/css/gaipForm.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>회원가입폼</title>
</head>
<body>
 <div class="gaip-form">
  <h1>회원가입</h1>
  <form action="insert" method="post">
    <label for="id">아이디</label>
    <div class="gaip-input-check">
      <input type="text" id="id" name="id" minlength="3" maxlength="12" placeholder="아이디를 입력하세요" required="required" oninput = "checkId()">
      <span class="id_ok">사용 가능한 아이디입니다.</span>
	  <span class="id_already">이미 존재하는 아이디입니다.</span>
    </div>
    <label for="pass">비밀번호</label>
      <input type="password" id="pw" name="pass" minlength="6" maxlength="16" placeholder="비밀번호를 입력하세요(특수문자 포함 6~16자 입력)" required="required" oninput="checkPw()">
    <label for="pass_check">비밀번호 확인</label>
  	<div class="gaip-input-check">
      <input type="password" id="pw2" placeholder="비밀번호 확인" required="required" oninput="checkPw()">
  	  <div class="pw_glyphicon"><span class="glyphicon glyphicon-ok-sign"></span></div>
  	  <div class="pw_check"><span class="pw_equal">비밀번호가 일치합니다.</span></div>
  	  <div class="pw_glyphicon"><span class="glyphicon glyphicon-remove-sign"></span></div>
  	  <div class="pw_check"><span class="pw_not_equal">비밀번호가 다릅니다.</span></div>
  	  <div class="pw_check"><span class="pw_not_length">비밀번호는 6글자 이상, 16글자 이하만 사용 가능합니다.</span></div>
  	  <div class="pw_check"><span class="pw_not_sc">!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.</span></div>
  	</div>

  	
  	<label for="name">이름</label>
  	  <input type="text" name="name" placeholder="이름을 입력하세요" required="required">
  	<label for="brith">생일</label>
  	  <input type="date" name="birth">
  	<label for="addr_load">주소</label>
  	  <input type="text" name="addr_load" id="sample4_roadAddress" onclick="sample4_execDaumPostcode()" placeholder="도로명주소" readonly="readonly" style="margin-bottom: 10px;">
	  <input type="hidden" id="sample4_jibunAddress" placeholder="지번주소" size="60">
	  <span id="guide" style="color:#999; display:none"></span>
	  <input type="text" name="addr_detail" id="sample4_detailAddress" placeholder="상세주소" size="60">
	  <input type="hidden" id="sample4_extraAddress" placeholder="참고항목" size="60">
	  <input type="hidden" id="sample4_engAddress" placeholder="영문주소" size="60">
  	<label for="hp">핸드폰 번호</label>
  	  <input type="tel" id="hp" name="hp" placeholder="'-'를 제외한 핸드폰 번호를 입력하세요" required="required">
  	<label for="email">이메일</label>
  	  <input type="email" id="email" name="e_mail" placeholder="이메일을 입력하세요">
  	<div class="gaip-cancel-btn">
  	  <button type="submit" class="gaip-btn">완료</button>
  	  <button type="button" class="cancel-btn" onclick="history.back()">취소</button>
  	</div>
  </form>
 </div>
 
 <!-- js -->
  <script src="/js/gaipForm.js"></script>
  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</body>
</html>