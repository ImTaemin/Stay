<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- css -->
<link rel="stylesheet" href="../css/hostMain.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<!-- 선택된 상태의 숙소 정보 -->
		<div class="hostMain-top">
			<div class="hostMain-info">
				<div class="hostMain-image"><img alt="" src="../photo/숙소1_1.jpg"></div>
				<div class="hostMain-detail">
					<div class="hostMain-name">스테이지</div>
					<div class="hostMain-site">경기도 바꿔봐</div>
				</div>
			</div>
			<div class="hostMain-info">
				<div class="hostMain-image"><img alt="" src="../photo/숙소1_1.jpg"></div>
				<div class="hostMain-detail">
					<div class="hostMain-name">행복주택</div>
					<div class="hostMain-site">경기도 수원시</div>
				</div>
			</div>
			<div class="hostMain-info">
				<div class="hostMain-image"><img alt="" src="../photo/숙소1_1.jpg"></div>
				<div class="hostMain-detail">
					<div class="hostMain-name">행복주택</div>
					<div class="hostMain-site">경기도 수원시</div>
				</div>
			</div>
		</div>
		
		<div class="hostMain-middle">
		<c:set var="now" value="<%=new java.util.Date()%>" /> 
		<fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일 (E)" var="today" />
			<div class="hostMain-date">
			${today }
			</div>
			<!-- 상태별 숙소 조회 selectBox -->
			<div class="hostMain-option">
				<select class="hostMain-state">
					<option value="" selected="selected">체크인예정</option>
					<option value="">체크아웃예정</option>
					<option value="">현재호스팅</option>
				</select>
			</div>
		</div>
		
		<div class="hostMain-bottom">
			<div class="hostMain-reservation">
				<div class="hostMain-number">예약번호 : 2112189098</div>
				<div class="hostMain-content">
					<div class="content-name">행복주택
						<div class="content-site">경기도 수원시</div>
					</div>
					<div class="content-checkIn">체크인
						<div class="content-inDate">2020년 12월 12일 (월)</div>
					</div>
					<div class="content-bar"></div>
					<div class="content-checkOut">체크아웃
						<div class="content-outDate">2020년 12월 13일 (화)</div>
					</div>
					<div class="content-guest">게스트
						<div class="content-number">1명</div>
					</div>
					<div class="content-payment">결제세부정보
						<div class="content-cost">103,000</div>
					</div>
				</div>
			</div>
			<div class="hostMain-reservation">
				<div class="hostMain-number">예약번호 : 2112189098</div>
				<div class="hostMain-content">
					<div class="content-name">행복주택
						<div class="content-site">경기도 수원시</div>
					</div>
					<div class="content-checkIn">체크인
						<div class="content-inDate">2020년 12월 12일 (월)</div>
					</div>
					<div class="content-bar"></div>
					<div class="content-checkOut">체크아웃
						<div class="content-outDate">2020년 12월 13일 (화)</div>
					</div>
					<div class="content-guest">게스트
						<div class="content-number">1명</div>
					</div>
					<div class="content-payment">결제세부정보
						<div class="content-cost">103,000</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>