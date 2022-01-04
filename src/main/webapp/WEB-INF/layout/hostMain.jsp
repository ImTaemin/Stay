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
		<div class="hostMain-top">
		
			<!-- 선택된 상태의 숙소 카드 -->
			<c:forEach var="list" items="${inThreeList}">
				<c:forEach var="room" items="${inThreeRoom}">
					<c:if test="${list.room_no == room.no}">
						<div class="card">
							<img class="card-img-top" src="../../photo/roomPhoto/${room.photos}">
							
							<div class="card-body top">
								<h5 class="card-title">${list.start_date} ~ ${list.end_date}</h5>
								<b class="card-text">
									${room.name}
								</b>
								<p class="card-text">
									${room.addr_load}
								</p>
							</div>
							
							<div class="card-body bottom" list-no="${list.no}" onclick="btnClick(this)">
								<label class="card-link">예약 정보 더보기</label>
								<i class="bi bi-chevron-right"></i>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</c:forEach>
			 
		</div>
		
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
				
					<!-- 예정 -->
					<li class="nav-item" role="presentation">
						<button class="nav-link active" id="now-tab" data-bs-toggle="tab" data-bs-target="#now" type="button" role="tab" aria-controls="now" aria-selected="true">예정된 예약</button>
					</li>
					
					<!-- 이전 -->
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="pre-tab" data-bs-toggle="tab" data-bs-target="#pre" type="button" role="tab" aria-controls="pre" aria-selected="false">이전 예약</button>
					</li>
					
					<!-- 취소 -->
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="can-tab" data-bs-toggle="tab" data-bs-target="#can" type="button" role="tab" aria-controls="can" aria-selected="false">취소된 예약</button>
					</li>
					
				</ul>
			</div>
		</div>
		
		<div class="hostMain-bottom">
			<div class="tab-content">
				<div class="tab-pane" id="now" role="tabpanel" aria-labelledby="now-tab">
					<c:forEach var="list" items="${nowList}">
								<div class="hostMain-reservation" list-no="${list.resDto.no}" onclick="btnClick(this)">
									<div class="hostMain-number">예약번호 : ${list.resDto.no}</div>
									<div class="hostMain-content">
										<div class="content-name">${list.roomDto.name}
											<div class="content-site">${list.roomDto.addr_load}</div>
										</div>
										<div class="content-checkIn">체크인
											<div class="content-inDate">${list.resDto.start_date}</div>
										</div>
										<div class="content-checkOut">체크아웃
											<div class="content-outDate">${list.resDto.end_date}</div>
										</div>
										<div class="content-guest">게스트
											<div class="content-number">${list.joinDto.count}명</div>
										</div>
										<div class="content-payment">결제세부정보
											<div class="content-cost">
												<fmt:formatNumber value="${list.resDto.price}" type="currency" currencySymbol="￦"/>
											</div>
										</div>
									</div>
								</div>
					</c:forEach>
				</div>
				
				
				<div class="tab-pane active" id="pre" role="tabpanel" aria-labelledby="pre-tab">
					<c:forEach var="list" items="${preList}">
								<div class="hostMain-reservation" list-no="${list.resDto.no}" onclick="btnClick(this)">
									<div class="hostMain-number">예약번호 : ${list.resDto.no}</div>
									<div class="hostMain-content">
										<div class="content-name">${list.roomDto.name}
											<div class="content-site">${list.roomDto.addr_load}</div>
										</div>
										<div class="content-checkIn">체크인
											<div class="content-inDate">${list.resDto.start_date}</div>
										</div>
										<div class="content-checkOut">체크아웃
											<div class="content-outDate">${list.resDto.end_date}</div>
										</div>
										<div class="content-guest">게스트
											<div class="content-number">${list.joinDto.count}명</div>
										</div>
										<div class="content-payment">결제세부정보
											<div class="content-cost">
												<fmt:formatNumber value="${list.resDto.price}" type="currency" currencySymbol="￦"/>
											</div>
										</div>
									</div>
								</div>
					</c:forEach>
				</div>
			
				<div class="tab-pane" id="can" role="tabpanel" aria-labelledby="can-tab">
					<c:forEach var="list" items="${canList}">
								<div class="hostMain-reservation" list-no="${list.resDto.no}" onclick="btnClick(this)">
									<div class="hostMain-number">예약번호 : ${list.resDto.no}</div>
									<div class="hostMain-content">
										<div class="content-name">${list.roomDto.name}
											<div class="content-site">${list.roomDto.addr_load}</div>
										</div>
										<div class="content-checkIn">체크인
											<div class="content-inDate">${list.resDto.start_date}</div>
										</div>
										<div class="content-checkOut">체크아웃
											<div class="content-outDate">${list.resDto.end_date}</div>
										</div>
										<div class="content-guest">게스트
											<div class="content-number">${list.joinDto.count}명</div>
										</div>
										<div class="content-payment">결제세부정보
											<div class="content-cost">
												<fmt:formatNumber value="${list.resDto.price}" type="currency" currencySymbol="￦"/>
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