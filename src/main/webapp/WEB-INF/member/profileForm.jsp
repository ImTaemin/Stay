<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${root}/css/profileForm.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<c:set var="root" value="${pageContext.request.contextPath}" />
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<title>프로필 화면</title>
</head>
<body>
	<div class="profile-main">
		<div class="profile-top">
			<div class="profile-first">
				<div class="photo">
					<!-- 일반 로그인 -->
					<c:if test="${not fn:contains(memberDto.id, '@')}">
						<img alt="" src="${root}/photo/memberPhoto/${memberDto.photo}">
					</c:if>
					
					<!-- 카카오 로그인 -->
					<c:if test="${fn:contains(memberDto.id, '@')}">
						<img src="${memberDto.photo}">
					</c:if>
				</div>
			</div>

			<div class="profile-second">
				<c:if test="${sessionScope.loginok!=null}">
					<h2>
						<b id="report_id">${memberDto.id}</b>
						<b> 님의<br> <br>프로필입니다.</b>😊
					</h2>
				</c:if>
			</div>

			<div class="profile-third">
				<c:set var="heart_flag" value="false"/>
				<div class="profile-like">
					<c:if test="${likeFlag == 1}">
						<i onclick="heartClick(this)" guestId="${memberDto.id}" cnt="${memberLikeNum}"
						class="bi bi-heart-fill co-heart"></i>
						<c:set var="heart_flag" value="true"/>
					</c:if>
					
					<c:if test="${not heart_flag}">
						<i onclick="heartClick(this)" guestId="${memberDto.id}" cnt="${memberLikeNum}"
						class="bi bi-heart co-heart"></i>
					</c:if>
					
					<span class="heart-count" id="heartCount">${memberLikeNum}</span>
				</div>
				
				<!-- hidden -->
				<input type="hidden" name="myid" value="${sessionScope.myid}">
				<input type="hidden" name="guestId" value="${memberDto.id}">
				
				<div class="update" data-toggle="modal" data-target="#singoModal">
					<button id="openBtn" class="openBtn btn btn-warning" style="">신고하기</button>
				</div>

				<!-- 신고하기 모달 -->
				<div id="singoModal" class="modal" role="dialog">
					<div class="modal-dialog modal-dialog-centered">
						<!-- Modal content -->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h2 class="modal-title">신고하기</h2>
							</div>

							<div class="modal-body">
								<label for="id-singo">신고 할 아이디&nbsp;</label>
								<input type="text" id="singo-id" required="required" value="${memberDto.id}"
								readonly="readonly"><br>
								
								<label for="reason-singo">신고사유</label>
								<input type="text" id="singo-reason" required>
							</div>

							<div class="modal-footer">
								<button type="button" id="bodyBtn" class="btn btn-danger" data-dismiss="modal">신고하기</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<hr width="100%">
		
		<div class="profile-review">
			<!-- 후기 -->
			<div class="comment-wrap">
				<div class="comment-title">
					<label class="title">숙소 후기 ${commentNum} 개</label>
				</div>

				<div class="comment">
					<c:if test="${commentNum == 0}">
						<div class="empty">등록된 후기가 없습니다.</div>
					</c:if>

					<c:if test="${commentNum != 0}">
						<div class="co-wrap">
							<c:forEach var="coList" items="${commentList}">
								<div class="co-detail">
									<div class="mem-detail">
										<div class="mem-img">
											<img src="${root}/photo/memberPhoto/${coList.memDto.photo}"
												onclick="location.href='/profile/profileform?id=${coList.memDto.id}'">
										</div>

										<div class="mem-content">
											<fmt:formatDate var="wirteDay"
												value="${coList.gcoDto.write_day}" pattern="yyyy년 MM월 dd일" />
											<span class="mem-id"
												onclick="location.href='/profile/profileform?id=${coList.memDto.id}'">${coList.memDto.id}</span>
											<span class="write-day">${wirteDay}</span>
										</div>

										<c:set var="heart_flag" value="false" />
										<div class="co-heart-wrap">
											<c:forEach var="like" items="${likeList}">
												<c:if test="${like.no eq coList.gcoDto.no}">
													<i reserNo="${coList.gcoDto.no}" guestId="${coList.gcoDto.guest_id}" myId="${myid}"
													cnt="${coList.gcoDto.countLike}" onclick="coHeartClick(this)" class="bi bi-heart-fill co-heart"></i>
													<c:set var="heart_flag" value="true" />
												</c:if>
											</c:forEach>

											<c:if test="${not heart_flag}">
												<i reserNo="${coList.gcoDto.no}"
													guestId="${coList.gcoDto.guest_id}" myId="${myid}"
													cnt="${coList.gcoDto.countLike}"
													onclick="coHeartClick(this)" class="bi bi-heart co-heart"></i>
											</c:if>

											<span class="heart-count" id="${coList.gcoDto.no}">${coList.gcoDto.countLike}</span>
										</div>
									</div>

									<div class="co-content">
										<span>${coList.gcoDto.content}</span>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:if>
				</div>
			</div>
			<br>
			<br>
		</div>
	</div>

	<script src="/js/profileForm.js"></script>
</body>
</html>