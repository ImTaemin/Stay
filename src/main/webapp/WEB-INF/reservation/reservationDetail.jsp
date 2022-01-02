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
							<label>공동 게스트 (최대 ${roomDto.max_per}명)</label>
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
			
			<hr>
			
			<c:if test="${preCheck == true}">
				<!-- 후기 작성 -->
				<form action="/comment/insert" method="post" name="commentInsert" class="comment-wrap">
					<c:if test="${gCommentDto.no == null}">
						<div class="rating">
						    <input type="radio" name="rate" id="star-1" onclick="getStarNum(this)">
						    <input type="radio" name="rate" id="star-2" onclick="getStarNum(this)">
						    <input type="radio" name="rate" id="star-3" onclick="getStarNum(this)">
						    <input type="radio" name="rate" id="star-4" onclick="getStarNum(this)">
						    <input type="radio" name="rate" id="star-5" onclick="getStarNum(this)">
						    
						    <div class="content">
						        <div class="stars">
						            <label for="star-1" class="star-1 fas fa-star"></label>
						            <label for="star-2" class="star-2 fas fa-star"></label>
						            <label for="star-3" class="star-3 fas fa-star"></label>
						            <label for="star-4" class="star-4 fas fa-star"></label>
						            <label for="star-5" class="star-5 fas fa-star"></label>
						        </div>
						    </div>
						    
						    <!-- hidden -->
						    <input type="hidden" name="reserNo" value="${reserDto.no}">
						    
						    <span class="numb">점</span>
						</div>
						
						<div class="comment">
							<textarea class="content-input" name="content"></textarea>
							<button type="button" id="insert-btn" class="btn btn-primary">후기 저장</button>
						</div>
					</c:if>
				</form>
				
				<!-- 후기 수정 -->
				<form action="/comment/update" method="post" name="commentUpdate" class="comment-wrap">
					<c:if test="${gCommentDto != null}">
						<div class="rating">
							<div class="rate-part">
								<input type="radio" name="rate" id="star-1" value="${gCommentDto.rating}"
								 ${gCommentDto.rating == '1.0' ? 'checked="checked"' : ''} onclick="changeStarNum(this)">
							    <input type="radio" name="rate" id="star-2" value="${gCommentDto.rating}"
							     ${gCommentDto.rating == '2.0' ? 'checked="checked"' : ''} onclick="changeStarNum(this)">
							    <input type="radio" name="rate" id="star-3" value="${gCommentDto.rating}"
							     ${gCommentDto.rating == '3.0' ? 'checked="checked"' : ''} onclick="changeStarNum(this)">
							    <input type="radio" name="rate" id="star-4" value="${gCommentDto.rating}"
							     ${gCommentDto.rating == '4.0' ? 'checked="checked"' : ''} onclick="changeStarNum(this)">
							    <input type="radio" name="rate" id="star-5" value="${gCommentDto.rating}"
							     ${gCommentDto.rating == '5.0' ? 'checked="checked"' : ''} onclick="changeStarNum(this)">
							    
							    <div class="content">
							        <div class="stars">
							            <label for="star-1" class="star-1 fas fa-star"></label>
							            <label for="star-2" class="star-2 fas fa-star"></label>
							            <label for="star-3" class="star-3 fas fa-star"></label>
							            <label for="star-4" class="star-4 fas fa-star"></label>
							            <label for="star-5" class="star-5 fas fa-star"></label>
							        </div>
							    </div>
							    
							    <!-- hidden -->
						    	<input type="hidden" name="reserNo" value="${reserDto.no}">
						    	
							    <fmt:parseNumber var="star" value="${gCommentDto.rating}" integerOnly="true" />
							    
							    <span class="numa" id="starNum">${star}</span>
							    <span class="numa" style="margin-left: 0;">점</span>
							</div>
						    
						    <div class="date-part">
						    	<fmt:formatDate var="writeDay" value="${gCommentDto.write_day}" pattern="yyyy년 MM월 dd일"/>
						    	<span class="date">작성일 | ${writeDay}</span>
						    </div>
						</div>
						
						<div class="comment">
							<textarea class="content-input" name="content">${gCommentDto.content}</textarea>
							
							<div class="btn-wrap">
								<button type="button" id="update-btn" class="btn btn-primary">후기 수정</button>
								<button type="button" id="delete-btn" class="btn btn-danger"
								onclick="location.href='/comment/delete?no=${reserDto.no}'">후기 삭제</button>
							</div>
						</div>
					</c:if>
				</form>
			</c:if>
			
			<c:if test="${preCheck == false}">
				<!-- 취소 버튼 -->
				<div class="can-wrap">
					<c:if test="${canReserDto == null}">
						<button class="btn btn-danger" id="can-reser" no="${reserDto.no}" price="${reserDto.price}"
						onclick="reserCan(this)">예약 취소</button>
					</c:if>
					
					<c:if test="${canReserDto.refund_check == 'ing'}">
						<button class="btn btn-secondary can-btn" id="can-reser" style="pointer-events: none;">예약 취소가 진행 중입니다.</button>
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
	<script src="${root}/js/reservationDetail.js"></script>
</body>
</html>