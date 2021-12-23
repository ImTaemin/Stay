<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="${root}/css/paymentForm.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<!-- js -->
<script src="${root}/js/paymentForm.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="pay-wrap">
		<div class="title">
			<div class="back" onclick="history.back()">
				<i class="bi bi-chevron-left"></i>
			</div>
			
			<div class="label">
				<label>확인 및 결제</label>
			</div>
		</div>
		
		<div class="main">
			<!-- 예약 정보 -->
			<div class="reser-detail">
				<div class="info">
					<div class="title">
						<label>예약 정보</label>
					</div>
					
					<div class="day">
						<!-- 체크인 날짜 -->
						<div class="check-in">
							<div class="day-title">
								<div class="de-title">
									<label>체크인 날짜</label>
								</div>
								
								<div class="update">
									<i class="bi bi-pencil-square"></i>
								</div>
							</div>
							
							<hr>
							
							<div class="day-info">
								<label>${start[0]}년 ${start[1]}월 ${start[2]}일</label>
							</div>					
						</div>
						
						<!-- 체크 아웃 날짜 -->
						<div class="check-out">
							<div class="day-title">
								<div class="de-title">
									<label>체크아웃 날짜</label>
								</div>
								
								<div class="update">
									<i class="bi bi-pencil-square"></i>
								</div>
							</div>
							
							<hr>
							
							<div class="day-info">
								<label>${end[0]}년 ${end[1]}월 ${end[2]}일</label>
							</div>					
						</div>
					</div>
					
					<hr style="margin-top: 40px;">
					
					<div class="title" style="margin-top: 40px;">
						<label>결제 방식</label>
					</div>
					
					<div class="pay" style="margin-bottom: 20px;">
						<div class="kakao">
							<div class="kakao-pay">
								<img src="${root}/photo/카카오페이.png" style="margin-right: 10px;">
								<img src="${root}/photo/카카오페이_영문.png">
							</div>
						</div>
						
						<div class="check" id="kakao">
							<i id="kakaoI" class="bi bi-circle" onclick="payClick(this)"></i>
						</div>
					</div>
					
					<div class="pay">
						<div class="card">
							<label>카드 결제</label>
						</div>
						
						<div class="check" id="card">
							<i id="cardI" class="bi bi-circle" onclick="payClick(this)"></i>
						</div>
					</div>
					
					<div class="wallet">
						<div class="wallet-de">
							<c:forEach var="card" items="${cardList}">
								<div class="card-title">
									<i class="bi bi-credit-card"></i>
									${card.num}
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 요금 정보 -->
			<div class="reser-pay">
				
			</div>
		</div>
	</div>
</body>
</html>