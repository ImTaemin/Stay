<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
								<span class="bi bi-receipt-cutoff" onclick="postPopUp()"></span>
								<!-- from -->
								<form action='/receipt/detail' method='post' id="receiptPop">
									<input type="hidden" name="reserNo" value="${reserDto.no}">
								</form>
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
							
							<!-- hidden -->
							<input type="hidden" name="joinNum" value="${joinGuestNum}">
							
							<div class="guest-btn">
								<c:if test="${roomDto.max_per > joinGuestNum}">
									<c:if test="${preCheck == false and canCheck == false}">
										<span class="bi bi-plus-circle" onclick="addGuest(this)" style="margin-right: 20%"
										no="${reserDto.no}" hostId="${hostDto.id}" myid="${sessionScope.myid}"
										maxPer="${roomDto.max_per}" id="addGuest"></span>
									</c:if>
								</c:if>
								
								<div onclick="guestInfo(${reserDto.no}, ${roomDto.max_per})" data-toggle="modal" data-target="#guestList">
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
												<img id="main-img" src="${root}/photo/memberPhoto/${guestDto.photo}">
											</div>
											
											<label>${guestDto.id}</label>
											
											<button type="button" class="btn btn-default" onclick="location.href='../chat'">메인 게스트</button>
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
			
			<!-- 게스트 후기 -->
			<div class="guest-comment-wrap">
				<div class="comment">
					<c:if test="${gcommentDto == null and preCheck == true}">
						<div class="empty">등록된 후기가 없습니다.</div>
					</c:if>
					
					<c:if test="${gcommentDto != null and preCheck == true}">
						<div class="comment-title">
							<label class="title">게스트 후기  |  <i class="bi bi-star-fill" style="font-size: 2.0rem;"></i>${gcommentDto.rating} 점</label>
						</div>
					
						<div class="co-wrap">
							<div class="co-detail">
								<div class="mem-detail">
									<div class="mem-img">
										<c:if test="${not fn:contains(guestDto.id, '@')}">
											<img src="${root}/photo/memberPhoto/${guestDto.photo}"
											onclick="location.href='/profile/profileform?id=${guestDto.id}'">
										</c:if>
										
										<c:if test="${fn:contains(guestDto.id, '@')}">
											<img src="${guestDto.photo}" onclick="location.href='/profile/profileform?id=${guestDto.id}'">
										</c:if>
									</div>
								
									<div class="mem-content">
										<fmt:formatDate var="writeDay" value="${gcommentDto.write_day}" pattern="yyyy년 MM월 dd일"/>
										<span class="mem-id" onclick="location.href='/profile/profileform?id=${guestDto.id}'">${guestDto.id}</span>
										<span class="write-day">${writeDay}</span>
									</div>
								</div>
							 
								<div class="co-content">
									<span>${gcommentDto.content}</span>
								</div>
								
								
								<!-- 호스트 댓글 작성 -->
								<div id="insertContainer">
									<form action="/hcomment/insert" method="post" name="commentInsert" class="comment-wrap">
										<div class="comment">
											<textarea class="content-input" name="content"></textarea>
											<button type="button" id="insert-btn" class="btn btn-primary">댓글 저장</button>
										</div>
									</form>
								</div>
								
								<!-- 댓글 수정 및 삭제 -->
								<div id="updateDeleteContainer">
									<form action="/hcomment/update" method="post" name="commentUpdate" class="comment-wrap">
										<div class="comment">
											<textarea class="content-update" name="content">${hCommentDto.content}</textarea>
											
											<div class="btn-wrap">
												<button type="button" id="update-btn" class="btn btn-primary">후기 수정</button>
												<button type="button" id="delete-btn" class="btn btn-danger">후기 삭제</button>
											</div>
										</div>
									</form>
								</div>
			
								<c:if test="${hcommentDto == null}">
									<script type="text/javascript">
										$("#insertContainer").show();
										$("#updateDeleteContainer").hide();
									</script>
								</c:if>
								
								<c:if test="${hcommentDto != null}">
									<script type="text/javascript">
										$("#updateDeleteContainer").show();
										$("#insertContainer").hide();
									</script>
								</c:if>
							</div>
							</div>
						  </c:if>
						</div>
						
				</div>
			</div>
	</div>
			
	
	<!-- js -->
	<script src="${root}/js/HostReservationDetail.js"></script>
</body>
</html>