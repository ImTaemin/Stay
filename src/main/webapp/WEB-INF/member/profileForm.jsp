<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${root}/css/profileForm.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<title>프로필 화면</title>
</head>
<body>
	<div class="profile-main">
		<div class="profile-top">
			<div class="profile-first">
				<div class="photo">
					<%-- <img alt="" src="${root}/photo/memberPhoto/${memberDto.photo}"> --%>
				  <!-- 일반 로그인 -->
				  <c:if test="${sessionScope.kakaologin==null }">
					<img alt="" src="${root}/photo/memberPhoto/${memberDto.photo}">
				  </c:if>
				  <!-- 카카오 로그인 -->
				  <c:if test="${sessionScope.kakaologin!=null }">
					<img src="${sessionScope.profile}">
				  </c:if>
				</div>
				
				<div class="update" data-toggle="modal" data-target="#singoModal">
					<button class="openBtn">신고하기</button>
				</div>

				<!-- <div class="modal hidden"> 
				<div class="modal">
					<div class="bg"></div>
					<div class="modalBox">
						<p></p>
						<label for="id">신고 할 아이디&nbsp;</label>
						<input type="text" required="required" value="${sessionScope.myid }"><br><br>
						<label for="content">&nbsp;&nbsp;&nbsp;&nbsp;신고사유&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<input type="text" required="required">
						<button class="closeBtn">✖</button>
					</div>
				</div>-->

				
				<!-- 신고하기 모달 -->
				<div id="singoModal" class="modal" role="dialog">
					<div class="modal-dialog modal-dialog-centered">
						<!-- Modal content -->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">신고하기</h4>
							</div>

							<div class="modal-body">
								<label for="id-singo">신고 할 아이디&nbsp;</label>
								<input type="text" id="singo-id" required="required" value="${sessionScope.id }"><br>
								<label for="reason-singo">신고사유</label>
								<input type="text" id="singo-reason" required="required">
							</div>

							<div class="modal-footer">
								<button type="button" id="bodyBtn" class="btn btn-danger" 
									data-dismiss="modal">신고하기</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="profile-second">
				<c:if test="${sessionScope.loginok!=null }">
					<%-- <h2>
						<b>${sessionScope.myid } 님의<br>
						<br>프로필입니다.😊
						</b>
					</h2> --%>
					
					<h2>
						<b id="report_id">${memberDto.id }</b>
						<b> 님의<br>
							<br>프로필입니다.😊
						</b>
					</h2>
				</c:if>
			</div>
			
			<div class="profile-third">
				<div class="profile-like">
					<%-- <span class="glyphicon glyphicon-heart likes"
						style="width: 30px; cursor: pointer; color: red" num="${num}"></span> --%>
					<span class="glyphicon glyphicon-heart likes" onclick="likeClick(this)"
						style="width: 30px; cursor: pointer; color: red" user_id="${memberDto.id}"></span>
					<span>LIKE &nbsp;${memberDto.likes}</span>
				</div>
				<%-- <div class="profile-message">
					<span class="glyphicon glyphicon-envelope message" style="width: 30px; cursor: pointer;"></span>
					<span>MESSAGE &nbsp;${message}</span>
				</div> --%>
			</div>
		</div>
		<br><br>
		<hr style="border: 1px solid #eee; height: 0px !important; display: block !important; width: 100% !important;;"/>
		<div class="profile-review">
			
			<!-- 후기 -->
			<div class="comment-wrap">
				<div class="comment-title">
					<label class="title">숙소 후기  ${commentNum}  개</label>
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
											<fmt:formatDate var="wirteDay" value="${coList.gcoDto.write_day}" pattern="yyyy년 MM월 dd일"/>
											<span class="mem-id" onclick="location.href='/profile/profileform?id=${coList.memDto.id}'">${coList.memDto.id}</span>
											<span class="write-day">${wirteDay}</span>
										</div>
										
										<c:set var="heart_flag" value="false"/>
										<div class="co-heart-wrap">
											<c:forEach var="like" items="${likeList}">
												<c:if test="${like.no eq coList.gcoDto.no}">
													<i reserNo="${coList.gcoDto.no}" guestId="${coList.gcoDto.guest_id}" myId="${myid}"
													cnt="${coList.gcoDto.countLike}" onclick="coHeartClick(this)" class="bi bi-heart-fill co-heart"></i>
													<c:set var="heart_flag" value="true"/>
												</c:if>
											</c:forEach>
											
											<c:if test="${not heart_flag}">
												<i reserNo="${coList.gcoDto.no}" guestId="${coList.gcoDto.guest_id}" myId="${myid}"
												cnt="${coList.gcoDto.countLike}" onclick="coHeartClick(this)" class="bi bi-heart co-heart"></i>
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
			
			<br><br>
			
			<!-- <div class="hugi" data-toggle="modal" data-target="#hugiModal">
					<button class="review-many">후기 더 보기</button>
				</div>
				
				후기 더 보기 모달
				<div id="hugiModal" class="modal" role="dialog">
					<div class="modal-dialog modal-dialog-centered">
						Modal content
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">후기</h4>
							</div>

							<div class="modal-body">
								
							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div> -->
		</div>
	</div>
	
	<script src="/js/profileForm.js"></script>
</body>
</html>