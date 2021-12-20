<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/mypageForm.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<title>마이페이지</title>
</head>
<body>
<div class="cardbtn">
     <!-- <h4>프로필 이미지
     <input type="file" name="photo1" id="photo1" style="display: none;" required="required" multiple="multiple">&nbsp;&nbsp;
     <span class="glyphicon glyphicon-user" style="cursor: pointer;"></span></h4> -->

		<div class="photo">
			<img alt="" src="${root}/photo/${mdto.photo}">
		</div>

		<!-- 이미지 -->
	    <div class="pfimg-div">
    		<!-- <label for="photo">회원프로필 이미지</label> -->
    		<label for="photo"></label>
    		<div class="pfinput">
    			<input type="file" required id="photo" name="photo" accept=".gif, .jpg, .png" multiple="multiple" style="width: 200px; padding-top: 6px;" maxlength="5">
    		</div>
    		
    		<div id="pfmainimg"></div>
    	</div>
     
     <button type="button" class="card-btn" onclick="location.href='card.jsp'">결제 카드 추가</button>
   </div>

  <div class="mypage-form">
  <form action="mypageform" method="post">
<!--    <form action="mypageform" method="post" enctype="multipart/form-data" onsubmit="return check(this)"> -->
  <input type="hidden" name="num" value="${num}">
    
  
   <div class="input">
   <h2>${mdto.name}님의 마이페이지</h2>
    <label for="pass">비밀번호</label>
      <input type="password" id="pass" minlength="8" maxlength="16" placeholder="비밀번호를 입력하세요(8~16자의 영문·특수문자 조합)" required="required">
    <label for="pass_check">비밀번호 확인</label>
      <input type="password" id="pass_check" placeholder="비밀번호 확인" required="required">
  	<label for="name">이름</label>
  	  <input type="text" required="required" value="${mdto.name}">
  	<label for="brith">생년월일</label>
  	  <input type="date" value="${mdto.birth}">
  	<label for="addr">주소</label>
  	  <input type="text" id="addr" name="addr" disabled="disabled">
  	  <button type="button" class="mypage-btn" name="addr">주소 찾기</button><br>
  	  <input type="text" name="addr" value="${mdto.addr}">
  	<label for="hp">핸드폰 번호</label>
  	  <input type="tel" id="hp" required="required" value="${mdto.hp}">
  	<label for="email">이메일</label>
  	   <input type="email" id="email" value="${mdto.email}"> 
  	 <!-- <input type="text" name="email1" class="form-control"
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
	  </select>-->
	<div class="btnmypage">
	   <button type="submit" class="mypage-btn">수정</button>
  	  <button type="button" class="cancel-btn">취소</button>
	</div>
  </div>
  
  </form>
  
  </div>
  <script src="/js/mypageForm.js"></script>
</body>
</html>