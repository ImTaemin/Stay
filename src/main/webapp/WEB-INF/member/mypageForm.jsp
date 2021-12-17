<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/mypageForm.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>마이페이지</title>
</head>
<body>
  <h2>${name}님의 마이페이지</h2>
  <form action="mypageform" method="post" enctype="multipart/form-data" onsubmit="return check(this)">
  <input type="hidden" name="num" value="${num}">
  <h4>프로필 이미지
  <input type="file" name="photo" id="photo" style="display: none;" required="required" multiple="multiple">&nbsp;&nbsp;
   <span class="glyphicon glyphicon-user" style="cursor: pointer;"></span></h4>
    <button type="button" class="btn btn-info" style="width: 150px;" onclick="location.href='card.jsp'">결제 카드 추가</button>
    
    <label for="pass">비밀번호</label>
      <input type="password" id="pass" minlength="8" maxlength="16" placeholder="비밀번호를 입력하세요(8~16자의 영문·특수문자 조합)" required="required">
    <label for="pass_check">비밀번호 확인</label>
      <input type="password" id="pass_check" placeholder="비밀번호 확인" required="required">
  	<label for="name">이름</label>
  	  <input type="text" required="required" value="${name}">
  	<label for="brith">생년월일</label>
  	  <input type="date" value="${birth}">
  	<label for="addr">주소</label>
  	  <input type="text" id="addr" name="addr" disabled="disabled">
  	  <button type="button" name="addr">주소 찾기</button>
  	  <input type="text" name="addr" value="${addr}">
  	<label for="hp">핸드폰 번호</label>
  	  <input type="tel" id="hp" required="required" value="${hp}">
  	<label for="email">이메일</label>
  	  <!-- <input type="email" id="email" placeholder="이메일을 입력하세요"> -->
  	  <input type="text" name="email1" class="form-control"
  	  		required="required" style="width: 80px;" value="${email1}">
		<b>@</b>
	  <input type="text" name="email2" id="email2" class="form-control"
			required="required" style="width: 150px;" value="${email2}">
	  <select id="selemail" class="form-control">
			<option value="-">직접입력</option>
			<option value="naver.com">네이버</option>
			<option value="nate.com">네이트</option>
			<option value="gmail.com">구글</option>
			<option value="hanmail.net">다음</option>
	  </select>
	  
	<button type="submit" class="btn btn-info" style="width: 100px;">수정</button>
	<button type="reset" class="btn btn-danger" style="width: 100px;">취소</button>
  </form>
  
  <script src="js/mypageForm.js"></script>
</body>
</html>