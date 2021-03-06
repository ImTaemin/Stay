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
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- css -->
<link rel="stylesheet" href="../css/hostMain.css">
<!-- js -->
<script src="${root}/js/hostReservationList.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="hostMain">
		<c:if test="${checkReser == 0}">
			<div class="none-hostMain-top">
				<span class="none-span">예약된 숙소가 없습니다.</span>
			</div>
		</c:if>
			
		<c:if test="${checkReser != 0}">
			<div class="hostMain-top">
				<!-- 선택된 상태의 숙소 카드 -->
				<c:forEach var="list" items="${reserThreeList}">
					<div class="card">
						<img class="card-img-top" src="../../photo/roomPhoto/${list.roomDto.photos}">
						
						<div class="card-body top">
							<h5 class="card-title">${list.resDto.start_date} ~ ${list.resDto.end_date}</h5>
							<b class="card-text">
								${list.roomDto.name}
							</b>
							<p class="card-text">
								${list.roomDto.addr_load}
							</p>
						</div>
						
						<div class="card-body bottom" list-no="${list.resDto.no}" onclick="btnClick(this)">
							<label class="card-link">예약 정보 더보기</label>
							<i class="bi bi-chevron-right"></i>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
		
		<div class="hostMain-middle">
		
			<!-- 오늘날짜 출력 -->
			<c:set var="now" value="<%=new java.util.Date()%>" /> 
			<fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일 (E)" var="today" />
			<div class="hostMain-date">
				${today }
			</div>
			
			<!-- 상태탭 -->
			<div class="hostMain-option">
				<!-- 탭 목록 -->
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<!-- 호스팅 중 -->
					<li class="nav-item" role="presentation">
						<button class="nav-link active" id="hosting-tab" data-bs-toggle="tab" data-bs-target="#hosting" type="button" role="tab" aria-controls="hosting" aria-selected="true">현재 호스팅</button>
					</li>
					
					<!-- 체크인 예정 -->
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="checkin-tab" data-bs-toggle="tab" data-bs-target="#checkin" type="button" role="tab" aria-controls="checkin" aria-selected="false">체크인 예정</button>
					</li>
					
					<!-- 체크아웃 예정 -->
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="checkout-tab" data-bs-toggle="tab" data-bs-target="#checkout" type="button" role="tab" aria-controls="checkout" aria-selected="false">체크아웃 예정</button>
					</li>
				</ul>
			</div>
		</div>
		
		<div class="hostMain-bottom">
			<div class="tab-content">
				<div class="tab-pane active" id="hosting" role="tabpanel" aria-labelledby="hosting-tab">
					<c:forEach var="list" items="${hostingList}">
						<div class="hostMain-reservation" list-no="${list.resNoDto.reser_no}" onclick="btnClick(this)">
							<div class="content-name">
								<span>${list.roomDto.name}</span>
								<div class="content-site">${list.roomDto.addr_load}</div>
							</div>
							
							<div class="hostMain-content-wrap">
								<div class="hostMain-number">예약번호 : ${list.resNoDto.reser_no}</div>
							
								<div class="hostMain-content">
									<div class="content-checkIn">체크인
										<div class="content-inDate">${list.resDto.start_date}</div>
									</div>
									<div class="content-checkOut">체크아웃
										<div class="content-outDate">${list.resDto.end_date}</div>
									</div>
									<div class="content-guest">게스트
										<c:if test="${list.joinDto.count == null}">
											<div class="content-number">1명</div>
										</c:if>
										
										<c:if test="${list.joinDto.count != null}">
											<div class="content-number">${list.joinDto.count}명</div>
										</c:if>
									</div>
									<div class="content-payment">결제 금액
										<div class="content-cost">
											<fmt:formatNumber value="${list.resDto.price}" type="currency" currencySymbol="￦"/>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				
				<div class="tab-pane" id="checkin" role="tabpanel" aria-labelledby="checkin-tab">
					<c:forEach var="list" items="${checkInList}">
						<div class="hostMain-reservation" list-no="${list.resDto.no}" onclick="btnClick(this)">
							<div class="content-name">
								<span>${list.roomDto.name}</span>
								<div class="content-site">${list.roomDto.addr_load}</div>
							</div>
							
							<div class="hostMain-content-wrap">
								<div class="hostMain-number">예약번호 : ${list.resDto.no}</div>
							
								<div class="hostMain-content">
									<div class="content-checkIn">체크인
										<div class="content-inDate">${list.resDto.start_date}</div>
									</div>
									<div class="content-checkOut">체크아웃
										<div class="content-outDate">${list.resDto.end_date}</div>
									</div>
									<div class="content-guest">게스트
										<c:if test="${list.joinDto.count == null}">
											<div class="content-number">1명</div>
										</c:if>
										
										<c:if test="${list.joinDto.count != null}">
											<div class="content-number">${list.joinDto.count}명</div>
										</c:if>
									</div>
									<div class="content-payment">결제 금액
										<div class="content-cost">
											<fmt:formatNumber value="${list.resDto.price}" type="currency" currencySymbol="￦"/>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			
				<div class="tab-pane" id="checkout" role="tabpanel" aria-labelledby="checkout-tab">
					<c:forEach var="list" items="${checkOutList}">
						<div class="hostMain-reservation" list-no="${list.resDto.no}" onclick="btnClick(this)">
							<div class="content-name">
								<span>${list.roomDto.name}</span>
								<div class="content-site">${list.roomDto.addr_load}</div>
							</div>
							
							<div class="hostMain-content-wrap">
								<div class="hostMain-number">예약번호 : ${list.resDto.no}</div>
							
								<div class="hostMain-content">
									<div class="content-checkIn">체크인
										<div class="content-inDate">${list.resDto.start_date}</div>
									</div>
									<div class="content-checkOut">체크아웃
										<div class="content-outDate">${list.resDto.end_date}</div>
									</div>
									<div class="content-guest">게스트
										<c:if test="${list.joinDto.count == null}">
											<div class="content-number">1명</div>
										</c:if>
										
										<c:if test="${list.joinDto.count != null}">
											<div class="content-number">${list.joinDto.count}명</div>
										</c:if>
									</div>
									<div class="content-payment">결제 금액
										<div class="content-cost">
											<fmt:formatNumber value="${list.resDto.price}" type="currency" currencySymbol="￦"/>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>