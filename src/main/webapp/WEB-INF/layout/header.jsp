<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet"> <!--CDN 링크 -->

<!-- css -->
<link rel="stylesheet" href="../css/header.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>


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
		<c:if test="${sessionScope.loginok!=null }">
	        <ul>
	          <li>
		          <c:if test="${sessionScope.mode=='guest'}">
			      	<a href="${root}/room/main">숙소</a>
			      </c:if>
			      <c:if test="${sessionScope.mode=='host'}">
				     <a href="#">숙소</a>
			      </c:if>
	          </li>
	        </ul>
	        <ul>
	          <li>
		           <c:if test="${sessionScope.mode=='guest'}">
				      <a href="${root}/reser/reservationlist">예약정보</a>
				   </c:if>
			       <c:if test="${sessionScope.mode=='host'}">
				     <a href="${root}/reser/hostreservationlist">예약정보</a>
			       </c:if>
	          </li>
	          <li>
		          <c:if test="${sessionScope.mode=='guest'}">
			      	 	<a href="${root }/wish/list">위시리스트</a>
			      </c:if>
		          <c:if test="${sessionScope.mode=='host'}">
			      	 	<a href="#">달력</a>
			      </c:if>
	          </li>
	        </ul>
	        <ul>
	          <li>
	            <a href="${root}/profile/profileform">프로필</a>
	          </li>
	          <li>
	            <a href="${root}/mypage/mypageform">마이페이지</a>
	          </li>
	          <li>
	        	  <c:if test="${sessionScope.mode=='guest'}">
			            <a href="/card/insertform">결제카드 추가</a>
		          </c:if>
		          <c:if test="${sessionScope.mode=='host'}">
			            <a href="/account/insertform">수금 계좌번호 등록</a>
		          </c:if>
	          </li>
	        </ul>
	        <ul>
	          <li>
		          <c:if test="${sessionScope.mode=='guest'}">
		      	 	<a href="/changeMode">호스트로 전환</a>
		           </c:if>
		            <c:if test="${sessionScope.mode=='host'}">
			            <a href="/changeMode">게스트로 전환</a>
		            </c:if>
	           </li>
	        </ul>
	    </c:if>
	       <c:if test="${sessionScope.loginok==null }">
	        <ul>
	          <li>
	            <a href="${root}/room/main">숙소</a>
	          </li>
	          <li>
	            <a href="${root }/member/login">예약정보</a>	
	          </li>
	          <li>
	            <a href="${root }/member/login">위시리스트</a>
	          </li>
	        </ul>
	        <ul>
	          <li>
	            <a href="${root}/member/login">프로필</a>
	          </li>
	          <li>
	            <a href="${root}/member/login">마이페이지</a>
	          </li>
	        </ul>
	        <ul>
	          <li>
	            <a href="${root}/member/login">호스트로 전환</a>
	          </li>
	        </ul>
	       </c:if>
	      </div>
		</div>
		<!-- 로고_클릭시 메인페이지로 이동 -->
		<div class="main-logo">
			<a href="${root }/">
				<img alt="" src="../photo/logo_sm.png">
			</a>
		</div>
		<!-- 로그인, 회원가입, 로그아웃버튼, 로그인중 표시-->
		<div class="main-buttons">
			<c:if test="${sessionScope.loginok==null }">
				<button type="button" class="btn btn-info" id="loginbtn"  onclick="location.href='${root}/member/login'">Login</button>
				<button type="button" class="btn btn-default"  id="signupbtn" onclick="location.href='${root}/member/gaip'">Sign Up</button>
			</c:if>
			<c:if test="${sessionScope.loginok!=null }">
				<c:if test="${sessionScope.kakaologin == null }">
					<b>${sessionScope.myid } 님</b>
				</c:if>
				<c:if test="${sessionScope.kakaologin!=null }">
					<b>${sessionScope.kakaoName } 님</b>
				</c:if>
				<div class="mypage-img" onclick="location.href='/mypage/mypageform'">
				  <!-- 일반 로그인 -->
				  <c:if test="${sessionScope.kakaologin==null }">
					<img id="img" src="${root}/photo/memberPhoto/${photo}">
				  </c:if>
				  <!-- 카카오 로그인 -->
				  <c:if test="${sessionScope.kakaologin!=null }">
					<img id="img" src="${sessionScope.profile}">
				  </c:if>
				</div>
				<button type="button" class="btn btn-danger" id="logoutbtn"  onclick="location.href='/member/kakaologout'">Logout</button>
			</c:if>
		</div>
	</div>
  <script src="../js/header.js"></script>
</body>
</html>