<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<title>Insert title here</title>
</head>
<body>
	<form action="insert" method="post" class="pay-wrap" id="frm" accept-charset="euc-kr">
		<!-- hidden -->
		<input type="hidden" name="roomNo" value="${dto.roomDto.no}">
		<input type="hidden" name="startDate" value="${startDate}">
		<input type="hidden" name="endDate" value="${endDate}">
		<input type="hidden" name="betweenDay" value="${betweenDay}">
		<input type="hidden" name="calPrice" value="${calPrice}">
		<input type="hidden" name="taxPrice" value="${taxPrice}">
		<input type="hidden" name="allPrice" value="${allPrice}">
		<input type="hidden" name="payMethod">
		<input type="hidden" name="cardNum">
		
		<script type="text/javascript">
			function formCheck(){
				var kakao = $("#kakao").attr("check");
				var card = $("#card").attr("check");
				
				if (kakao == "0" && card == "0") {
					$("#frm").attr("action", "");
				
					Swal.fire({
						icon: 'error',
						title: '결제 방법을 선택해주세요.'
					});
				} else if(kakao == "1" && card == "0"){
					$.ajax({
						url:"kakaopay",
						dataType:"json",
						data:{"roomNo" : ${dto.roomDto.no}, "allPrice" : ${allPrice}, "startDate" : "${startDate}", "endDate" : "${endDate}" ,  "taxPrice" : ${allPrice * 0.2}},
						contentType: "application/json; charset=utf-8",
					}).done(function(resp){
						if(resp.status === 500){
							alert("카카오페이결제를 실패하였습니다.")
						} else{
							//결제 고유 번호
							var box = resp.next_redirect_pc_url;
							// 새창 열기
							// window.open(box, '_blank', "width=500,height=500");
							location.href = box;
						}
					}).fail(function(error){
						alert(JSON.stringify(error));
					});
				} else {
					$("#frm").submit();
				}
			}
		</script>
		
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

								<div class="update" data-toggle="modal" data-target="#checkInModal">
									<i class="bi bi-pencil-square"></i>
								</div>
							</div>

							<hr>

							<div class="day-info">
								<label id="start1">${start[0]}</label>년&nbsp;
								<label id="start2">${start[1]}</label>월&nbsp;
								<label id="start3">${start[2]}</label>일
							</div>
						</div>

						<!-- 체크 아웃 날짜 -->
						<div class="check-out">
							<div class="day-title">
								<div class="de-title">
									<label>체크아웃 날짜</label>
								</div>

								<div class="update" data-toggle="modal" data-target="#checkOutModal">
									<i class="bi bi-pencil-square"></i>
								</div>
							</div>

							<hr>

							<div class="day-info">
								<label id="end1">${end[0]}</label>년&nbsp;
								<label id="end2">${end[1]}</label>월&nbsp;
								<label id="end3">${end[2]}</label>일
							</div>
						</div>
					</div>
					
					<jsp:useBean id="now" class="java.util.Date"/>
					<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/>
					
					<!-- 체크인 모달 -->
					<div id="checkInModal" class="modal">
						<div class="modal-dialog">
							<!-- Modal content -->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">체크인 날짜 변경하기</h4>
								</div>
								
								<div class="modal-body">
									<input type="date" id="re-check-in" min="${today}">
									<button type="button" id="bodyBtn" class="btn btn-primary" onclick="changeCheckIn()" data-dismiss="modal">변경</button>
								</div>
								
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
					
					<!-- 체크아웃 모달 -->
					<div id="checkOutModal" class="modal">
						<div class="modal-dialog">
							<!-- Modal content -->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">체크아웃 날짜 변경하기</h4>
								</div>
								
								<div class="modal-body">
									<input type="date" id="re-check-out" min="${startDate}">
									<button type="button" id="bodyBtn" class="btn btn-primary" onclick="changeCheckOut()" data-dismiss="modal">변경</button>
								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
					
					<hr style="margin-top: 40px;">

					<div class="title" style="margin-top: 40px;">
						<label>결제 방식</label>
					</div>
					
					<!-- 카카오페이 결제 -->
					<div class="pay" style="margin-bottom: 20px;">
						<div class="kakao">
							<div class="kakao-pay">
								<img src="${root}/photo/카카오페이.png" style="margin-right: 10px;">
								<img src="${root}/photo/카카오페이_영문.png">
							</div>
						</div>

						<div class="check">
							<i class="bi bi-circle" id="kakao" name="kakao" check="0" onclick="payClick(this)"></i>
						</div>
					</div>
					
					<!-- 카드 결제 -->
					<div class="pay">
						<div class="card">
							<label>카드 결제</label>
						</div>
						
						<div class="check">
							<i class="bi bi-circle" id="card" name="card" check="0" onclick="payClick(this)"></i>
						</div>
					</div>
					
					<div class="wallet"></div>

					<hr style="margin-top: 40px;">
					
					<!-- 환불 안내 -->
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
						아래 버튼을 선택함으로써, 호스트가 설정한 숙소 이용규칙, 쉼의 코로나19 방역 수칙 및 게스트 환불 정책에 동의합니다.
					</div>
					
					<div class="reser-btn">
						<button type="button" onclick="formCheck()" class="btn btn-primary">예약 하기</button>
					</div>
				</div>
			</div>

			<!-- 요금 정보 -->
			<div class="reser-pay-wrap">
				<div class="reser-pay">
					<!-- 숙소 정보 -->
					<div class="room">
						<div class="photo">
							<img src="${root}/photo/roomPhoto/${dto.roomDto.photos}">
						</div>
		
						<div class="detail">
							<div class="name">
								<b>${dto.roomDto.name}</b>
								<label>${dto.roomDto.host_id} 님의 숙소</label>
							</div>
							
							<label>⭐ ${dto.atDto.avg} 점 (후기 ${dto.atDto.total}개)</label>
						</div>
					</div>
		
					<hr>
		
					<div class="title">
						<label>요금 세부정보</label>
					</div>
					
					<c:set var="calPrice" value="${dto.roomDto.price * betweenDay}"/>
					<c:set var="taxPrice" value="${calPrice * 0.2}"/>
					
					<!-- 요금 상세 정보 -->
					<div class="room-price">
						<div>
							<fmt:formatNumber value='${dto.roomDto.price}' type='currency' currencySymbol='￦' />
							 X <label id="betweenDay">${betweenDay}</label> 박
						</div>
						
						<b>
							<fmt:formatNumber value='${calPrice}' type='currency' currencySymbol='￦' var="calPrice"/>
							<label id="calPrice">${calPrice}</label>
						</b>
					</div>
					
					<div class="room-price">
						<div>수수료 및 부과세</div>
						
						<b>
							<fmt:formatNumber value='${taxPrice}' type='currency' currencySymbol='￦' var="taxPrice"/>
							<label id="taxPrice">${taxPrice}</label>
						</b>
					</div>
					
					<hr>
					
					<div class="all-price">
						<label>총 합계</label>
						
						<b>
							<fmt:formatNumber value='${allPrice}' type='currency' currencySymbol='￦' var="allPrice"/>
							<label id="allPrice">${allPrice}</label>
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
			var n = "";

			if (kakao == "0" && card == "0" && $(e).attr("id") == "kakao") {
				$(e).attr("class", "bi bi-check-circle-fill");
				$(e).attr("check", "1");
			} else if (kakao == "1" && card == "0" && $(e).attr("id") == "kakao") {
				$(e).attr("class", "bi bi-circle");
				$(e).attr("check", "0");
			} else if (kakao == "0" && card == "0" && $(e).attr("id") == "card") {
				$(e).attr("class", "bi bi-check-circle-fill");
				$(e).attr("check", "1");
				
				if(${cardList != "[]"}){
					s += "<select id='card-list' name='card_num'>";
					s += "<c:forEach var='card' items='${cardList}'>";
					s += "<option value='${card.num}'>";
					s += "${card.name}&nbsp;(${card.last_num})";
					s += "</option>";
					s += "</c:forEach>";
					s += "</select>";
					s += `<button type='button' id='cardInsert' class='btn btn-primary' onclick='location.href="/card/insertform"'>`;
					s += "결제 카드 추가</button>";
					
					$(".wallet").html(s);
				} else if(${cardList == "[]"}) {
					n += `<button type='button' id='cardInsert' class='btn btn-primary' onclick='location.href="/card/insertform"'>`;
					n += "결제 카드 추가</button>";
					
					$(".wallet").html(n);
				}
			} else if (kakao == "1" && card == "0") {
				$(e).attr("class", "bi bi-check-circle-fill");
				$(e).attr("check", "1");

				$("#kakao").attr("class", "bi bi-circle");
				$("#kakao").attr("check", "0");

				if(${cardList != "[]"}){
					s += "<select id='card-list' name='card_num'>";
					s += "<c:forEach var='card' items='${cardList}'>";
					s += "<option value='${card.num}'>";
					s += "${card.name}&nbsp;(${card.last_num})";
					s += "</option>";
					s += "</c:forEach>";
					s += "</select>";
					s += `<button type='button' id='cardInsert' class='btn btn-primary' onclick='location.href="/card/insertform"'>`;
					s += "결제 카드 추가</button>";
					
					$(".wallet").html(s);
				} else if(${cardList == "[]"}) {
					n += `<button type='button' id='cardInsert' class='btn btn-primary' onclick='location.href="/card/insertform"'>`;
					n += "결제 카드 추가</button>";
					
					$(".wallet").html(n);
				}
			} else {
				$(e).attr("class", "bi bi-check-circle-fill");
				$(e).attr("check", "1");

				$("#card").attr("class", "bi bi-circle");
				$("#card").attr("check", "0");

				s = "";

				$(".wallet").html(s);
			}
			
			if (kakao == "1" || $(e).attr("id") == "kakao") {
				$('input[name=payMethod]').attr('value', 'kakao');
			} else if (card == "1" || $(e).attr("id") == "card") {
				var cardNum = $("#card-list").attr("value");

				$('input[name=payMethod]').attr('value', 'card');
				$('input[name=cardNum]').attr('value', cardNum);
			}
		}
		
		function changeCheckIn() {
			var startDate = $('#re-check-in').val();

			var start = startDate.split("-");

			var start_date = new Date(start[0], start[1], start[2]);
			var end_date = new Date(${end[0]}, ${end[1]}, ${end[2]});
			
			var betweenMs = end_date.getTime() - start_date.getTime();
			var betweenDay = betweenMs / (1000 * 60 * 60 * 24);
			
			var calPrice = ${dto.roomDto.price} * betweenDay;
			var commaCal = Number(calPrice).toLocaleString();
			
			var taxPrice = calPrice * 0.2;
			var commaTax = Number(taxPrice).toLocaleString();

			var allPrice = calPrice + taxPrice;
			var commaAll = Number(allPrice).toLocaleString();
			
			if(betweenDay <= 0) {
				Swal.fire({
					icon: 'error',
					title: '날짜를 다시 선택해주세요.',
					text: '체크인 날짜를 다시 입력해주세요.'
				});
			} else {
				$('label[id=start1]').html(start[0]);
				$('label[id=start2]').html(start[1]);
				$('label[id=start3]').html(start[2]);
				
				$('label[id=betweenDay]').html(betweenDay);
				$('label[id=calPrice]').html("￦" + commaCal);
				$('label[id=taxPrice]').html("￦" + commaTax);
				$('label[id=allPrice]').html("￦" + commaAll);
				
				$('input[name=startDate]').attr('value', startDate);
				$('input[name=betweenDay]').attr('value', betweenDay);
				$('input[name=calPrice]').attr('value', calPrice);
				$('input[name=taxPrice]').attr('value', taxPrice);
				$('input[name=allPrice]').attr('value', allPrice);
			}
		}

		function changeCheckOut() {
			var endDate = $('#re-check-out').val();

			var end = endDate.split("-");

			var start_date = new Date(${start[0]}, ${start[1]}, ${start[2]});
			var end_date = new Date(end[0], end[1], end[2]);
			
			var betweenMs = end_date.getTime() - start_date.getTime();
			var betweenDay = betweenMs / (1000 * 60 * 60 * 24);
			
			var calPrice = ${dto.roomDto.price} * betweenDay;
			var commaCal = Number(calPrice).toLocaleString();
			
			var taxPrice = calPrice * 0.2;
			var commaTax = Number(taxPrice).toLocaleString();

			var allPrice = calPrice + taxPrice;
			var commaAll = Number(allPrice).toLocaleString();
			
			if(betweenDay <= 0) {
				Swal.fire({
					icon: 'error',
					title: '날짜를 다시 선택해주세요.',
					text: '체크아웃 날짜를 다시 입력해주세요.'
				});
			} else {
				$('label[id=end1]').html(end[0]);
				$('label[id=end2]').html(end[1]);
				$('label[id=end3]').html(end[2]);
				
				$('label[id=betweenDay]').html(betweenDay);
				$('label[id=calPrice]').html("￦" + commaCal);
				$('label[id=taxPrice]').html("￦" + commaTax);
				$('label[id=allPrice]').html("￦" + commaAll);
				
				$('input[name=endDate]').attr('value', endDate);
				$('input[name=betweenDay]').attr('value', betweenDay);
				$('input[name=calPrice]').attr('value', calPrice);
				$('input[name=taxPrice]').attr('value', taxPrice);
				$('input[name=allPrice]').attr('value', allPrice);
			}
		}
	</script>
</body>
</html>