<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<!-- js -->
<script src="${root}/js/paymentForm.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form action="insert" method="post" class="pay-wrap">
		<!-- hidden -->
		<input type="hidden" name="startDate" value="${startDate}">
		<input type="hidden" name="endDate" value="${endDate}">
		
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

						<div class="check">
							<i class="bi bi-circle" id="kakao" check="0"
								onclick="payClick(this)"></i>
						</div>
					</div>

					<div class="pay">
						<div class="card">
							<label>카드 결제</label>
						</div>

						<div class="check">
							<i class="bi bi-circle" id="card" check="0"
								onclick="payClick(this)"></i>
						</div>
					</div>

					<div class="wallet"></div>

					<hr style="margin-top: 40px;">

					<div class="title" style="margin-top: 40px;">
						<label>환불 안내</label>
					</div>

					<div class="payback">
						<label>
							${monthArray[1]}월 ${monthArray[2]}일 전까지 무료로 예약을 취소할 수 있습니다.<br>
							그 후에는 ${weekArray[1]}월 ${weekArray[2]}일 전에 예약을 취소하면 첫 1박 요금 및 서비스 수수료를 제외한 요금의 50%가 환불됩니다.
						</label>
					</div>

					<hr>
					
					<div class="agree">
						아래 버튼을 선택함으로써, 호스트가 설정한 숙소 이용규칙, 에어비앤비 코로나19 방역 수칙 및 게스트 환불 정책에 동의합니다.
					</div>
					
					<div class="reser-btn">
						<button type="submit" class="btn btn-primary">예약 하기</button>
					</div>
				</div>
			</div>

			<!-- 요금 정보 -->
			<div class="reser-pay-wrap">
				<div class="reser-pay">
					<div class="room">
						<div class="photo">
							<img src="${root}/photo/roomPhoto/${roomDto.photos}">
						</div>
		
						<div class="detail">
							<div class="name">
								<b>${roomDto.name}</b>
								<label>${roomDto.host_id} 님의 숙소</label>
							</div>
							
							<label>⭐ ${avgRating} 점 (후기 ${totalComment}개)</label>
						</div>
					</div>
		
					<hr>
		
					<div class="title">
						<label>요금 세부정보</label>
					</div>
					
					<c:set var="calPrice" value="${roomDto.price * betweenDay}"/>
					<c:set var="taxPrice" value="${calPrice * 0.2}"/>
					
					<div class="room-price">
						<div>
							<fmt:formatNumber value='${roomDto.price}' type='currency' currencySymbol='￦' /> X ${betweenDay} 박
						</div>
						
						<b>
							<fmt:formatNumber value='${calPrice}' type='currency' currencySymbol='￦' />
						</b>
					</div>
					
					<div class="room-price">
						<div>수수료 및 부과세</div>
						
						<b>
							<fmt:formatNumber value='${taxPrice}' type='currency' currencySymbol='￦' />
						</b>
					</div>
					
					<hr>
					
					<div class="all-price">
						<label>총 합계</label>
						
						<b>
							<fmt:formatNumber value='${allPrice}' type='currency' currencySymbol='￦' />
						</b>
					</div>
				</div>
			</div>
		</div>
	</form>

	<script type="text/javascript">
		// 카드 클릭 이벤트
		function payClick(e) {
			var kakao = $("#kakao").attr("check");
			var card = $("#card").attr("check");
			var s = "";

			if (kakao == "0" && card == "0" && $(e).attr("id") == "kakao") {
				$(e).attr("class", "bi bi-check-circle-fill");
				$(e).attr("check", "1");
			} else if (kakao == "0" && card == "0" && $(e).attr("id") == "card") {
				$(e).attr("class", "bi bi-check-circle-fill");
				$(e).attr("check", "1");

				s += "<select id='card-list' name='card-list'>";
				s += "<c:forEach var='card' items='${cardList}'>";
				s += "<option value='${card.name}'>";
				s += "${card.name}&nbsp;(${card.num})";
				s += "</option>";
				s += "</c:forEach>";
				s += "</select>";

				$(".wallet").html(s);
			} else if (kakao == "1" && card == "0") {
				$(e).attr("class", "bi bi-check-circle-fill");
				$(e).attr("check", "1");

				$("#kakao").attr("class", "bi bi-circle");
				$("#kakao").attr("check", "0");

				s += "<select id='card-list' name='card-list'>";
				s += "<c:forEach var='card' items='${cardList}'>";
				s += "<option value='${card.name}'>";
				s += "${card.name}&nbsp;(${card.num})";
				s += "</option>";
				s += "</c:forEach>";
				s += "</select>";

				$(".wallet").html(s);
			} else {
				$(e).attr("class", "bi bi-check-circle-fill");
				$(e).attr("check", "1");

				$("#card").attr("class", "bi bi-circle");
				$("#card").attr("check", "0");

				s = "";

				$(".wallet").html(s);
			}
		}
	</script>
</body>
</html>