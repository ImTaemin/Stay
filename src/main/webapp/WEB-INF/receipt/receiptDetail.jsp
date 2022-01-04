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
<link rel="stylesheet" href="${root}/css/receiptDetail.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<!-- js -->
<script src="${root}/js/receiptDetail.js"></script>
<title>Insert title here</title>
</head>
<body>
	<!-- hidden -->
	<input type="hidden" name="startDate" value="${receiptDto.resDto.start_date}">
	<input type="hidden" name="endDate" value="${receiptDto.resDto.end_date}">
	<input type="hidden" name="roomPrice" value="${receiptDto.roomDto.price}">

	<div class="receipt-wrap">
		<div class="receipt-title">
			<label>쉼, 영수증</label>
			
			<div class="receipt-id">
				<label>영수증 No | ${receiptDto.recDto.id}</label>
			</div>
			
			<img src="${root}/photo/logo_sm.png">
		</div>
		
		<hr>
		
		<div class="receipt-main">
			<div class="room-detail">
				<div class="title-wrap">
					<div class="title-detail">
						<label class="title">${receiptDto.roomDto.name}</label>
					
						<span class="addr">${receiptDto.roomDto.addr_load} ${receiptDto.roomDto.addr_detail}</span>
					</div>
					
					<c:if test="${canCheck == 0}">
						<img src="${root}/photo/event.png">
					</c:if>
					
					<c:if test="${canCheck == 1}">
						<img src="${root}/photo/cancel-event.png">
					</c:if>
				</div>
				
				<hr>
				
				<div class="reser-date">
					<span>${start[0]}년 ${start[1]}월 ${start[2]}일 (${startDayWeek})
					 ~ ${end[0]}년 ${end[1]}월 ${end[2]}일 (${endDayWeek})</span>
				</div>
				
				<div class="guest-wrap">
					<span>게스트 | ${receiptDto.resDto.guest_id} ( 총 ${joinNum}명 )</span>
				</div>
				
				<div class="photo-wrap">
					<img id="roomPhoto" src="${root}/photo/roomPhoto/${receiptDto.roomDto.photos}">
				</div>
				
				<hr>
				
				<div class="host-wrap">
					<span>호스트 | ${receiptDto.resDto.host_id}</span>
				</div>
				
				<hr>
				
				<span>
					체크인 30일 전까지 예약을 취소하면 요금 전액이 환불됩니다.<br>
					그 이후 체크인 전에 취소하면 요금의 50%가 환불됩니다. 이 예약의 서비스 수수료는 환불되지 않습니다.
				</span>
			</div>
			
			<div class="pay-detail">
				<div class="price-wrap">
					<label class="title">요금 내역</label>
					
					<hr>
					
					<div class="cal-wrap">
						<div class="cal-tag">
							<fmt:formatNumber var="roomPirce" value="${receiptDto.roomDto.price}" type="currency" currencySymbol="￦"/>
							<span>${roomPirce} X </span><span id="betweenDay"></span>
						</div>
						
						<span id="calPrice"></span>
					</div>
					
					<div class="tax-wrap">
						<span>수수료 및 부과세</span>
						
						<span id="taxPrice"></span>
					</div>
					
					<hr>
				</div>
				
				<div class="payment-wrap">
					<label class="title">결제</label>
					
					<hr>
				</div>
			</div>
		</div>
	</div>
</body>
</html>