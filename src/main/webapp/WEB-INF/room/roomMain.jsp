<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!-- css -->
<link rel="stylesheet" href="${root}/css/roomMain.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<!-- js -->
<!-- 카카오 지도 API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=	05c1abb954049537a223eedcab5c9b64"></script>
</head>
<body>
	<div class="room-main-wrap">
		<!-- 숙소 상세 목록 -->
		<div class="list">
			<c:forEach var="list" items="${roomList}">
				<div class="room-list-wrap">
					<div class="photo">
						<img alt="" src="${root}/photo/${list.photos}">
					</div>
					
					<div class="content">
						<div class="content-top">
							<b>${list.name}</b>
							<i id="heart-${list.no}" class="bi bi-heart"></i>
						</div>
						<label style="margin-top: 10px;">${list.addr_load}</label>
						
						<hr>
						
						<label style="font-size: 0.6em;">최대 인원 : ${list.max_per}명</label>
						
						<div class="star-cost">
							<div class="star">
								<label>⭐ <b>${avgRating}</b> ( 후기 ${totalComment}개 )</label>
							</div>
							
							<div class="cost">
								<label>
									<b style="font-size: 1.2em;">
										<fmt:formatNumber value="${list.price}" type="currency" currencySymbol="￦"/>
									</b>
								</label>
							</div>
						</div>
					</div>
				</div>
				
				<hr>
			</c:forEach>
			
			<!-- 페이징 -->
			<div style="width: 100%; text-align: center;">
				<ul class="pagination">
					<!-- 이전 -->
					<c:if test="${startPage > 1}">
						<li>
							<a href="main?currentPage=${startPage - 1}">
								<span class="bi bi-chevron-double-left"></span>
							</a>
						</li>
					</c:if>
					
					<c:forEach var="pp" begin="${startPage}" end="${endPage}">
						<c:if test="${currentPage == pp}">
							<li class="active">
								<a href="main?currentPage=${pp}">${pp}</a>
							</li>
						</c:if>
						
						<c:if test="${currentPage != pp}">
							<li>
								<a href="main?currentPage=${pp}">${pp}</a>
							</li>
						</c:if>
					</c:forEach>
					
					<!-- 다음 -->
					<c:if test="${endPage < totalPage}">
						<li>
							<a href="main?currentPage=${endPage + 1}">
								<span class="bi bi-chevron-double-right"></span>
							</a>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
		
		<!-- 지도 -->
		<div class="map-wrap">
			<div class="map" id="map"></div>
		</div>
		
		<script src="${root}/js/roomMain.js"></script>
	</div>
</body>
</html>