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
<link rel="stylesheet" href="${root}/css/hostReservationDetail.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<!-- js -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="reser-detail-wrap">
		<div class="room-detail">
			<!-- 숙소 이름 -->
			<div class="title">
				<a href="/room/content?no=${roomDto.no}">${roomDto.name}</a>
			</div>
			
			<!-- 숙소 주소, 예약 번호 -->
			<div class="addr">
				<label>주소 | ${roomDto.addr_load} ${roomDto.addr_detail}</label>	
				<label>예약번호 | ${reserDto.no}</label>
			</div>
			
			<hr style="margin: 20px 0;">
			
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
							<label>게스트 (최대 ${roomDto.max_per}명)</label>
						</div>
						
						<hr>
						
						<div class="guest">
							<span id="joinNum">${joinGuestNum}명</span>
							
							<div class="guest-btn">	
								<div data-toggle="modal" data-target="#guestList">
									<span class="bi bi-three-dots"></span>
								</div>
							</div>
						</div>
						
						<!-- 게스트리스트 모달 -->
						<div id="guestList" class="modal">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">게스트 상세 목록</h4>
									</div>
									
									<div class="modal-body">
										<div class="main-guest-wrap">
											<div class="main-guest-img">
												<img src="${root}/photo/memberPhoto/${guestDto.photo}">
											</div>
											
											<label>${guestDto.id}</label>
											
											<button type="button" class="btn btn-default"	>메인 게스트</button>
										</div>
										
										<div class="join-guest-wrap" id="${join.memDto.id}">
										</div>
										
										<c:forEach var="join" items="${joinList}">
											<div class="join-guest-wrap" id="${join.memDto.id}">
												<hr>
												
												<div class="join-guest-de">
													<div class="join-guest-img">
														<img src="${root}/photo/memberPhoto/${join.memDto.photo}">
													</div>
													
													<label>${join.memDto.id}</label>
												</div>
											</div>
										</c:forEach>
									</div>
									
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<hr>
			
			<c:if test="${preCheck == false}">
				<!-- 취소 버튼 -->
				<div class="can-wrap">
					<c:if test="${canReserDto == null}">
						<button class="btn btn-danger" id="can-reser" no="${reserDto.no}" price="${reserDto.price}"
						onclick="reserCan(this)">예약 취소</button>
					</c:if>
					
					<c:if test="${canReserDto.refund_check == 'ing'}">
						<button class="btn btn-secondary can-btn" id="can-reser" no="${reserDto.no}" onclick="reserRef(this)">예약 취소가 진행 중입니다.</button>
					</c:if>
					
					<c:if test="${canReserDto.refund_check == 'end'}">
						<button class="btn btn-secondary can-btn" id="can-reser" style="pointer-events: none;">결제 환불이 진행 중입니다.</button>
					</c:if>
					
					<c:if test="${canReserDto.refund_check == 'can'}">
						<button class="btn btn-warning can-btn" id="can-reser" style="pointer-events: none;">예약 취소가 완료되었습니다.</button>
					</c:if>
				</div>
			</c:if>
		</div>
	</div>
	
	<!-- js -->
	<script src="${root}/js/HostReservationDetail.js"></script>
</body>
</html>