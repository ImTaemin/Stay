<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- css -->
<link rel="stylesheet" href="../css/title.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<title>Insert title here</title>
</head>
<c:set var="root" value="<%=request.getContextPath() %>"></c:set>
<body>
	<div class="main-title">
		<!-- 드롭다운메뉴 -->
		<div class="dropdown">
	      <button type="button" aria-label="Open user menu" class="dropdown-button">
			<div class="glyphicon glyphicon-align-justify" id="main-menu"></div>
	      </button>
		<div class="dropdown-menu"> 
	        <ul>
	          <li>
	            <a href="#">
	              숙소
	            </a>
	          </li>
	          <li>
	            <a href="#">
	              예약정보
	            </a>
	          </li>
	          <li>
	            <a href="#">
	              위시리스트
	            </a>
	          </li>
	        </ul>
	        <ul>
	          <li>
	            <a href="">
	              프로필
	            </a>
	          </li>
	          <li>
	            <a href="#">
	              마이페이지
	            </a>
	          </li>
	        </ul>
	        <ul>
	          <li>
	            <a href="#">
	             	호스트로 전환
	            </a>
	          </li>
	        </ul>
	      </div>
		</div>
		<!-- 로고 -->
		<div class="main-logo">
			<img alt="" src="../photo/logo_sm.png">
		</div>
		<!-- 로그인, 로그아웃버튼 -->
		<div class="main-buttons">
			<c:if test="${sessionScope.loginok==null }">
				<button type="button" class="btn btn-info" id="loginbtn"  onclick="location.href='${root}/member/login'">Login</button>
				<button type="button" class="btn btn-default"  id="signupbtn" onclick="location.href='${root}/member/gaipform'">Sign Up</button>
			</c:if>
			<c:if test="${sessionScope.loginok!=null }">
				<b>${sessionScope.myid } 님 </b><i class="fas fa-user-circle"></i>
				<button type="button" class="btn btn-danger" id="logoutbtn"  onclick="location.href='logoutprocess'">Logout</button>
			</c:if>
		</div>
	</div>
  <script src="../js/title.js"></script>
</body>
</html>