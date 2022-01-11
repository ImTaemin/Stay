<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- css File -->
<link rel="stylesheet" href="/css/loginForm.css">

<title>회원 로그인</title>
</head>
<body>
<div class="login">
 <h1>로그인</h1>
 
  <form action="loginprocess" method="post">
  <div class="idsave">
    <input type="checkbox" id="save" name="cbsave" ${sessionScope.saveok==null?"":"checked"}>아이디 저장하기
  </div>
   <div class="login-input-form">
    <label for="id">ID</label>
      <input type="text" id="id" name="id" placeholder="아이디 입력" required="required" autofocus="autofocus" value="${sessionScope.saveok==null?"":sessionScope.myid }"><br>
   </div>
   <div class="login-input-form">
    <label for="pass">PW</label>
      <input type="password" id="pass" name="pass" placeholder="비밀번호 입력" required="required"><br>
   </div>
   
    <span class="find">
	  <a href="findId">아이디 찾기</a>
	  <span class="or">|</span>
	  <a href="findPw">비밀번호 찾기</a>
    </span><br>
	    
    <span class="sign-up">회원이 아니신가요?&nbsp;&nbsp;<a href="gaip" class="sign-up-link">Sign up</a></span>
    
    <button type="submit" class="login-btn">Login</button>
  </form>
  
  <!-- 카카오톡 로그인 -->
	<c:if test="${sessionScope.kakaologin eq null}">
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=caa825a053430f44f87ec4ac4c7e463e&redirect_uri=https://localhost:8080/member/kakaologin&response_type=code">
      		<img src="/photo/kakao_login_medium_narrow.png">
  		</a>
    </c:if>
</div>

  <!-- kakao login script-->
  <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
</body>
</html>