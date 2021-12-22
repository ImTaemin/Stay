<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${root}/css/mypageForm.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<c:set var="root" value="<%=request.getContextPath() %>"/>

<title>마이페이지</title>
</head>
<body>
<div class="cardbtn">
     <!-- <h4>프로필 이미지
     <input type="file" name="photo1" id="photo1" style="display: none;" required="required" multiple="multiple">&nbsp;&nbsp;
     <span class="glyphicon glyphicon-user" style="cursor: pointer;"></span></h4> -->
		
		<div class="photo">
			<img src="${root}/photo/memberPhoto/${memberDto.photo}">
		</div>

		<!-- 이미지 -->
	    <div class="pfimg-div">
    		<!-- <label for="photo">회원프로필 이미지</label> -->
    		<label for="photo"></label>
    		<div class="pfinput">
    			<input type="file" required id="photo" name="photo" accept=".gif, .jpg, .png" style="width: 200px; padding-top: 6px;" maxlength="5">
    		</div>
    		
    		<div id="pfmainimg"></div>
    	</div>
     
     <button type="button" class="card-btn" onclick="location.href='../card/insertform'">결제 카드 추가</button>
   
   </div>
   
   

  <div class="mypage-form">
  <form action="mypageform" method="post">
<!--    <form action="mypageform" method="post" enctype="multipart/form-data" onsubmit="return check(this)"> -->
  
    <c:if test="${sessionScope.loginok!=null }">
    <input type="hidden" name="id" value="${sessionScope.myid}">
	</c:if>
  
   <div class="input">
   <h2>${memberDto.name}님의 마이페이지</h2>
    <label for="pass">비밀번호</label>
      <input type="password" name="userPW" id="pass" minlength="8" maxlength="16" placeholder="비밀번호를 입력하세요(8~16자의 영문·특수문자 조합)" required="required" onchange="check_pw()">
    <label for="pass_check">비밀번호 확인</label>
      <input type="password" name="userPW2" id="pass_check" placeholder="비밀번호 확인" required="required" onchange="check_pw()">&nbsp;<span id="check"></span>
  	<label for="name">이름</label>
  	  <input type="text" required="required" value="${memberDto.name}">
  	<label for="brith">생년월일</label>
  	  <input type="date" value="${memberDto.birth}">
  	<label for="addr_load">주소</label>
  	  <input type="text" name="addr_load" id="sample4_roadAddress" onclick="sample4_execDaumPostcode()" placeholder="도로명주소" readonly="readonly" style="margin-bottom: 10px;" value="${memberDto.addr_load}">
	  <input type="hidden" id="sample4_jibunAddress" placeholder="지번주소" size="60">
	  <span id="guide" style="color:#999; display:none"></span>
	  <input type="text" name="addr_detail" id="sample4_detailAddress" placeholder="상세주소" size="60" value="${memberDto.addr_detail}">
	  <input type="hidden" id="sample4_extraAddress" placeholder="참고항목" size="60">
	  <input type="hidden" id="sample4_engAddress" placeholder="영문주소" size="60">
  	
  	<%-- 
  	
  	  <div class="mypage-update-addr">
  	  <input type="text" id="addr1" name="addr1" disabled="disabled" value="${memberDto.addr_load}">
  	  <button type="button" class="mypage-btn" name="addr_find">주소 찾기</button><br>
  	  </div>
  	  <input type="text" id="addr2" name="addr2" value="${memberDto.addr_detail}"> --%>
  	  
  	<label for="hp">핸드폰 번호</label>
  	  <input type="tel" id="hp" required="required" value="${memberDto.hp}">
  	<label for="email">이메일</label>
  	   <input type="email" id="email" value="${memberDto.e_mail}"> 
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
	<br><br>
  </div>
  
  </form>
  
  </div>
  <script src="${root}/js/mypageForm.js"></script>
  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</body>
</html>