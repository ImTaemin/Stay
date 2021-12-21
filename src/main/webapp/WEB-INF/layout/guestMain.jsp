<%@page import="stay.data.dto.RoomDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<!-- css -->
<link rel="stylesheet" href="../css/guestMain.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- Swiper -->
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
 <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" /> 
 <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<div class="guestMain-top">
		<!-- 검색핉터창 -->
		<div class="guestMain-filter">
			<div class="guestMain-map"><button type="button">위치 검색</button>  |  </div>
			<div class="guestMain-start"><button type="button">시작 날짜</button>  |  </div>
			<div class="guestMain-end"><button type="button">종료 날짜</button></div>
		</div>
		<div class="glyphicon glyphicon-search guestMain-search"></div>
	</div>
	
	<div class="guestMain-bottom">
		<!-- 인기숙소 -->
		<div class="guestMain-toproom">
			<span class="glyphicon glyphicon-heart red"></span> 
			인기 숙소
			<span class="glyphicon glyphicon-heart red"></span> 
		</div>
		
		<div class="guestMain-list">
			<!-- Slider main container -->
		    <div class="swiper-container">
		        <!-- Additional required wrapper -->
		        <div class="swiper-wrapper">
		            <!-- Slides ::슬라이더 -->
		            <c:forEach var="list" items="${roomList }">
		            	<div class="swiper-slide">
		            		<div class="guestMain-room"  onclick="location.href='room/content?no=${list.no}&currentPage=${currentPage}'">
									<div class="guestMain-image"><img alt="" src="${root}/photo/roomPhoto/${list.photos}"></div>
									<div class="guestMain-detail">
										<div class="guestMain-name">${list.name }</div>
										<div class="guestMain-site">${list.addr_load }</div>
									</div>
							</div>
		            	</div>
		            </c:forEach>
		        </div>
			
		        <!-- If we need navigation buttons ::양옆 좌우 버튼(선택) -->
		        <div class="swiper-button-next"></div>
		        <div class="swiper-button-prev"></div>
		    </div>
	
		</div>
	</div>
	<script src="${root}/js/guestMain.js"></script>
</body>
</html>