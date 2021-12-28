<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- css -->
<link rel="stylesheet" href="${root}/css/reservationDetail.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<title>Insert title here</title>
</head>
<body>
	<div class="reser-detail-wrap">
		<div class="room-detail">
			<!-- 숙소 이름 -->
			<div class="title">
				<label>${roomDto.name}</label>
			</div>
			
			<!-- 숙소 주소, 예약 번호 -->
			<div class="addr">
				<label>주소 | ${roomDto.addr_load} ${roomDto.addr_detail}</label>	
				<label>예약번호 | ${reserDto.no}</label>
			</div>
			
			<div class="photo-reser">
				<!-- 숙소 이미지 -->
				<div id="slideShow">
					<ul class="slides">
						<c:forEach var="img" items="${roomDto.photos}">
							<li>
								<img src="../photo/roomPhoto/${img}">
							</li>
						</c:forEach>
					</ul>
					
					<p class="controller">
						<!-- &lang: 왼쪽 방향 화살표 &rang: 오른쪽 방향 화살표 -->
						<span class="prev">&lang;</span>
						<span class="next">&rang;</span>
					</p>
				</div>
				
				<!-- 예약 정보 -->
				<div class="reser">
					<!-- 체크인 -->
					<div class="check-wrap">
						<div class="check-in">
							<div class="check-title">
								<label>체크인</label>
							</div>
							
							<hr>
							
							<div class="date">
								<span>${start[0]}년 ${start[1]}월 ${start[2]}일 (${startDayWeek})</span>
							</div>
						</div>
						
						<!-- 체크아웃 -->
						<div class="check-out">
							<div class="check-title">
								<label>체크아웃</label>
							</div>
							
							<hr>
							
							<div class="date">
								<span>${end[0]}년 ${end[1]}월 ${end[2]}일 (${endDayWeek})</span>
							</div>
						</div>
					</div>
					
					<!-- 가격 -->
					<div class="price-wrap">
						<div class="price-title">
							<label>결제 세부 정보</label>
						</div>
						
						<hr>
						
						<div class="price-de">
							<div class="price">
								<fmt:formatNumber value="${reserDto.price}" type="currency"/>
							</div>
							
							<div class="receipt">
								<span>영수증 보기</span>
								<span class="bi bi-receipt-cutoff"></span>
							</div>
						</div>
					</div>
					
					<!-- 공동 게스트 -->
					<div class="guest-wrap">
						<div class="guest-title">
							<label>공동 게스트</label>
						</div>
						
						<hr>
						
						<div class="guest">
							<span>${joinGuestNum}명</span>
							
							<c:if test="${preCheck == false}">
								<span class="bi bi-plus-circle"></span>
							</c:if>
							
							<c:if test="${preCheck == true}">
								<span class="bi bi-three-dots"></span>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			
			<hr>
			
			<!-- 호스트 정보 -->
			<div class="host-wrap">
				<div class="photo">
					<img alt="" src="../photo/memberPhoto/${hostDto.photo}">
				</div>
				
				<div class="content">
					<label class="name">호스트 : ${hostDto.id} 님</label>
					<br>
					<label class="email">${hostDto.e_mail}</label>
				</div>
				
				<div class="message">
					<button type="button" class="btn btn-default" id="message-btn">호스트에게 연락하기</button>
				</div>
			</div>
			
			<c:if test="${preCheck == false}">
				<hr>
				
				<!-- 취소 버튼 -->
				<div class="btn-wrap">
					<button class="btn btn-danger" id="can-reser">예약 취소</button>
				</div>
			</c:if>
		</div>
	</div>
	
	<!-- js -->
	<script src="${root}/js/reservationDetail.js"></script>
</body>
</html>